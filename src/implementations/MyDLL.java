package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void clear() {

        MyDLLNode<E> current = head;
        while (current != null) {
            current.prev = null;
            current.data = null;
            current = current.next;
        }
        this.size = 0;
    }

    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {

        if (index < 0 ||  index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (toAdd == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.next = this.head;
            if (head != null) {
                this.head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == this.size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            MyDLLNode<E> previous = current.prev;

            newNode.next = current;
            newNode.prev = previous;
            previous.next = newNode;
            current.prev = newNode;
        }

        this.size++;
        return true;
    }

    public boolean add( E toAdd ) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }


        this.size++;
        return true;
    }

    public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            this.add(it.next());
        }

        return true;
    }

    public E get( int index ) throws IndexOutOfBoundsException {
        if  (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 0;
            MyDLLNode<E> current = head;

            while (counter < index) {
                current = current.next;
                counter++;
            }
            return current.data;
        }
    }

    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<E> toRemove;

        if (index == 0) {
            toRemove = this.head;
            this.head = this.head.next;
            if (this.head != null) {
                this.head.prev = null;
            } else {
                this.tail = null;
            }
        } else if (index == this.size - 1) {
            toRemove = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
        } else {
            toRemove = this.head;
            for (int i = 0; i < index; i++) {
                toRemove = toRemove.next;
            }
            MyDLLNode<E> previous = toRemove.prev;
            MyDLLNode<E> next = toRemove.next;

            previous.next = next;
            next.prev = previous;
        }

        this.size--;
        return toRemove.data;
    }

    public E remove( E toRemove ) throws NullPointerException {
        if  (toRemove == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            return null;
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data == toRemove) {
                if (current == this.head) {
                    this.head = this.head.next;
                    if (this.head != null) {
                        this.head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == this.tail) {
                    this.tail = this.tail.prev;
                    this.tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                this.size--;
                return toRemove;
            }
            current = current.next;
        }

        return null;
    }

    public E set( int index, E toChange ) throws NullPointerException, IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else if (toChange == null) {
            throw new NullPointerException();
        } else {
            int counter = 0;
            MyDLLNode<E> current = head;

            while (counter < index) {
                current = current.next;
                counter++;
            }

            E data = current.data;
            current.data = toChange;

            return data;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains( E toFind ) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }


        int counter = 0;
        MyDLLNode<E> current = head;

        while (counter < this.size) {
            if (current.data == toFind) {
                return true;
            }
            counter++;
            current = current.next;
        }

        return false;
    }

    public E[] toArray( E[] toHold ) throws NullPointerException {
        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }

        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            toHold[index++] = current.data;
            current = current.next;
        }

        if (toHold.length > size) {
            toHold[size] = null; // Per Java Collection API behavior
        }

        return toHold;

    }

    public Object[] toArray() {
        Object[] newArray = new Object[this.size];
        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            newArray[index++] = current.data;
            current = current.next;
        }

        return newArray;

    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
