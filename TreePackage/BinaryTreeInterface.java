/**
 * BinaryTreeInterface.java - interface for a Binary Tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
package TreePackage;


public interface BinaryTreeInterface<T> extends
           TreeInterface<T>, TreeIteratorInterface<T>
{
    //sets the data in the root of this binary tree
    // @param rootData: the object that is the data for the root
    public void setRootData(T rootData);
    
    //sets this binary tree to a new binary tree
    // @param rootData: the object that is the data for new tree's root
    // @param leftTree: the left subtree of the new tree
    // @param rightTree: the right subtree of the new tree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                          BinaryTreeInterface<T> rightTree);
}
