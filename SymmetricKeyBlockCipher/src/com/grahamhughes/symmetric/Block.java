package com.grahamhughes.symmetric;

import java.util.BitSet;

/**
 * Created by grahamhughes on 3/26/17.
 * One block of 64 bits as specified by DES. Represents eight 8 bit letters in UTF_8
 */
public class Block {
    private BitSet block;
    public Block(BitSet b) {
        if (b.size() != 64) {
            throw new IllegalArgumentException("Blocks are 64 bits each");
        }
        block = b;
    }
    public BitSet getBlock() {
        return block;
    }
}
