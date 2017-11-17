
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + value + ')';
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        T key = (T) o;
        if (!contains(key)) return false;
        Node<T> node = root;
        int comparison = -2;
        int lastComparison = -3;
        Node parent = root;
        if (root.value == key) {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null && root.right == null) {
                root = root.left;
            } else if (root.left == null && root.right != null) {
                root = root.right;
            } else {
                Node rightNode = root.right;
                Node leftNode = root.left;
                node = root.left;
                if (leftNode.right == null) {
                    root = leftNode;
                    root.right = rightNode;
                } else {
                    while (node.right != null) {
                        parent = node;
                        node = node.right;
                    }
                    parent.right = node.left;
                    root = node;
                    root.left = leftNode;
                    root.right = rightNode;
                }
            }
        } else {
            while (node.left != null || node.right != null) {
                comparison = key.compareTo(node.value);
                if (comparison == 0) break;
                if (comparison < 0) {
                    parent = node;
                    node = node.left;
                } else {
                    parent = node;
                    node = node.right;
                }
                lastComparison = key.compareTo((T) parent.value);
            }
            if (comparison == 0) {
                if (node.left != null && node.right == null) {
                    if (lastComparison < 0) {
                        parent.left = node.left;
                    } else {
                        parent.right = node.left;
                    }
                }
                if (node.left == null && node.right != null) {
                    if (lastComparison > 0) {
                        parent.right = node.right;
                    } else {
                        parent.left = node.right;
                    }
                }
                if (node.left != null && node.right != null) {
                    Node leftNode = node.left;
                    Node rightNode = node.right;
                    Node lastNode = node;
                    node = node.left;
                    while (node.right != null) {
                        lastNode = node;
                        node = node.right;
                    }
                    if (node.left != null) {
                        lastNode.right = node.left;
                    } else lastNode.right = null;
                    parent.left = node;
                    parent.left.right = rightNode;
                    parent.left.left = leftNode.left;
                }

            } else {
                if (lastComparison < 0) parent.left = null;
                else parent.right = null;
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }


    }


    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public void prn() {
        Queue<Node> q1 = new LinkedList<Node>();

        q1.add(root);
        prn(q1);
    }

    private void prn(Queue<Node> q1) {
        Queue<Node> q2 = new LinkedList<Node>();
        for (Node n : q1) {
            if (n != null) System.out.print(n);
        }
        System.out.println();
        while (!q1.isEmpty()) {
            Node node = q1.poll();
            if (node != null && node.left != null) q2.add(node.left);
            else q2.add(null);
            if (node != null && node.right != null) q2.add(node.right);
            else q2.add(null);
        }
        q1 = q2;
        int count = 0;
        for (Node n : q2) {
            if (n != null) {
                count++;
                break;
            }
        }
        if (count != 0) prn(q1);
        else System.out.println();

    }

}

