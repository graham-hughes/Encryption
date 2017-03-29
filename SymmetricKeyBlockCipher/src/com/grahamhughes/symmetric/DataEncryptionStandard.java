package com.grahamhughes.symmetric;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by grahamhughes on 3/26/17.
 *
 * Reference for DES details - page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm
 */
public class DataEncryptionStandard {
    Keys keys;
    private int[] IP = {58,  50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};

    private int[] E = {32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1};

    public DataEncryptionStandard(String k) {
        keys = new Keys(k);
    }


    /* Turns an input string into an array of blocks
     * Package private for testing purposes */
    ArrayList<Block> stringToBlocks(String input) {
        ArrayList<Block> output = new ArrayList<>();

        byte[] inBytes = input.getBytes(StandardCharsets.UTF_8);
        // Pads with zeros up to nearest 8 because each Block has 64 bits (8 bytes)
        byte[] in  = new byte[8 * ((inBytes.length + 7) / 8)];
        System.arraycopy(inBytes, 0, in, 0, inBytes.length);

        for (int i = 0; i < in.length/8; i++) {
            byte[] temp = new byte[8];
            System.arraycopy(in, i*8, temp, 0, 8);
            output.add(new Block(BitSet.valueOf(temp)));
        }

        return output;
    }

    /* Not finished */
    public String encrypt(String str) {
        ArrayList<Block> blocks = stringToBlocks(str);
        String output = "";
        for (Block b : blocks) {
            output += encrypt(b);
        }
        return output;
    }

    /* Not finished */
    private String encrypt(Block block) {
        BitSet IPLeft = new BitSet(32);
        BitSet IPRight = new BitSet(32);
        /* Performs initial permutation and splits into left/right*/
        for (int i = 0; i < 32; i ++) {
            IPLeft.set(i, block.getBlock().get(IP[i]));
        }
        for (int i = 0; i < 32; i ++) {
            IPRight.set(i, block.getBlock().get(IP[i + 32]));
        }

        BitSet IPLeftMinusOne;
        BitSet IPRightMinusOne;
        for (int i = 0; i < 16; i++) {
            IPLeftMinusOne = IPLeft;
            IPRightMinusOne = IPRight;
            IPLeft = IPRightMinusOne;
            IPRight = IPLeftMinusOne;
            IPRight.xor(functionF(IPRightMinusOne, keys.subKeys[i]));
        }


        return "";
    }

    private BitSet functionF(BitSet data, BitSet key) {
        return null;
    }

}
