package BitSet;

import java.util.*;

public class BitSetExample {
    // 10010100010001001101000
    // 01110111011001011010000

    // 00010100010001001... &

    // &, |, ~, <<, >>: Java bit operations
    // bit set
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
                else continue;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return bitSet.hashCode() + cardinal;
    }

    public int getCardinal() {
        return cardinal;
    }

    public boolean check(int element) {
        if (element < 0 && element >= cardinal) throw new IndexOutOfBoundsException();
        else {
            int indexOfBitSet = element / 8;
            byte numb = bitSet[indexOfBitSet];
            return ((numb >> (8 * (indexOfBitSet + 1) - element - 1)) & 1) == 1;
        }
    }

    public BitSetExample union(BitSetExample other) {
        int dist1 = this.cardinal - other.cardinal;
        BitSetExample set1 = (dist1 >= 0) ? this : other;
        BitSetExample set2 = (dist1 >= 0) ? other : this;
        BitSetExample result = new BitSetExample(Math.max(this.cardinal, other.cardinal));
        int distance = set1.bitSet.length - set2.bitSet.length;
        for (int i = 0; i < set1.bitSet.length; i++) {
            if (distance > 0 && i < distance)
                result.bitSet[i] = set1.bitSet[i];
            else
                result.bitSet[i] = (byte) (set1.bitSet[i] | set2.bitSet[i - distance]);
        }
        return result;
    }

    public BitSetExample intersection(BitSetExample other) {
        int dist1 = this.cardinal - other.cardinal;
        BitSetExample set1 = (dist1 > 0) ? this : other;
        BitSetExample set2 = (dist1 < 0) ? this : other;
        BitSetExample result = new BitSetExample(Math.min(this.cardinal, other.cardinal));
        int distance = set1.bitSet.length - set2.bitSet.length;
        for (int i = 0; i < set2.bitSet.length; i++) {
            result.bitSet[i] = (byte) (set1.bitSet[i + distance] & set2.bitSet[i]);
        }
        return result;
    }

    public BitSetExample complement() {
        for (int i = 0; i < bitSet.length; i++) {
            this.bitSet[i] = (byte) ~this.bitSet[i];
        }
        return this;
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

}
