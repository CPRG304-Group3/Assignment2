package utilities;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * The StackADT interface is a customized stack collection data type.
 * The implementors of this interface will be required to add all the functionality.
 * @param <E>
 */
public interface StackADT<E> extends Serializable {

    /**
     * This method is used to push (add) the element to the end of the stack
     *
     * @param item is the element to be added to the stack
     */
    void push(E item);

    /**
     * This method removes the element at the end of the stack
     *
     * @return the element that was removed
     */
    E pop();

    /**
     * This method checks to see the element at the end of the stack
     *
     * @return the element viewed
     */
    E peek();

    /**
     * This method compares one stack against another to check whether they
     * are identical in terms of elements and their stored order
     *
     * @param other is the other stack being compared against
     * @return true if stacks are identical
     */
    boolean equals(StackADT<E> other);

    /**
     * This method returns an iterator over the elements in the stack in
     * proper sequence
     *
     * @return an iterator over the elements in the stack in proper sequence
     */
    Iterator<E> iterator();

    /**
     * This method translates the stack into an array
     *
     * @return the stack as an array of objects
     */
    Object[] toArray();

    /**
     * This method accepts an array and creates an array based on the stack type
     * containing all stack elements in correct LIFO order. Removes extra space from
     * given array for the new array or adds more space if necessary.
     *
     * @param copy is the original array to be copied from
     * @return the new array of stack type
     */
    E[] toArray(E[] copy);

    /**
     * This method searches for an element in the stack and returns the index of the
     * first instance of the element if found
     *
     * @param item is the element to be searched for
     * @return a non-negative value if found, otherwise returns -1
     */
    int search(E item);

    /**
     * This method checks to see if the element passed exists in the stack.
     *
     * @param item is the element we are looking for
     * @return true if the element does exist
     */
    boolean contains(E item);

    /**
     * This method returns the size of the stack
     *
     * @return the current size
     */
    int size();

    /**
     * This method checks to see if the stack is empty
     *
     * @return true if the stack has no elements in it
     */
    boolean isEmpty();

    /**
     * This method empties out the stack
     */
    void clear();

}
