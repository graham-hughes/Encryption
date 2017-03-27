package com.grahamhughes.symmetric;

/**
 * Created by grahamhughes on 3/26/17.
 */

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import static org.junit.Assert.*;

public class TestDataEncryptionStandard {
    /* Tests stringToBinary method by encoding a string as a sequence of blocks
     * And then decoding the string and comparing it against the original
     */
    @Test
    public void testStringToBlocks() {
        String input = "Hello, my name is Graham Hughes";
        DataEncryptionStandard des = new DataEncryptionStandard("12345678");
        String output = "";
        for (Block b : des.stringToBlocks(input)) {
            output += new String(b.getBlock().toByteArray());
        }
        assertEquals("Failed encoding to decoding", input, output);
    }


    /* For bitsets of length 56 */
    public String toString(BitSet b, int len) {
        String out = "";
        for (int i = 0; i < len; i ++) {
            if (b.get(i) == true) {
                out += "1";
            } else {
                out += "0";
            }
            if (i != 0 && (i + 1) % 4 == 0) {
                out += " ";
            }
        }
        return out;
    }
}
