package implementations;

import utilities.ListADT;
import utilities.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<E> implements ListADT<E> {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        Objects.requireNonNull(toAdd, "Element cannot be null");

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        ensureCapacity();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        Objects.requireNonNull(toAdd, "Element cannot be null");

        ensureCapacity();
        elements[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        Objects.requireNonNull(toAdd, "List to add cannot be null");

        Iterator<? extends E> it = toAdd.iterator();
        boolean changed = false;

        while (it.hasNext()) {
            add(it.next());
            changed = true;
        }

        return changed;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return elements[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        E removed = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        Objects.requireNonNull(toRemove, "Element to remove cannot be null");

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i);
            }
        }

        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        Objects.requireNonNull(toChange, "Element cannot be null");

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        E old = elements[index];
        elements[index] = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        Objects.requireNonNull(toFind, "Element to find cannot be null");

        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        Objects.requireNonNull(toHold, "Destination array cannot be null");

        if (toHold.length < size) {
            return (E[]) java.util.Arrays.copyOf(elements, size, toHold.getClass());
        }

        System.arraycopy(elements, 0, toHold, 0, size);

        if (toHold.length > size) {
            toHold[size] = null;
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(elements, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    // Internal helper methods

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            E[] newArray = (E[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    // Inner class for Iterator<E>
    private class MyArrayListIterator implements Iterator<E> {
        private int current = 0;
        private final E[] snapshot;

        @SuppressWarnings("unchecked")
        public MyArrayListIterator() {
            snapshot = (E[]) new Object[size];
            for (int i = 0; i < size; i++) {
                snapshot[i] = elements[i]; // Deep copy by value (shallow for non-primitives)
            }
        }

        @Override
        public boolean hasNext() {
            return current < snapshot.length;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            return snapshot[current++];
        }
    }
}
