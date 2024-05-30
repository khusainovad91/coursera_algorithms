import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        exception1(item);
        if (size == 0)
            createFirstNode(item);
        if (size > 0) {
            Node oldNode = first;
            first = new Node(item);
            first.next = oldNode;
            oldNode.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        exception1(item);
        if (size == 0)
        createFirstNode(item);
        if (size > 0) {
            Node oldNode = last;
            last = new Node(item);
            last.prev = oldNode;
            oldNode.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        exception2();
        Node removedNode = first;
        if (size == 1) {
            first = last = null;
        }
        if (size > 1) {
            first = removedNode.next;
            first.prev = null;
        }
        size--;
        return removedNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        exception2();
        Node removedNode = last;
        if (size == 1) {
            first = last = null;
        }
        if (size > 1) {
            last = removedNode.prev;
            last.next = null;
        }
        size--;
        return removedNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            exception3();
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            return null;
        }

        @Override
        public void remove() {
            exception4();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
/*
        Deque deq = new Deque();
        System.out.println(deq.isEmpty());
        deq.addFirst("1");
        System.out.println(deq.isEmpty());
        deq.addFirst("2");
        deq.addLast("3");
        deq.addFirst("4");
        deq.removeLast();
        deq.removeFirst();
        System.out.println(deq.size());

        for (Object it : deq) {
            System.out.println(it.toString());
        }
*/

    }

    private void createFirstNode(Item item) {
        Node firstNode = new Node(item);
        firstNode.item = item;
        first = last = firstNode;
    }

    private void exception1(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }
    private void exception2() {
        if (size == 0) throw new NoSuchElementException();
    }
    private void exception3() {
        if (first.next == null) throw new NoSuchElementException();
    }

    private void exception4() {
        throw new UnsupportedOperationException();
    }
}