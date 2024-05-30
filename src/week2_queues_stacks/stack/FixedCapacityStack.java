package week2_queues_stacks.stack;

import java.util.Iterator;

public class FixedCapacityStack<T> implements Iterable<T> {
    private T[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push (T item) {
        s[N++] = item;
    }

    public T pop() {
        return s[--N];
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return s[--i];
        }
    }
}
