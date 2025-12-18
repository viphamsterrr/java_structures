package nr.foh.DatStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Declares classic LIFO stack of object of specific type, implemented by linked list. This structure allows
 * to add and remove elements to the beginning of it. Also allows iterations through start to end and getting
 * information about total current capacity. Getting values are "fatal" - once its taken the value gets out of
 * the stack. There is a function which allow to simply check the presence of specific element in it. Stacks
 * don't allow to operate with null arguments, so it will produce an exception if you try to add null as item.
 */
public class Stack <T> implements Iterable<T>{
    private Node first;
    private static long size = 0;

    /**
     * Creates an empty stack.
     */
    public Stack() {
        first = null;
    }

    /**
     * Creates a stack of specific type and initialize it with specific element.
     *
     * @param thing object of previously defined type for this queue.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public Stack(T thing) {
        if (thing == null) {throw new IllegalArgumentException("Null values are not allowed to be added in stack.");}
        first = new Node();
        first.next = null;
        first.item = thing;
        size = 1;
    }

    private class Node {
        T item;
        Node next;
    }

    /**
     * Returns current amount of elements in stack.
     *
     * @return size of structure as longinteger.
     */
    public long getSize(){return size;}

    /**
     * Checks against any element is present in stack.
     *
     * @return true if stack has no any element, false otherwise.
     */
    public boolean isEmpty() {return first == null;}

    /**
     * Adds specific element in the beginning of stack.
     *
     * @param thing object of previously defined type for this stack.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public void put(T thing){
        if (thing == null) {throw new IllegalArgumentException("Null arguments not allowed to be added in this structure.");}
        Node old = first;
        first = new Node();
        first.item = thing;
        first.next = old;
        size++;
    }

    /**
     * Returns first element in stack and removes it.
     *
     * @return first element in stack.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T take() {
        if (isEmpty()) {throw new NoSuchElementException("Structure is already empty, unable to take more items.");}
        T thing = first.item;
        first = first.next;
        size--;
        return thing;
    }

    /**
     * Checks against this element present in stack.
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
    public Iterator<T> iterator() {return new StIter();}
    private class StIter implements Iterator<T>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()){throw new NoSuchElementException("Iterator is empty.");}
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
