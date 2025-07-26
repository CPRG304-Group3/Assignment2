package implementation;

import implementations.MyArrayList;
import utilities.StackADT;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.io.Serializable;

public class MyStack<E> implements StackADT<E>, Serializable {

    private static final long serialVersionUID = 1L;
    private MyArrayList<E> data;

    public MyStack() {
        data = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot push null to the stack.");
        data.add(toAdd);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        return data.remove(data.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        return data.get(data.size() - 1);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return data.toArray(holder);
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return data.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        if (toFind == null)
            return -1;
        for (int i = data.size() - 1, pos = 1; i >= 0; i--, pos++) {
            if (toFind.equals(data.get(i)))
                return pos;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null || that.size() != this.size())
            return false;

        Iterator<E> thisIter = this.iterator();
        Iterator<E> thatIter = that.iterator();

        while (thisIter.hasNext()) {
            E thisItem = thisIter.next();
            E thatItem = thatIter.next();
            if (thisItem == null && thatItem != null || thisItem != null && !thisItem.equals(thatItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean stackOverflow() {
        // Since MyArrayList grows dynamically, overflow only if max capacity is reached
        return data.size() == Integer.MAX_VALUE;
    }
}
