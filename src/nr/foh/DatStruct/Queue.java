package nr.foh.DatStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Declares classic FIFO queue. This structure allows to add elements to the end of it and remove them from
 * the start. Also allows iterations through start to end and get information about total current capacity.
 * Getting values are "fatal" - once its taken the value gets out of the queue. Queues don't allow to operate
 * with null arguments, so it will produce an exception if you try to add null as item.
 */
public class Queue<T> implements Iterable<T>{
    private Node first;
    private Node last;
    private static long size = 0;

    private class Node{
        T item;
        Node next;
    }

    /**
     * Creates an empty queue.
     */
    public Queue() {
        first = null;
    }

    /**
     * Creates a new queue of specified type and adds first element to it.
     *
     * @param thing object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     *
     */
    public Queue(T thing) {
        if (thing == null)  {throw new IllegalArgumentException("Null values are not allowed to add in this structure.");}
        first = new Node();
        first.item = thing;
        first.next = null;
        last = first;
        size = 1;
    }

    /**
     * Returns first elements in queue and removes it.
     *
     * @return first element in stack.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T take() {
        if (isEmpty()) {throw new NoSuchElementException("Structure is already empty, unable to take more items.");}
        T thing = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        size--;
        return thing;
    }

    /**
     * Adds specific element at the end of queue.
     *
     * @param thing object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public void put(T thing) {
        if (thing == null) {throw new IllegalArgumentException("Null arguments not allowed to be added in this structure");}
        Node oldLast = last;
        last = new Node();
        last.item = thing;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    /**
     * Checks against any element is present in queue.
     *
     * @return true if stack has no any element, false otherwise.
     */
    public boolean isEmpty() {return first == null;}

    /**
     * Returns current amount of elements in stack.
     *
     * @return size of structure as longinteger.
     */
    public long getSize() {
        return size;
    }

    /**
     * Checks against this element present in queue.
     *
     * @return true if specific element is found in stack, false otherwise.
     */
    public boolean contains(T thing) {
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
     * Counts number of specified values at current queue
     *
     * @param thing - value to be found
     * @return number of entries of specified value
     */
    public int count(T thing) {
        int cntr = 0;
        for (T itr : this) {if (itr == thing) {cntr++;}}
        return cntr;
    }

    @Override
    public Iterator<T> iterator() {return new QuIter();}
    private class QuIter implements Iterator<T>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {throw new NoSuchElementException("Iterator is empty.");}
            T thing = current.item;
            current = current.next;
            return thing;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator is not allowed to remove elements");
        }
    }
}
