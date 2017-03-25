package com.company;

/**
 * Created by grahamhughes on 3/24/17.
 */

import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

public class TestCaesarCracker {
    @Test
    public void testCracker() throws FileNotFoundException {
        CaesarCracker cracker = new CaesarCracker("YVCCF DP ERDV ZJ DRIB Z CZBV GZQQR");
        assertEquals("Failed cracking",
                "HELLO MY NAME IS MARK I LIKE PIZZA", cracker.bestGuess());
    }

    @Test
    public void testEncryptAndCrack() throws FileNotFoundException {
        Caesar caesar = new Caesar(10053);
        String str = "NEVER GONNA GIVE YOU UP NEVER GONNA LET YOU DOWN";
        CaesarCracker cracker = new CaesarCracker(caesar.encrypt(str));
        assertEquals("Failed cracking", str, cracker.bestGuess());
    }

    @Test
    public void testLarge() throws FileNotFoundException {
        Caesar caesar = new Caesar(10053);
        String str = "HELLO MY NAME IS GRAHAM AND I LIKE PUPPIES AND COFFEE";
        for (int i = 0; i < 1000; i++) {
            str += "HELLO MY NAME IS GRAHAM AND I LIKE PUPPIES AND COFFEE";
        }
        CaesarCracker cracker = new CaesarCracker(caesar.encrypt(str));
        assertEquals("Failed cracking", str, cracker.bestGuess());

    }
}
