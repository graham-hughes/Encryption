package com.grahamhughes.symmetric;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 * Created by grahamhughes on 3/26/17.
 *
 * Reference: page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm
 */
public class Keys {
    BitSet keyPlus;
    BitSet[] keys;

    public Keys(String k) {
        keys = new BitSet[16];
        BitSet[] subKeysC;
        BitSet[] subKeysD;
        BitSet[] subKeysK = new BitSet[16];

        if (k.getBytes(StandardCharsets.UTF_8).length != 8) {
            throw new IllegalArgumentException("Key is the wrong length");
        }
        BitSet temp = BitSet.valueOf(k.getBytes());
        keyPlus = new BitSet(56);

        int j = 65;
        for (int i = 0; i < 56; i ++) {
            j -= 8;
            if (j < 0) {
                j += 65;
            }
            keyPlus.set(i, temp.get(j));
        }
        BitSet cNaught = keyPlus.get(0, 28);
        BitSet dNaught = keyPlus.get(28, 56);

        subKeysC = subKeysPC1(cNaught);
        subKeysD = subKeysPC1(dNaught);

        for (int i = 0; i < 16; i++) {
            subKeysK[i] = new BitSet(56);
            for (int q = 0; q < 28; q++) {
                subKeysK[i].set(q, subKeysC[i].get(q));
            }
            for (int c = 28; c < 56; c++) {
                subKeysK[i].set(c, subKeysD[i].get(c - 28));
            }
        }

        for (int i = 0; i < 16; i++) {
            keys[i] = subKeysPC2(subKeysK[i]);
        }
    }

    /* Helper method for generating the 16 subkeys for C, D */
    private BitSet[] subKeysPC1(BitSet k) {
        BitSet[] sub = new BitSet[16];
        BitSet next = k;
        for (int i = 0; i < 16; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 15) {
                next = leftShifted(next);
            } else {
                next = leftShifted(leftShifted(next));
            }
            sub[i] = next;
        }
        return sub;
    }

    /* Helper method for generating a subkey for PC2
    *  This is ugly, but I couldn't find any justification better than a table */
    private BitSet subKeysPC2(BitSet k) {
        BitSet next = new BitSet(48);
        next.set(0, k.get(14));
        next.set(1, k.get(17));
        next.set(2, k.get(11));
        next.set(3, k.get(24));
        next.set(4, k.get(1));
        next.set(5, k.get(5));

        next.set(6, k.get(3));
        next.set(7, k.get(28));
        next.set(8, k.get(15));
        next.set(9, k.get(6));
        next.set(10, k.get(21));
        next.set(11, k.get(10));

        next.set(12, k.get(23));
        next.set(13, k.get(19));
        next.set(14, k.get(12));
        next.set(15, k.get(4));
        next.set(16, k.get(26));
        next.set(17, k.get(8));

        next.set(18, k.get(16));
        next.set(19, k.get(7));
        next.set(20, k.get(27));
        next.set(21, k.get(20));
        next.set(22, k.get(13));
        next.set(23, k.get(2));


        next.set(24, k.get(41));
        next.set(25, k.get(52));
        next.set(26, k.get(31));
        next.set(27, k.get(37));
        next.set(28, k.get(47));
        next.set(29, k.get(55));

        next.set(30, k.get(30));
        next.set(31, k.get(40));
        next.set(32, k.get(51));
        next.set(33, k.get(45));
        next.set(34, k.get(33));
        next.set(35, k.get(48));

        next.set(36, k.get(44));
        next.set(37, k.get(49));
        next.set(38, k.get(39));
        next.set(39, k.get(56));
        next.set(40, k.get(34));
        next.set(41, k.get(53));

        next.set(42, k.get(46));
        next.set(43, k.get(42));
        next.set(44, k.get(50));
        next.set(45, k.get(36));
        next.set(46, k.get(29));
        next.set(47, k.get(32));
        return next;
    }

    /* Helper method for generating left shifting bitsets of length 56*/
    private BitSet leftShifted(BitSet b) {
        BitSet leftShifted = new BitSet(28);
        for (int i = 0; i < 28; i++) {
            if (i == 28 - 1) {
                leftShifted.set(i, b.get(0));
            } else {
                leftShifted.set(i, b.get(i + 1));
            }
        }
        return leftShifted;
    }
}
