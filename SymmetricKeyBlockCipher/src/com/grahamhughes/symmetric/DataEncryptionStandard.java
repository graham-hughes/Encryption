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
    private int key;
    public DataEncryptionStandard(int k) {
        key = k;
    }


    /* Turns an input string into an array of blocks
    *  Package private for testing purposes */
    ArrayList<Block> stringToBlocks(String input) {
        ArrayList<Block> output = new ArrayList<>();

        byte[] inBytes = input.getBytes(StandardCharsets.UTF_8);
        // in needs to be rounded up to nearest multiple of 8
        // because each block is 64 bits (8 bytes)
        byte[] in  = new byte[8 * ((inBytes.length + 7) / 8)];
        System.arraycopy(inBytes, 0, in, 0, inBytes.length);

        for (int i = 0; i < in.length/8; i++) {
            byte[] temp = new byte[8];
            System.arraycopy(in, i*8, temp, 0, 8);
            output.add(new Block(BitSet.valueOf(temp)));
        }


        return output;
    }


}
