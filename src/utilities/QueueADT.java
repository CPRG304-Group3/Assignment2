package utilities;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * The QueueADT interface is a customized queue collection data type.
 * The implementors of this interface will be required to add all the functionality.
 *
 * @param <E> the type of elements held in this queue
 */
public interface QueueADT<E> extends Serializable {

    /**
     * This method is used to add the element to the rear of the queue.
     *
     * @param item the element to be added to the rear of the queue
     */
    void enqueue(E item);

    /**
     * This method removes the element at the front of the queue.
     *
     * @return the element that was removed
     */
    E dequeue();

    /**
     * This method returns (without removing) the element at the front of the queue.
     *
     * @return the element at the front of the queue
     */
    E peek();

    /**
     * This method checks if this queue is equal to another queue, by comparing all elements in order.
     *
     * @param other the other queue being compared against
     * @return true if queues are identical
     */
    boolean equals(QueueADT<E> other);

    /**
     * This method returns an iterator over the elements in the queue in proper FIFO sequence.
     *
     * @return an iterator over the elements in the queue in FIFO order
     */
    Iterator<E> iterator();

    /**
     * This method translates the queue into an array.
     *
     * @return the queue as an array of objects
     */
    Object[] toArray();

    /**
     * This method copies the queue into the given array in correct FIFO order.
     * If the array is too small, a new array is created. If it's too large, it is trimmed.
     *
     * @param copy the original array to be copied into
     * @return the new array containing all queue elements in correct order
     */
    E[] toArray(E[] copy);

    /**
     * This method searches for an element in the queue and returns the index of the first match.
     *
     * @param item the element to be searched for
     * @return the index if found; otherwise -1
     */
    int search(E item);

    /**
     * This method checks if the queue contains the specified element.
     *
     * @param item the element to check for
     * @return true if the element is in the queue
     */
    boolean contains(E item);

    /**
     * This method returns the number of elements currently in the queue.
     *
     * @return the current size of the queue
     */
    int size();

    /**
     * This method checks whether the queue is empty.
     *
     * @return true if the queue contains no elements
     */
    boolean isEmpty();

    /**
     * This method removes all elements from the queue.
     */
    void clear();
}
