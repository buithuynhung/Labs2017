package BitSet;

import com.sun.org.apache.xerces.internal.impl.dv.util.ByteListImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    final int cardinal = 17;
    BitSetExample bitSet = new BitSetExample(cardinal);
    final int newCardinal = 5;
    BitSetExample newBitSet = new BitSetExample(newCardinal);

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
        BitSetExample result = new BitSetExample(newCardinal);  //            00000
        for (int i = 0; i < cardinal; i++) {
            if (i < 10) bitSet.addElement(i);                   //11111111110000000
            if (i < 5) newBitSet.addElement(i);                 //            11111
        }

        assertEquals(result, bitSet.intersection(newBitSet));

        for (int i = 0; i < cardinal; i++) {
            if (i % 2 == 0) bitSet.addElement(i);               //10101010101010101
            if (i % 2 == 0 && i < newCardinal) newBitSet.addElement(i); //    10101
        }

        assertEquals(newBitSet, bitSet.intersection(newBitSet));
    }

    @Test
    public void complement() {
        BitSetExample newBitSet = new BitSetExample(cardinal);
        for (int i = 0; i < cardinal; i++) {
            if (i > 10) {
                bitSet.addElement(i);
            } else newBitSet.addElement(i);
        }

        assertEquals(newBitSet, bitSet.complement());
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

}