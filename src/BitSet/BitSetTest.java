package BitSet;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    final int cardinal = 17;
    BitSetExample bitSet = new BitSetExample(cardinal);
    final int newCardinal = 5;
    BitSetExample newBitSet = new BitSetExample(cardinal);

    @Test
    public void getCardinal() {
        assertEquals(17, bitSet.getCardinal());
    }

    @Test
    public void check() {
        bitSet.addElement(1);
        bitSet.addElement(4);
        bitSet.addElement(5);
        bitSet.addElement(7);
        assertTrue(bitSet.check(1));
        assertTrue(bitSet.check(4));
        assertTrue(bitSet.check(5));
        assertFalse(bitSet.check(3));
    }

    @Test
    public void union() {
        for (int i = 0; i < cardinal; i++) {
            if (i % 2 == 0)
                bitSet.addElement(i);
            if (i % 2 == 0 && i < newCardinal)
                newBitSet.addElement(i);
        }
        assertEquals(bitSet, bitSet.union(newBitSet));
    }

    @Test
    public void intersection() {
        for (int i = 0; i < cardinal; i++) {
            if (i < 10) bitSet.addElement(i);                   //11111111110000000
            if (i < 5) newBitSet.addElement(i);                 //11111000000000000
        }

        assertEquals(newBitSet, bitSet.intersection(newBitSet));
    }

    @Test
    public void complement() {
        BitSetExample result = new BitSetExample(cardinal);
        for (int i = 0; i < cardinal; i++) {
            if (i > 10)
                bitSet.addElement(i);
            if (i < 12)
                newBitSet.addElement(i);
            if (i != 11)
                result.addElement(i);
        }

        assertEquals(result, bitSet.complement(newBitSet));
    }

    @Test
    public void addElement() {
        bitSet.addElement(4);
        assertTrue(bitSet.addElement(1));
        assertTrue(bitSet.addElement(5));
        assertTrue(bitSet.addElement(7));
        assertFalse(bitSet.addElement(4));
    }

    @Test
    public void removeElement() {
        bitSet.addElement(1);
        bitSet.addElement(5);
        assertTrue(bitSet.removeElement(1));
        assertTrue(bitSet.removeElement(5));
        assertFalse(bitSet.removeElement(10));
    }

    @Test
    public void addMassive() {
        bitSet.addElement(1);
        bitSet.addElement(4);
        assertEquals(3, bitSet.addMassive(new int[]{1, 3, 5, 7}));
        assertEquals(1, bitSet.addMassive(new int[]{4, 10}));
    }

    @Test
    public void removeMassive() {
        bitSet.addElement(5);
        bitSet.addElement(7);
        assertEquals(2, bitSet.removeMassive(new int[]{7, 5, 2, 3}));
    }

    @Test
    public void iterator() {
        int cursor = 0;
        BitSetExample ex1 = new BitSetExample(10);
        for (int i = 0; i < ex1.getCardinal(); i++) {
            if (i % 2 == 0) ex1.addElement(i);
        }

        Iterator it = ex1.iterator();

        while (it.hasNext()) {
            assertEquals(cursor, it.next());
            cursor += 2;
        }
    }

}