package com.harrycodeman.compression.rle;

import java.util.Iterator;
import java.util.Stack;

public abstract class BytesBlock implements Iterable<Integer> {
    private final int MAX_LENGTH = 128;

    public void addSymbol(int s) throws Exception {
        if (isBlockFull()) {
            throw new Exception("Block is already full!");
        }
        if (!isSymbolSuitableForBlock(s)) {
            throw new Exception("Incorrect symbol adding to block!");
        }
        addSymbolOverridden(s);
    }

    public boolean isBlockFull() {
        return size() == MAX_LENGTH;
    }

    public abstract int size();
    public abstract boolean isSymbolSuitableForBlock(int s);
    protected abstract void addSymbolOverridden(int s);

    public void displaceUnsuitableSymbols(Stack<Integer> stack) {
    }

    protected boolean isBlockOverflowed() {
        return size() > MAX_LENGTH;
    }

    public abstract Iterator<Integer> compressedIterator();
}
