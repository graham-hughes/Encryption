package com.company;

/**
 * Created by grahamhughes on 3/23/17.
 * Referenced en.wikipedia.org/wiki/Caesar_cipher for Cipher details
 * <p>
 * Caesar encryption shifts each letter in the input by a given number, then mods by 26
 * Note: spaces are not shifted, and all upper case inputs are required as per Wikipedia details
 * Decrypting only requires shifting each characters backwards by the same number
 */
public class Caesar {

    private static int shift;

    public Caesar(int shiftBy) {
        shift = shiftBy;
    }

    /**
     * Returns the encrypted input
     */
    public String encrypt(String input) {
        String output = "";
        for (char c : input.toCharArray()) {
            if (c != ' ' && !Character.isUpperCase(c)) {
                throw new IllegalArgumentException("Character '" + c + "' is not capitalized");
            } else if (c == ' ') {
                output += c;
            } else {
                output += (char) (Math.floorMod((c + shift - 'A'), 26) + 'A');
            }
        }
        return output;
    }

    /**
     * Given an encrypted input, returns the decrypted string
     */
    public String decrypt(String input) {
        String output = "";
        for (char c : input.toCharArray()) {
            if (c != ' ' && !Character.isUpperCase(c)) {
                throw new IllegalArgumentException("Character '" + c + "' is not capitalized");
            } else if (c == ' ') {
                output += c;
            } else {
                output += (char) (Math.floorMod((c - shift - 'A'), 26) + 'A');
            }
        }
        return output;
    }
}
