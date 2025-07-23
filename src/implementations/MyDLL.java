package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.sql.Array;

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

        if (index < 0 ||  index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else if (toAdd == null) {
            throw new NullPointerException();
        } else {
            if (!isEmpty()) {
                MyDLLNode<E> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                MyDLLNode<E> temp = current; // Reference or copy?
                current.prev = temp.prev;
                current.data = toAdd;
                current.next = temp;
            } else {
                this.head = this.tail = new MyDLLNode<>(toAdd);
            }
        }

        this.size++;
        return true;
    }

    public boolean add( E toAdd ) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        } else if (!isEmpty()) {
            int counter = 0;
            MyDLLNode<E> current = head;
            while (counter < this.size) {
                current = current.next;
            }
            MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
            newNode.prev = current;
            current.next = newNode;
            this.tail =  newNode;
        } else {
            this.tail = this.head = new MyDLLNode<>(toAdd);
        }
        this.size++;
        return true;
    }

    public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
        int counter = 0;
        MyDLLNode<E> current = head;

        while (counter < this.size) {
            current = current.next;
        }

        for (int i = 0; i < toAdd.size(); i++) {
            if (toAdd.get(i) == null) {
                throw new NullPointerException();
            } else {
                MyDLLNode<E> newNode = new MyDLLNode<>(toAdd.get(i));
                newNode.prev = current;
                current.next = newNode;
            }
        }

        this.size += toAdd.size();
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

    public E remove( int index ) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 0;
            MyDLLNode<E> current = head;

            while (counter < index) {
                current = current.next;
                counter++;
            }

            E data = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current.data = null; // may be current = null;

            this.size--;
            return data;
        }
    }

    public E remove( E toRemove ) throws NullPointerException {
        if  (toRemove == null) {
            throw new NullPointerException();
        } else {
            int counter = 0;
            MyDLLNode<E> current = head;

            while (counter < this.size) {
                if (current.data ==  toRemove) {
                    E data = current.data;
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current.data = null;
                    return data;
                }
            }
            return null;
        }
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
        } else {
            int counter = 0;
            MyDLLNode<E> current = head;

            while (counter < this.size) {
                if (current.data == toFind) {
                    return true;
                }
            }
        }

        return false;
    }

    public E[] toArray( E[] toHold ) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        } else if (toHold.length < this.size) {
            toHold = (E[]) new Object[this.size];
            for (int i = 0; i < this.size; i++) {
                toHold[i] = this.get(i);
            }
        }

        return toHold;
    }

    public Object[] toArray() {
        Object[] newArray = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.get(i);
        }

        return newArray;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;

            @Override
            public boolean hasNext() {
                if (current.next != null) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                return current.next.data;
            }
        };
    }
}
