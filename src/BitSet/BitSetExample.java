package BitSet;

import java.util.*;

public class BitSetExample implements Iterable<Integer> {
    // 10010100010001001101000
    // 01110111011001011010000

    // 00010100010001001... &

    // &, |, ~, <<, >>: Java bit operations

    private final byte[] bitSet;
    private final int cardinal;

    public BitSetExample(int cardinal) {
        if (cardinal <= 0) throw new IllegalArgumentException();
        else this.cardinal = cardinal;
        if (cardinal % 8 == 0) bitSet = new byte[cardinal / 8];
        else bitSet = new byte[cardinal / 8 + 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof BitSetExample) {
            BitSetExample other = (BitSetExample) obj;
            if (this.cardinal != other.cardinal) return false;
            for (int i = 0; i < this.bitSet.length; i++) {
                if (this.bitSet[i] != other.bitSet[i]) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.cardinal; i++) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bitSet) + cardinal;
    }

    public int getCardinal() {
        return cardinal;
    }

    public boolean check(int element) {
        if (element < 0 && element >= cardinal) throw new IndexOutOfBoundsException();

        int indexOfBitSet = element / 8;
        byte numb = bitSet[indexOfBitSet];
        return ((numb >> (8 * (indexOfBitSet + 1) - element - 1)) & 1) == 1;
    }

    public BitSetExample union(BitSetExample other) {
        if (this.cardinal != other.cardinal) throw new NullPointerException();

        for (int i = 0; i < this.bitSet.length; i++) {
            this.bitSet[i] = (byte) (this.bitSet[i] | other.bitSet[i]);
        }
        return this;
    }

    public BitSetExample intersection(BitSetExample other) {
        if (this.cardinal != other.cardinal) throw new NullPointerException();

        for (int i = 0; i < this.bitSet.length; i++) {
            this.bitSet[i] = (byte) (this.bitSet[i] & other.bitSet[i]);
        }
        return this;
    }

    public BitSetExample complement() {
        BitSetExample result = new BitSetExample(this.cardinal);
        for (int i = 0; i < this.cardinal; i++) {
            if (!check(i)) result.addElement(i);
        }
        return result;
    }

    public boolean addElement(int element) {
        if (!check(element)) {
            int indexOfBitSet = element / 8;
            byte numb = bitSet[indexOfBitSet];
            numb = (byte) (numb | (1 << (8 * (indexOfBitSet + 1) - element - 1)));
            bitSet[indexOfBitSet] = numb;
            return true;
        }
        return false;
    }

    public boolean removeElement(int element) {
        if (check(element)) {
            int indexOfBitSet = element / 8;
            byte numb = bitSet[indexOfBitSet];
            numb = (byte) (numb ^ (1 << (8 * (indexOfBitSet + 1) - element - 1)));
            bitSet[indexOfBitSet] = numb;
            return true;
        }
        return false;
    }

    public int addMassive(int[] elements) {
        int result = 0;
        for (int element : elements) {
            if (!check(element)) {
                addElement(element);
                result++;
            }
        }
        return result;
    }

    public int removeMassive(int[] elements) {
        int result = 0;
        for (int element : elements) {
            if (check(element)) {
                removeElement(element);
                result++;
            }
        }
        return result;
    }

    // Бонус
    @Override
    public Iterator<Integer> iterator() {
        return new BitSetIterator();
    }

    private class BitSetIterator implements Iterator<Integer> {
        private int cursor;

        public BitSetIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            for (int i = cursor; i < BitSetExample.this.cardinal; i++) {
                if (BitSetExample.this.check(i)) {
                    return true;
                }
                cursor++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if (this.hasNext()) {
                int current = cursor;
                cursor++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
}
