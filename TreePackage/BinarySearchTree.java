/**
 * BinarySearchTree.java - implementation of binary search tree
 */
package TreePackage;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>>
    extends BinaryTree<T> implements SearchTreeInterface<T>
{
    public BinarySearchTree()
    {
        super();
    } // end default constructor

    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    } // end constructor

    // Disable setTree 
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
            BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    //SearchTreeInterface methods
    
    /** Searches for a specific entry in this tree.
    @param anEntry  An object to be found.
    @return  True if the object was found in the tree. */
    public boolean contains(T anEntry)
    {
        return getEntry(anEntry) != null;
    }

    /** Retrieves a specific entry in this tree.
    @param anEntry  An object to be found.
    @return  Either the object that was found in the tree or
    null if no such object exists. */
    public T getEntry(T anEntry)
    {
        return findEntry(getRootNode(), anEntry);
    }
    
    //helper method to recursively find anEntry starting at given
    //  node following binary search tree algorithm
    private T findEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;
        
        if (rootNode != null)
        {
            //grab current entry
            T rootEntry = rootNode.getData();
            
            //we found it
            if (anEntry.equals(rootEntry))
                result = rootEntry;
            //anEntry is less than current entry
            else if (anEntry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(), anEntry);
            //anEntry is more than current entry
            else
                result = findEntry(rootNode.getRightChild(), anEntry);
        }    
        
        return result;
    }

    /** Adds a new entry to this tree, if it does not match an existing 
    object in the tree. Otherwise, replaces the existing object with
    the new entry.
    @param anEntry  An object to be added to the tree.
    @return  Either null if anEntry was not in the tree but has been added, or
    the existing entry that matched the parameter anEntry
    and has been replaced in the tree. */
    public T add(T anEntry)
    {
        T result = null;
        
        //if tree is empty, set as the root
        if (isEmpty())
            setRootNode(new BinaryNode<>(anEntry));
        //otherwise, call recursive method starting at root
        else
            result = addEntry(getRootNode(), anEntry);
            
        return result;
    }
    
    //helper method to recursively add to nonempty subtree
    //  rooted at given node
    private T addEntry(BinaryNode<T> rootNode, T anEntry)
    {
        //Assertion:  rootNode != null
        T result = null;
        int comparison = anEntry.compareTo(rootNode.getData());
        
        //if matches, replace the entry
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(anEntry);
        } else if (comparison < 0) {    //look left
            //If there is a left subtree, recurse left
            if (rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), anEntry);
            else    //We found where it goes
                rootNode.setLeftChild(new BinaryNode<T>(anEntry));
        } else {    //look right
            //If there is a right subtree, recurse right
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), anEntry);
            else    //We found where it goes
                rootNode.setRightChild(new BinaryNode<T>(anEntry));
        }
        
        return result;
    }
    
    //alternate iterative helper method to add to nonempty subtree
    //  rooted at given node
    private T addEntry(T anEntry)
    {
        BinaryNode<T> currentNode = getRootNode();
        T result = null;
        boolean found = false;
        
        while (!found)
        {
            T currentEntry = currentNode.getData();
            int comparison = anEntry.compareTo(currentEntry);
            
            //We have a match, so replace
            if (comparison == 0)
            {
                found = true;
                result = currentEntry;
                currentNode.setData(anEntry);
            } else if (comparison < 0) {
                if (currentNode.hasLeftChild())
                    currentNode = currentNode.getLeftChild();
                else
                {
                    found = true;
                    currentNode.setLeftChild(new BinaryNode<>(anEntry));
                }
            } else {
                if (currentNode.hasRightChild())
                    currentNode = currentNode.getRightChild();
                else
                {
                    found = true;
                    currentNode.setRightChild(new BinaryNode<>(anEntry));
                }
            }
        
        }
        return result;
    }

    /** Removes a specific entry from this tree.
    @param anEntry  An object to be removed.
    @return  Either the object that was removed from the tree or
    null if no such object exists. */
    public T remove(T anEntry)
    {
        T result = null;
        
        if (!isEmpty())
        {
            BinaryNode<T> currentNode = getRootNode();
            BinaryNode<T> parentNode = null;
            
            boolean found = false;
            
            //run binary search protocol to locate data
            while (currentNode != null && !found)
            {
                int comparison = anEntry.compareTo(currentNode.getData());
                if (comparison == 0)
                {
                    result = currentNode.getData(); 
                    removeNode(currentNode, parentNode);
                    found = true;
                } else {
                    parentNode = currentNode;
                    if (comparison < 0)
                        currentNode = currentNode.getLeftChild();
                    else
                        currentNode = currentNode.getRightChild();
                }    
            }
        }
        
        return result;
    }
    
    //helper method to remove nodeToRemove with given parentNode
    private void removeNode(BinaryNode<T> nodeToRemove, BinaryNode<T> parentNode)
    {
        //Assert: nodeToRemove != null
        BinaryNode<T> leftChild = nodeToRemove.getLeftChild();
        BinaryNode<T> rightChild = nodeToRemove.getRightChild();
        
        //case 1:  there are 2 children
        if (leftChild != null && rightChild != null)
        {
            //find the node to swap - largest value in left subtree
            BinaryNode<T> large = leftChild;
            BinaryNode<T> trail = nodeToRemove;
            
            while (large.getRightChild() != null)
            {
                trail = large;
                large = large.getRightChild();
            }
            
            //swap the values
            T data = nodeToRemove.getData();
            nodeToRemove.setData(large.getData());
            large.setData(data);
            
            //remove the swapped node 
            removeNode(large, trail);
        }   
        //Case 2: 1 (right) child
        else if (rightChild != null) 
        {
            if (nodeToRemove == getRootNode())
                setRootNode(rightChild);
                
            //nodeToRemove was the left child
            else if (parentNode.getLeftChild() == nodeToRemove)
                parentNode.setLeftChild(rightChild);
            //nodeToRemove was the right child
            else
                parentNode.setRightChild(rightChild);
        }
        //case 3: 1 left child (or leaf
        else
        {
            if (nodeToRemove == getRootNode())
                setRootNode(leftChild);
                
            //nodeToRemove was left child
            else if (parentNode.getLeftChild() == nodeToRemove)
                parentNode.setLeftChild(leftChild);
            //nodeToRemove was right child
            else
                parentNode.setRightChild(leftChild);
        }
    }

}
