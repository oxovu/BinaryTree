import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Tests {
    @Test
    public void remove() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(29);
        binaryTree.add(27);
        binaryTree.add(26);
        binaryTree.add(7);
        binaryTree.add(29);
        binaryTree.add(87);
        binaryTree.add(93);
        binaryTree.add(65);
        binaryTree.add(82);
        binaryTree.add(19);
        binaryTree.add(79);
        binaryTree.add(38);
        binaryTree.add(50);
        binaryTree.add(1);
        binaryTree.add(22);
        binaryTree.add(48);
        binaryTree.add(89);
        binaryTree.add(91);
        binaryTree.add(97);
        binaryTree.add(91);

        assertTrue(binaryTree.contains(50));
        assertTrue(binaryTree.remove(50));
        assertFalse(binaryTree.contains(50));
        assertEquals(binaryTree.size(), 17);
        assertFalse(binaryTree.remove(50));
        assertTrue(binaryTree.checkInvariant());
        binaryTree.prn();
        System.out.println(binaryTree.size());


    }

    @Test
    public void veryBigTest() {
        for (int i = 0; i < 100; i++) {
            assertTrue(VeryBigTest());
        }
    }

    private boolean VeryBigTest() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
//Заполняем дерево 50 случайными числами от 0 до 1000
        while (binaryTree.size() != 50) {
            int next = random.nextInt(1000);
            if (binaryTree.add(next)) list.add(next);
        }
//Удаляем из дерева случайное число
        int removeIt = list.get(random.nextInt(50));
        assertTrue(binaryTree.remove(removeIt));
        assertEquals(binaryTree.size(), 49);
        return true;
    }
}
