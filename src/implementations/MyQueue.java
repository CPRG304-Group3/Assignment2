package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

import java.util.NoSuchElementException;

public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> elements;
    private int size;

    public MyQueue() {
        this.elements = new MyDLL<>();
        this.size = 0;
    }

    public void enqueue( E toAdd ) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        elements.add(toAdd);
        this.size++;
    }

    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        E removed = elements.remove(0);
        this.size--;
        return removed;
    }

    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.get(0);
    }

    public void dequeueAll() {
        elements.clear();
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(E toFind) throws NullPointerException {
        return elements.contains(toFind);
    }

    public int search(E toFind) {
        for  (int i = 0; i < this.size; i++) {
            if (elements.get(i) == (toFind)) {
                return i + 1;
            }
        }

        return -1;
    }

    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() throws NoSuchElementException {
                if  (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return elements.get(index++);
            }
        };

    }

    public boolean equals(QueueADT<E> that) {
        if (this.size != that.size()) {
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

    public Object[] toArray() {
        Object[] newArray = new Object[this.size];

        for (int i = 0; i < this.size; i++) {
            newArray[i] = elements.get(i);
        }

        return newArray;
    }

    public E[] toArray(E[] holder) throws NullPointerException {
        if  (holder == null) {
            throw new NullPointerException();
        }

        if (holder.length < this.size) {
            holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
            for (int i = 0; i < this.size ; i++) {
                holder[i] = elements.get(i);
            }
            return holder;
        }

        for (int i = 0; i < this.size ; i++) {
            holder[i] = elements.get(i);
        }

        if (holder.length > size) {
            holder[size] = null;
        }
        return holder;
    }


    public boolean isFull() {
        if (this.size <= this.elements.size()) {
            return false;
        }
        return true;
    }


    public int size() {
        return this.size;
    }
}
