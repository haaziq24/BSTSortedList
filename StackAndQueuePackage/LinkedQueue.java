package StackAndQueuePackage;


/**
 * LinkedQueue.java - Linked list implementation of Queue ADT
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode;
    private Node lastNode;
    
    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    }

    private class Node
    {
        private T data;
        private Node next;
        
        public Node(T theData)
        {
            data = theData;
            next = null;
        }
    }
    
    /** Adds a new entry to the back of this queue.
    @param newEntry  An object to be added. */
    public void enqueue(T newEntry)
    {
        //create new Node
        Node newNode = new Node(newEntry);
        
        //if queue is empty our new Node is the front Node...
        if (isEmpty())
            firstNode = newNode;
        else  //else we attach to after the back Node
            lastNode.next = newNode;
            
        //either way, we have a new back Node
        lastNode = newNode;
    }

    /** Removes and returns the entry at the front of this queue.
    @return  The object at the front of the queue. 
    @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        T frontValue = getFront();  //get the value (and check for empty Queue)
        
        firstNode = firstNode.next; //cut out the front node
        
        //if we removed the last Node...
        if (firstNode == null)
            lastNode = null;
            
        return frontValue;
    }

    /**  Retrieves the entry at the front of this queue.
    @return  The object at the front of the queue.
    @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return firstNode.data;
    }

    /** Detects whether this queue is empty.
    @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    }

    /** Removes all entries from this queue. */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
    }
}
