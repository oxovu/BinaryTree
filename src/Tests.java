import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class Tests {
    @Test
    public void remove() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(11);
        binaryTree.add(9);
        binaryTree.add(20);
        binaryTree.add(2);
        binaryTree.add(10);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(5);
        binaryTree.add(6);

        binaryTree.prn();


        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.remove(5));
        assertFalse(binaryTree.contains(5));
        assertFalse(binaryTree.remove(5));
        assertTrue(binaryTree.checkInvariant());
        binaryTree.prn();

        assertTrue(binaryTree.contains(9));
        assertTrue(binaryTree.remove(9));
        assertFalse(binaryTree.contains(9));
        assertFalse(binaryTree.remove(9));
        assertTrue(binaryTree.checkInvariant());
        binaryTree.prn();

        assertTrue(binaryTree.contains(11));
        assertTrue(binaryTree.remove(11));
        assertFalse(binaryTree.contains(11));
        assertFalse(binaryTree.remove(11));
        assertTrue(binaryTree.checkInvariant());
        binaryTree.prn();

        assertTrue(binaryTree.contains(6));
        assertTrue(binaryTree.remove(6));
        assertFalse(binaryTree.contains(6));
        assertFalse(binaryTree.remove(6));
        assertTrue(binaryTree.checkInvariant());
        binaryTree.prn();


    }
}
