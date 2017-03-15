package lesson1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    final int cardinal = 17;
    BitSetExample bitSet1 = new BitSetExample(cardinal);
    BitSetExample bitSet2 = new BitSetExample(cardinal);
    final int newCardinal = 8;
    BitSetExample newBitSet1 = new BitSetExample(newCardinal);
    BitSetExample newBitSet2 = new BitSetExample(newCardinal);

    @Test
    public void getCardinal() {
        assertEquals(17, bitSet1.getCardinal());
    }

    @Test
    public void check() {
        bitSet1.addElement(1);
        bitSet1.addElement(4);
        bitSet1.addElement(5);
        bitSet1.addElement(7);
        assertTrue(bitSet1.check(1));
        assertTrue(bitSet1.check(4));
        assertTrue(bitSet1.check(5));
        assertFalse(bitSet1.check(3));
    }

    @Test
    public void union() {
        for (int i = 0; i < cardinal; i++) {
            if (i % 2 == 0)
                bitSet1.addElement(i);
            if (i % 2 == 0 && i < newCardinal)
                bitSet2.addElement(i);
        }
        assertEquals(bitSet1, bitSet1.union(bitSet2));
    }

    @Test
    public void intersection() {
        for (int i = 0; i < cardinal; i++) {
            if (i < 10) bitSet1.addElement(i);                   //11111111110000000
            if (i < 5) bitSet2.addElement(i);                 //11111000000000000
        }

        assertEquals(bitSet2, bitSet1.intersection(bitSet2));
    }

    @Test
    public void complement() {
        for (int i = 0; i < newCardinal; i++) {
            if (i < 2) newBitSet1.addElement(i);
            else newBitSet2.addElement(i);
        }

        assertEquals(newBitSet1, newBitSet2.complement());
        for (int i = 0; i < cardinal; i++) {
            if(i < 10) bitSet1.addElement(i);
            else bitSet2.addElement(i);
        }

        assertEquals(bitSet2, bitSet1.complement());
    }

    @Test
    public void addElement() {
        bitSet1.addElement(4);
        assertTrue(bitSet1.addElement(1));
        assertTrue(bitSet1.addElement(5));
        assertTrue(bitSet1.addElement(7));
        assertFalse(bitSet1.addElement(4));
    }

    @Test
    public void removeElement() {
        bitSet1.addElement(1);
        bitSet1.addElement(5);
        assertTrue(bitSet1.removeElement(1));
        assertTrue(bitSet1.removeElement(5));
        assertFalse(bitSet1.removeElement(10));
    }

    @Test
    public void addMassive() {
        bitSet1.addElement(1);
        bitSet1.addElement(4);
        assertEquals(3, bitSet1.addMassive(new int[]{1, 3, 5, 7}));
        assertEquals(1, bitSet1.addMassive(new int[]{4, 10}));
    }

    @Test
    public void removeMassive() {
        bitSet1.addElement(5);
        bitSet1.addElement(7);
        assertEquals(2, bitSet1.removeMassive(new int[]{7, 5, 2, 3}));
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