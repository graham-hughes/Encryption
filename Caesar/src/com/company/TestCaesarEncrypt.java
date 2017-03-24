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

        assertEquals("Shit by one failed.", "bcdef", ceasar.encrypt("abcde"));
        assertEquals("Shit by one failed.", "abcde", ceasar.decrypt("bcdef"));
    }

    @Test
    public void testCaesarLarge() {
        Caesar ceasar = new Caesar(800);

        assertEquals("Shit by one failed.", "ͨ΅ΌΌΏ͌̀\u038DῚΎ\u0381\u038D΅̀ΉΓ̀ͧΒ\u0381Έ\u0381\u038D",
                ceasar.encrypt("Hello, my name is Graham"));
        assertEquals("Shit by one failed.", "Hello, my name is Graham",
                ceasar.decrypt("ͨ΅ΌΌΏ͌̀\u038DῚΎ\u0381\u038D΅̀ΉΓ̀ͧΒ\u0381Έ\u0381\u038D"));
    }

}
