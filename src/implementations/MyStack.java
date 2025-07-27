package implementations;

import utilities.Iterator;
import utilities.StackADT;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> elements;
    private int top;

    public MyStack() {
        this.elements = new MyArrayList<>();
        this.top = -1;
    }

    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        top++;
        elements.add(toAdd);
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        E removed = elements.remove(top);
        this.top--;
        return removed;
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.elements.get(top);
    }

    public void clear() {
        elements.clear();

        this.top = -1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public Object[] toArray() {
        int size = top + 1;
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements.get(top - i);
        }

        return newArray;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException();
        }

        int size = top + 1;

        if (holder.length < size) {
            holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
            for (int i = 0; i < size ; i++) {
                holder[i] = elements.get(top - i);
            }
            return holder;
        }

        for (int i = 0; i < size ; i++) {
            holder[i] = elements.get(top - i);
        }
        if (holder.length > size) {
            holder[size] = null;
        }
        return holder;
    }

    public boolean contains( E toFind ) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i <= this.top; i++) {
            if (this.elements.get(i) == toFind) {
                return true;
            }
        }

        return false;
    }

    public int search( E toFind ) {
        int counter = this.top + 1;
        for (int i = 0; i <= this.top; i++) {
            if (this.elements.get(i) == toFind) {
                return counter - i;
            }
        }

        return -1;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0; // may change to -1

            @Override
            public boolean hasNext() {
                return counter <= top;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = elements.get(top - counter);
                counter++;
                return value;
            }
        };
    }

    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) {
            return false;
        }

        Iterator<E> thisIterate = this.iterator();
        Iterator<E> thatIterate = that.iterator();

        while (thisIterate.hasNext() && thatIterate.hasNext()) {
            E value1 = thisIterate.next();
            E value2 = thatIterate.next();
            if (value1 != value2) {
                return false;
            }
        }

        return true;
    }


    public int size() {
        return this.top + 1;
    }

    public boolean stackOverflow() {
        boolean overflow = false;
        for (int i = 0; i <= this.top; i++) {
            overflow = iterator().hasNext();
        }
        return overflow;
    }
}
