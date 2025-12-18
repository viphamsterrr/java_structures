package nr.foh.DatStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Declares classic LIFO stack of object of specific type, implemented by array. This structure allows to add
 * and remove elements to the beginning of it. Also allows iterations through start to end and getting
 * information about total current capacity. Getting values are "fatal" - once its taken the value gets out of
 * the stack. There is a function which allow to simply check the presence of specific element in it and to find
 * total number of presence of it. Null arguments are available.
 */

public class AStack <T> implements Iterable<T>{
    private T[] things;
    private static int size = 0;

/**
 * Creates an empty stack.
 */
    public AStack() {
        things = (T[]) new Object[16];
        size = 0;
    }

/**
 * Creates a stack of specific type and initialize it with specific element.
 *
 * @param thing object of previously defined type for this queue.
 */
    public AStack(T thing) {
        things = (T[]) new Object[16];
        size = 1;
        things[0] = thing;
    }

    /**
     * Returns current amount of elements in stack.
     *
     * @return size of structure as integer.
     */
    public long getSize(){return size;}

    /**
     * Checks against any element is present in stack.
     *
     * @return true if stack has no any element, false otherwise.
     */
    public boolean isEmpty() {return size == 0;}

    /**
     * Adds specific element in the beginning of stack.
     *
     * @param thing object of previously defined type for this stack.
     * @throws IllegalArgumentException when attempting to add a null value.
     */
    public void put(T thing) {
        size++;
        if (size >= things.length) {refactor(things.length * 2);}
        things[size - 1] = thing;
    }

    /**
     * Returns first element in stack and removes it.
     *
     * @return first element in stack.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T take() {
        if (isEmpty()) {throw new NoSuchElementException("Structure is already empty, unable to take more items.");}
        T thing = things[size - 1];
        size--;
        if (size <= things.length / 4) {refactor(things.length / 2);}
        return thing;
    }

    /**
     * Checks against this element present in stack.
     *
     * @return true if specific element is found in stack, false otherwise.
     */
    public boolean contains(T thing) {
        boolean f = false;
        for (int i = 0; i < size; i++){
            if (things[i] == thing) {
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
        for (int i = 0; i < size; i++) {if (things[i] == thing) {cntr++;}}
        return cntr;
    }

    private void refactor(int leng) {
        T[] intmd = (T[]) new Object[leng];
        System.arraycopy(things, 0, intmd, 0, size);
        things = intmd;
    }

@Override
    public Iterator<T> iterator() {return new ASIter();}

    private class ASIter implements Iterator<T>{
        private int idx = size - 1;

        @Override
        public boolean hasNext() {
            return idx >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {throw new NoSuchElementException("Iterator is empty.");}
            return things[idx--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator is not allowed to remove elements");
        }
    }
}

