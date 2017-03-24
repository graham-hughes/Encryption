package com.company;

/**
 * Created by grahamhughes on 3/23/17.
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class TestCaesarEncrypt {
    @Test
    public void testCaesarOne() {
        Caesar ceasar = new Caesar(1);

        assertEquals("Encrypt by one failed.", "BCDEF", ceasar.encrypt("ABCDE"));
        assertEquals("Decrypt by one failed.", "ABCDE", ceasar.decrypt("BCDEF"));
    }

    @Test
    public void testCaesarLarge() {
        Caesar ceasar = new Caesar(800);

        assertEquals("Shit by 800 failed.", "BYFFI GS HUGY CM ALUBUG",
                ceasar.encrypt("HELLO MY NAME IS GRAHAM"));
        assertEquals("Shit by 800 failed.", "HELLO MY NAME IS GRAHAM",
                ceasar.decrypt("BYFFI GS HUGY CM ALUBUG"));
    }

}
