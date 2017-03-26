package com.grahamhughes.caesar;

import java.io.BufferedReader;

/**
 * Created by grahamhughes on 3/24/17.
 *
 * References:
 *  * gist.github.com/deekayen/4148741 for a list of 1000 most common English words (my dictionairy.txt file)
 *  * stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java for parsing dictionairy
 *
 * Takes in an encrypted string and makes a best guess based off of word frequency
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CaesarCracker {
    private HashSet<String> english;
    private String[] attempts;
    private int[] englishWords;
    private int bestGuessOffset;

    /* Cracks by checking which version of the decrypted string has the most words in the dictionairy */
    public CaesarCracker(String encrypted) throws FileNotFoundException{
        english = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader("dictionairy.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                english.add(line.toUpperCase());
            }
        } catch (IOException io) {
            System.out.println("IOException");
        }

        attempts = new String[26];
        englishWords = new int[26];
        for (int i = 0; i < 25; i++) {
            String attempt = attemptDecrypt(encrypted, i);
            attempts[i] = attempt;
            englishWords[i] = numEnglishWords(attempt);
        }

        int highest = englishWords[0];
        bestGuessOffset = 0;
        for (int i = 1; i < 26; i++) {
            if (englishWords[i] > highest) {
                highest = englishWords[i];
                bestGuessOffset = i;
            }
        }
    }

    public String bestGuess() {
        return attempts[bestGuessOffset];
    }


    /* Given an encrypted input and shift, returns the shifted string */
    private String attemptDecrypt(String input, int shift) {
        String output = "";
        for (char c : input.toCharArray()) {
            if (c != ' ' && c != '\n' && !Character.isUpperCase(c)) {
                throw new IllegalArgumentException("Character '" + c + "' is not capitalized");
            } else if (c == ' ' || c == '\n') {
                output += c;
            } else {
                output += (char) (Math.floorMod((c - shift - 'A'), 26) + 'A');
            }
        }
        return output;
    }

    /* Compares each word in the input string against the 1000 most common english words
    *  Returns the number of recognized words in the input string*/
    private int numEnglishWords(String str) {
        int numWords = 0;
        String[] strings = str.split(" ");
        for (String word : strings) {
            if (english.contains(word)) {
                numWords ++;
            }
        }
        return numWords;
    }
}
