import TreePackage.*;
import java.util.Iterator;

/**
 * A sorted list implementation using a Binary Search Tree as the underlying structure.
 * The BST maintains entries in sorted order automatically.
 * 
 * @author [Haaziq Abbasi]
 */

public class BSTSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {
    
    private BinarySearchTree<T> bst;
    private int numOfEntries;
    
    /**
     * Constructor - initializes an empty sorted list
     */
    public BSTSortedList() {
        bst = new BinarySearchTree<>();
        numOfEntries = 0;
    }
    
    /**
     * Adds a new entry to the sorted list in its proper sorted position.
     * @param anEntry The entry to add
     */
    @Override
    public void add(T anEntry) {
        bst.add(anEntry);
        numOfEntries++;
    }
    
    /**
     * Removes a specific entry from the sorted list.
     * @param anEntry The entry to remove
     * @return true if the entry was found and removed, false otherwise
     */
    @Override
    public boolean remove(T anEntry) {
        T removed = bst.remove(anEntry);
        if (removed != null) {
            numOfEntries--;
            return true;
        }
        return false;
    }
    
    /**
     * Removes the entry at a given position in the sorted list.
     * @param givenPosition The position (1-based) of the entry to remove
     * @return The entry that was removed
     * @throws IndexOutOfBoundsException if position is invalid
     */
    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numOfEntries) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        
        // Use inorder iterator to find the entry at the given position
        T entryToRemove = getEntry(givenPosition);
        
        // Remove the entry using the BST's remove method
        bst.remove(entryToRemove);
        numOfEntries--;
        
        return entryToRemove;
    }
    
    /**
     * Gets the position of a given entry in the sorted list.
     * @param anEntry The entry to find
     * @return Positive position if found, negative position where it would be if not found
     */
    @Override
    public int getPosition(T anEntry) {
        int pos = 1;
        Iterator<T> iter = bst.getInorderIterator();
        
        while (iter.hasNext()) {
            T currentEnt = iter.next();
            int comp = anEntry.compareTo(currentEnt);
            
            if (comp == 0) {
                // Found the entry - return positive position
                return pos;
            } else if (comp < 0) {
                // anEntry would come before currentEntry
                // Return negative position where it would be inserted
                return -pos;
            }
            pos++;
        }
        
        // anEntry would go at the end
        return -(pos);
    }
    
    /**
     * Gets the entry at a given position in the sorted list.
     * @param givenPosition The position (1-based) to retrieve
     * @return The entry at that position
     * @throws IndexOutOfBoundsException if position is invalid
     */
    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numOfEntries) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        
        // Use inorder iterator to traverse to the position
        Iterator<T> iter = bst.getInorderIterator();
        T entry = null;
        
        for (int i = 1; i <= givenPosition; i++) {
            entry = iter.next();
        }
        
        return entry;
    }
    
    /**
     * Checks if the sorted list contains a given entry.
     * @param anEntry The entry to search for
     * @return true if found, false otherwise
     */
    @Override
    public boolean contains(T anEntry) {
        return bst.contains(anEntry);
    }
    
    /**
     * Gets the number of entries in the sorted list.
     * @return The number of entries
     */
    @Override
    public int getLength() {
        return numOfEntries;
    }
    
    /**
     * Checks if the sorted list is empty.
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }
    
    /**
     * Removes all entries from the sorted list.
     */
    @Override
    public void clear() {
        bst.clear();
        numOfEntries = 0;
    }
    
    /**
     * Converts the sorted list to an array in sorted order.
     * @return An array containing all entries in sorted order
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numOfEntries];
        
        Iterator<T> iterator = bst.getInorderIterator();
        int i = 0;
        
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        
        return result;
    }
}