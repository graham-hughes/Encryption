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
    BitSet[] subKeys;
    private int[] PC1 = {57, 49, 41, 33, 25, 17, 9,
                        1, 58, 50, 42, 34, 26, 18,
                        10, 2, 59, 51, 43, 35, 27,
                        19, 11, 3, 60, 52, 44, 36,
                        63, 55, 47, 39, 31, 23, 15,
                        7, 62, 54, 46, 38, 30, 22,
                        14, 6, 61, 53, 45, 37, 29,
                        21, 13, 5, 28, 20, 12, 4};
      
    private int[] PC2 = {14, 17, 11, 24,  1, 5,
                        3, 28, 15, 6, 21, 10,
                        23, 19, 12, 4, 26, 8,
                        16, 7, 27, 20, 13, 2,
                        41, 52, 31, 37, 47, 55,
                        30, 40, 51, 45, 33, 48,
                        44, 49, 39, 56, 34, 53,
                        46, 42, 50, 36, 29, 32};


    public Keys(String k) {
        subKeys = new BitSet[16];
        BitSet[] subKeysC;
        BitSet[] subKeysD;
        BitSet[] subKeysK = new BitSet[16];

        if (k.getBytes(StandardCharsets.UTF_8).length != 8) {
            throw new IllegalArgumentException("Key is the wrong length");
        }
        BitSet temp = BitSet.valueOf(k.getBytes());
        keyPlus = new BitSet(56);

        // Initial permutation that also removes the 8 parity bits
        for (int i = 0; i < 56; i ++) {
            keyPlus.set(i, temp.get(PC1[i]));
        }

        BitSet cNaught = keyPlus.get(0, 28);
        BitSet dNaught = keyPlus.get(28, 56);

        subKeysC = subKeysPC1(cNaught);
        subKeysD = subKeysPC1(dNaught);

        /* Concatenates C and D keys into subKeysK*/
        for (int i = 0; i < 16; i++) {
            subKeysK[i] = new BitSet(56);
            for (int q = 0; q < 28; q++) {
                subKeysK[i].set(q, subKeysC[i].get(q));
            }
            for (int c = 28; c < 56; c++) {
                subKeysK[i].set(c, subKeysD[i].get(c - 28));
            }
        }

        /* Final operation that permutes subkeysK into the final set of subkeys */
        for (int i = 0; i < 16; i++) {
            subKeys[i] = subKeysPC2(subKeysK[i]);
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

    /* Helper method for generating a subkey for PC2 */
    private BitSet subKeysPC2(BitSet k) {
        BitSet next = new BitSet(48);
        for (int i = 0; i < 48; i++) {
            next.set(0, k.get(PC2[i]));
        }
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
