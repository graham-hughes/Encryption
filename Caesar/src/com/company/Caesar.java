package com.company;

/**
 * Created by grahamhughes on 3/23/17.
 * Caesar encryption shifts each character in the input by a given number
 * Decrypting only requires shifting each characters backwards by the same number
 */
public class Caesar {

    private static int shift;

    public Caesar(int shiftBy) {
        shift = shiftBy;
    }

    /** Returns the encrypted input */
    public String encrypt(String input) {
        String output = "";
        for (char c : input.toCharArray()) {
            output += (char) (c + shift);
        }
        return output;
    }

    /** Given an encrypted input, returns the decrypted string */
    public String decrypt(String input) {
        String output = "";
        for (char c : input.toCharArray()) {
            output += (char) (c - shift);
        }
        return output;
    }
}
