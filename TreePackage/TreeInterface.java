
/**
 * TreeInterface.java - implementation of general Tree ADT
 */
package TreePackage;

public interface TreeInterface<T>
{
    //get the value in the root of the tree
    public T getRootData();
    
    public int getHeight();
    
    public int getNumberOfNodes();
    
    public boolean isEmpty();
    
    public void clear();
}
