package com.grahamhughes.symmetric;

/**
 * Created by grahamhughes on 3/26/17.
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDataEncryptionStandard {
    /* Tests stringToBinary method by encoding a string as a sequence of blocks
     * And then decoding the string and comparing it against the original
     */
    @Test
    public void testStringToBlocks() {
        String input = "Hello, my name is Graham Hughes";
        DataEncryptionStandard des = new DataEncryptionStandard(0);
        String output = "";
        for (Block b : des.stringToBlocks(input)) {
            output += new String(b.getBlock().toByteArray());
        }
        assertEquals("Failed encoding to decoding", input, output);
    }
}
