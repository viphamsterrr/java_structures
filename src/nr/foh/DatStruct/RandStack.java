package nr.foh.DatStruct;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Declares stack with taking a random value instead of the last one as in clasic. This structure allows to
 * add elements to the end of it and remove them from randomly choosen place. Allows random order iterations
 * and get information about total current capacity. Getting values are "fatal" - once its taken the value gets
 * out of the stack. Null arguments are allowed.
 */
public class RandStack<T> implements Iterable<T> {

    private T[] items;
    private int size;

    /**
     * Creates an empty stack.
     */
    public RandStack() {
        items = (T[]) new Object[16];
        size = 0;
    }

    /**
     * Creates a stack of specific type and initialize it with specific element.
     *
     * @param thing object of previously defined type for this queue.
     */
    public RandStack(T thing) {
        items = (T[]) new Object[16];
        items[0] = thing;
        size = 1;
    }

    /**
     * Checks against any element is present in stack.
     *
     * @return true if stack has no any element, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns current amount of elements in stack.
     *
     * @return size of structure as integer.
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds specific element in the beginning of stack.
     *
     * @param thing object of previously defined type for this stack.
     */
    public void put(T thing) {
        size++;
        if (size == items.length) {
            refactor(items.length * 2);
        }
        items[size - 1] = thing;
    }

    private void refactor(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        System.arraycopy(items, 0, newArr, 0, size);
        items = newArr;
    }

    /**
     * Returns random element in stack and removes it.
     *
     * @return random element in stack.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T take() {
        if (isEmpty()) {
            emptyQueue();
        }
        int toTake = getRnd(size);
        T res = items[toTake];
        if (toTake != size - 1) {
            items[toTake] = items[size - 1];
        }
        size--;
        if (size < items.length / 4) {
            refactor(items.length / 2);
        }
        return res;
    }

    /**
     * Returns the value of a random element in stack. The element remains inside stack.
     *
     * @return random element in stack.
     * @throws NoSuchElementException when no elements present in object.
     */
    public T pick() {
        if (isEmpty()) {
            emptyQueue();
        }
        return items[getRnd(size)];
    }

    /**
     * Checks against this element present in stack.
     *
     * @return true if specific element is found in stack, false otherwise.
     */
    public boolean contains(T thing) {
        if (isEmpty()) return false;
        boolean f = false;
        for (int i = 0; i < size; i++) {
            if (items[i] == thing) {
                f = true;
                break;
            }
        }
        return f;
    }

    /**
     * removes null values (if present) from randomized stack
     *
     * @return number of null values removed
     */
    public int pack() {
        int i = 0;
        int cntr = 0;
        while (i < size) {
            if (items[i] == null) {
                items[i] = items[size];
                size--;
                cntr++;
            }
            else{i++;}
        }
        return cntr;
    }

    /**
     * Counts number of specified values at current randomized stack
     *
     * @param T thing - value to be found
     * @return number of entries of specified value
     */
    public int count(T thing) {
        if (isEmpty()) return 0;
        int cntr = 0;
        for (T item : items) {if (item == thing) {cntr++;}}
        return cntr;
    }

    private void emptyQueue() {
        throw new java.util.NoSuchElementException();
    }

    private int getRnd(int max) {
        Random rnd = new Random();
        return rnd.ints(0, max).findFirst().getAsInt();
    }

    public int[] getPermutation(int size) {
        int[] res = new int[size];
        int[] hlpr = new int[size];
        int idx;
        for (int i = 0; i < size; i++) {hlpr[i] = i;}
        for (int i = 0; i < size; i++) {
            idx = getRnd(size - i);
            res[i] = hlpr[idx];
            if (idx != size - i - 1) {hlpr[idx] = hlpr[size - i - 1];}
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<T> {
        private final int[] orderGlobal;
        private int order;

        private RQIterator() {
            orderGlobal = getPermutation(size);
            order = 0;
        }

        @Override
        public boolean hasNext() {
            return !(order == size);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                emptyQueue();
            }
            return items[orderGlobal[order++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
