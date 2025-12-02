package StackAndQueuePackage;


/**
 * AraryQueue.java - circular array based implementation of Queue ADT
 */
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity)
    {
        integrityOK = false;
        checkCapacity(capacity);

        T[] tempQueue = (T[]) new Object[capacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = capacity;
        integrityOK = true;
    }

    //helper method to ensure we are not trying to create an array that is too large
    private void checkCapacity(int capacity)
    {
        if (capacity >= MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create bag whose capacity exceeds max allowed value.");

    }

    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }

    /** Adds a new entry to the back of this queue.
    @param newEntry  An object to be added. */
    public void enqueue(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();       //will resize array if it is full
        backIndex = (backIndex + 1) % queue.length;  //mod will allow us to wrap around
        queue[backIndex] = newEntry;
    }

    //ensureCapacity() - doubles the size of the array queue if it is full
    //  precondition - checkIntegrity() has been called
    private void ensureCapacity()
    {
        if (frontIndex == ((backIndex + 2) % queue.length)) //if array is full..
        {
            //save the old queue
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            
            //check we can double the size
            checkCapacity(newSize);
            integrityOK = false;    //set flag to false in case we fail
            
            T[] tempQueue = (T[])new Object[newSize];
            queue = tempQueue;
            
            //copy into the new, larger array
            for (int index = 0; index < oldSize - 1; index++)
            {
                queue[index] = oldQueue[frontIndex];        //copy into the new array
                frontIndex = (frontIndex + 1) % oldSize;    //update frontIndex (with wrapping)
            }
            
            
            frontIndex = 0;
            backIndex = oldSize - 2;
            integrityOK = true;
        }
    }
    
    /** Removes and returns the entry at the front of this queue.
    @return  The object at the front of the queue. 
    @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        checkIntegrity();
        
        T frontValue = getFront();
        
        queue[frontIndex] = null;   //delete the entry 
        frontIndex = (frontIndex + 1) % queue.length;
        
        return frontValue;
    }

    /**  Retrieves the entry at the front of this queue.
    @return  The object at the front of the queue.
    @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        checkIntegrity();
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return queue[frontIndex];    
    }

    /** Detects whether this queue is empty.
    @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        checkIntegrity();
        return (frontIndex == ((backIndex + 1) % queue.length));  //if empty, the two values are adjacent
    }

    /** Removes all entries from this queue. */
    public void clear()
    {
        while(!isEmpty())
            dequeue();
    }

}
