package week2_queues_stacks;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue = (Item[]) new Object[1];
    private int size = 0;


    // construct an empty randomized queue
    public RandomizedQueue() {}

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        exception1(item);
        queue[size++] = item;
        if (size == queue.length) {
            resize(queue.length * 2);
        }
    }

    private void resize(int capacity) {
        Item[] oldQueue = queue;
        queue = (Item[]) new Object[capacity];
        if (oldQueue.length > queue.length) {
            System.arraycopy(oldQueue, 0, queue, 0, queue.length);
        } else {
            System.arraycopy(oldQueue, 0, queue, 0, oldQueue.length);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        exception2();
        int rand = StdRandom.uniform(size);
        Item obj = queue[rand];
        queue[rand] = queue[size-1];
        queue[size--] = null;
        if (size > 0 && size < queue.length/4) {
            resize(queue.length/2);
        }
        return obj;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        exception2();
        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = size;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int y = 0; y < i; y++) {
                order[y] = y;
            }
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            exception2();
            if (hasNext()) {
                return queue[order[--i]];
            }
            return null;
        }

        @Override
        public void remove() {
            exception4();
        }
    }

    private void exception1(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    //same as exception3
    private void exception2() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private void exception4() {
        throw new UnsupportedOperationException();
    }

    // unit testing (required)
    public static void main(String[] args) {
/*        RandomizedQueue<String> queue = new RandomizedQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        for (String it: queue) {
            System.out.println(it);
        }
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        for (String it: queue) {
            System.out.println(it);
        }*/
    }

}