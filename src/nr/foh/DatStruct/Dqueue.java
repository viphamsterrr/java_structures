package nr.foh.DatStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Declares double-edged queue. This structure allows to add and remove elements to both start and end
 * of it. Words "start" and "end" are just abstract in this structure, because both sides can be considered
 * as start and end relatively. Also allows iterations through start to end, get information about total
 * current capacity and getting elements from both size of it. Getting values are "fatal" - once its taken
 * the value gets out of the queue. Queues don't allow to operate with null arguments, so it will produce an
 * exception if you try to add null as item.
 */

public class Dqueue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private static long size = 0;

    private class Node {
        T item;
        Node next;
        Node prev;
    }

    /**
     * Creates an empty double-edged queue.
     */
    public Dqueue() {
        first = null;
    }

    /**
     * Creates a double-edged queue of specific type and initialize it with specific element.
     *
     * @param item object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public Dqueue(T item) {
        if (item == null) {throw new IllegalArgumentException("Null values are not allowed to be added in this structure.");}
        first = new Node();
        this.first.item = item;
        this.first.next = null;
        this.first.prev = null;
        last = first;
        size = 1;
    }

    /**
     * Adds specific element in the beginning of double-edged queue.
     *
     * @param item object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public void putFirst(T item) {
        if (item == null) {throw new IllegalArgumentException("Null arguments not allowed to be added in this structure");}
        if (size == 0) {
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = null;
            last = first;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            oldfirst.prev = first;
            first.next = oldfirst;
            first.item = item;
        }
        size++;
    }

    /**
     * Adds specific element in the end of double-edged queue.
     *
     * @param item object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public void putLast(T item) {
        if (item == null) {throw new IllegalArgumentException("Null arguments not allowed to be added in this structure");}
        if (size == 0) {
            last = new Node();
            last.next = null;
            last.item = item;
            last.prev = null;
            first = last;
        }
        else {
            Node oldlast = last;
            last = new Node();
            oldlast.next = last;
            last.prev = oldlast;
            last.item = item;
        }
        size++;
    }

    /**
     * Returns first elements in double-edged queue and removes it.
     *
     * @return first element in double-edged queue.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T takeFirst() {
        T ret = first.item;
        if (size > 1) {first = first.next;}
        else if (size == 1) {first.item = null;}
        else {throw new NoSuchElementException("Queue is empty");}
        first.prev = null;
        size--;
        return ret;
    }

    /**
     * Returns first elements in double-edged queue without deleting it.
     *
     * @return first element in double-edged queue. The element remains in queue.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T pickFirst() {
        if (isEmpty()) {throw new NoSuchElementException("Queue is empty");}
        return first.item;
    }

    /**
     * Returns last elements in double-edged queue and removes it.
     *
     * @return last element in double-edged queue.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T takeLast() {
        T ret = last.item;
        if (size > 1) {last = last.prev;}
        else if (size == 1) {last.item = null;}
        else {throw new NoSuchElementException("Queue is empty");}
        last.next = null;
        size--;
        return ret;
    }

    /**
     * Returns last elements in double-edged queue without deleting it.
     *
     * @return last element in double-edged queue. The element remains in queue.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T pickLast() {
        if (isEmpty()) {throw new NoSuchElementException("Queue is empty");}
        return last.item;
    }

    /**
     * Returns current amount of elements in double-edged queue.
     *
     * @return size of structure as longinteger.
     */
    public long getSize() {return size;}

    public boolean isEmpty() {
        return (first.item == null);
    }

    /**
     * Checks against this element present in double-edged queue.
     *
     * @return true if specific element is found in stack, false otherwise.
     */
    public boolean contains(T thing) {
        if (isEmpty()) return false;
        boolean f = false;
        for (T itr : this) {
            if (itr == thing) {
                f = true;
                break;
            }
        }
        return f;
    }

    /**
     * Counts number of specified values at current double-edged queue
     *
     * @param thing - value to be found
     * @return number of entries of specified value
     */
    public int count(T thing) {
        if (isEmpty()) return 0;
        int cntr = 0;
        for (T itr: this) {if (itr == thing) {cntr++;}}
        return cntr;
    }

@Override
    public Iterator<T> iterator() {return new DquIter();}

    private class DquIter implements Iterator<T> {
        private Node current = first;
        private long sz = size;

        @Override
        public boolean hasNext() {
            return sz > 0;
        }

        @Override
        public T next() {
            if (current.item == null) {throw new NoSuchElementException("Iterator is empty.");}
            T ret = current.item;
            current = current.next;
            sz--;
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException("Removing is not supported here.");
        }
    }
}