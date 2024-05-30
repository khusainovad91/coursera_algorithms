package week2_queues_stacks;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueueFailed<Item> implements Iterable<Item> {
    int size = 0;
    Node first;

    private class Node {
        Item item;
        Node next;

        private Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueueFailed() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        exception1(item);
        if (size == 0) {
            first = new Node(item);
        }
        else {
            Node oldNode = first;
            first = new Node(item);
            first.next = oldNode;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        int randomInt = StdRandom.uniform(size);
        if (size == 1) {
            Node oldNode = first;
            first = null;
            size--;
            return oldNode.item;
        } else {
            Node prePointer = first;
            Node pointer = first;
            while (randomInt-- != 0) {
                pointer = pointer.next;
                if(randomInt == 1) {
                    prePointer = pointer;
                }
            }
            if(pointer.next != null) {
                prePointer.next = pointer.next;
            }
            size--;
            return pointer.item;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randomInt = StdRandom.uniformInt(0, size);
        Node pointer = first;
        while (randomInt-- != 0) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Node pointer = first;

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public Item next() {
            exception3();
            if (hasNext()) {
                Item item = pointer.item;
                pointer = pointer.next;
                return item;
            }
            return null;
        }
    }

    private void exception1(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void exception2() {
        if (size == 0) throw new NoSuchElementException();
    }

    private void exception3() {

    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueueFailed queue = new RandomizedQueueFailed();
        System.out.println(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.isEmpty());
        System.out.println(queue.sample());
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.isEmpty());
        System.out.println("Стек");
        for (Object it: queue) {
            System.out.println(it);
        }
    }

}
