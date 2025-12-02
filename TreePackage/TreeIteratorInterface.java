
/**
 * TreeIteratorInterface.java - generalized interface for traversal methods of a tree
 *
 * 
 */

package TreePackage;
import java.util.Iterator;

public interface TreeIteratorInterface<T>
{
    public Iterator<T> getPreorderIterator();
    public Iterator<T> getPostorderIterator();
    public Iterator<T> getInorderIterator();
    public Iterator<T> getLevelOrderIterator();
}
