package org.homework;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator<T> implements Iterator<T> {
    private T[] array;
    private int currentIndex;

    public CustomIterator(T[] array) {
        this.array = array;
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(String.format("Элемент в массиве по индексу %d отсутствует", currentIndex));
        }
        return array[currentIndex++];
    }
}
