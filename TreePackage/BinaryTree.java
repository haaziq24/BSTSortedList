package TreePackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*;  //needed by tree iterators


/**
 * BinaryTree.java - implementation of a binary tree
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
    private BinaryNode<T> root;

    public BinaryTree()
    {
        root = null;
    }

    public BinaryTree(T rootData)
    {
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
    BinaryTree<T> rightTree)
    {
        initializeTree(rootData, leftTree, rightTree);
    }

    //sets this binary tree to a new binary tree
    // @param rootData: the object that is the data for new tree's root
    // @param leftTree: the left subtree of the new tree
    // @param rightTree: the right subtree of the new tree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
    BinaryTreeInterface<T> rightTree)
    {
        initializeTree(rootData, (BinaryTree<T>)leftTree,
            (BinaryTree<T>)rightTree);
    }

    //helper method to set the subtrees
    //  will make copies of the params
    private void initializeTree(T rootData, BinaryTree<T> leftTree,
    BinaryTree<T> rightTree)
    {
        root = new BinaryNode<>(rootData);

        //left subtree exists and is not empty, so attach it to root
        if ((leftTree != null) && (!leftTree.isEmpty()))
            root.setLeftChild(leftTree.root);

        //right subtree exists and is not empty...
        if ((rightTree != null) && (!rightTree.isEmpty()))
        {
            //and is distinct from left tree, so safe to attach
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            //both are the same, so make a new copy to be right tree
            else
                root.setRightChild(rightTree.root.copy());
        }

        //We can safely clear out leftTree
        if ((leftTree != null) && (leftTree != this))
            leftTree.clear();

        //We can safely clear out rightTree    
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
    }

    //sets the data in the root of this binary tree
    // @param rootData: the object that is the data for the root
    public void setRootData(T rootData)
    {
        root.setData(rootData);

    }

    //gets the root data
    public T getRootData()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return root.getData();
    }

    protected void setRootNode(BinaryNode<T> rootNode)
    {
        root = rootNode;
    }

    protected BinaryNode<T> getRootNode()
    {
        return root;
    }

    public int getHeight()
    {
        int height = 0;
        if (root != null)
            height = root.getHeight();
        return height;
    }

    public int getNumberOfNodes()
    {
        int numberOfNodes = 0;
        if (root != null)
            numberOfNodes = root.getNumberOfNodes();

        return numberOfNodes;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void clear()
    {
        root = null;
    }

    /* Implementations of the methods specified in TreeIteratorInterface are here.
     * . . . */

    public Iterator<T> getPreorderIterator()
    {
        return new PreorderIterator();
    }

    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    }

    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }

    public Iterator<T> getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    }
    
    private class InorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        
        public InorderIterator()
        {
            nodeStack = new LinkedStack();
            currentNode = root;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || currentNode != null;
        }
        
        public T next()
        {
            BinaryNode<T> nextNode = null;
            
            //Find leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            
            //Get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                //Assertion: nextNode != null b/c stack was not empty
                // before the pop
                currentNode = nextNode.getRightChild();
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
    }
    
    private class PreorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
                
        public PreorderIterator()
        {
            nodeStack = new LinkedStack();
            if (root != null)
                nodeStack.push(root);
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext()
        {
            return !nodeStack.isEmpty();
        }
        
        public T next()
        {
            BinaryNode<T> nextNode = null;
            
            if (hasNext())
            {
                nextNode = nodeStack.pop();
                BinaryNode<T> leftChild = nextNode.getLeftChild();
                BinaryNode<T> rightChild = nextNode.getRightChild();
                
                //push onto stack in reverse order
                if (rightChild != null)
                    nodeStack.push(rightChild);
                    
                if (leftChild != null)
                    nodeStack.push(leftChild);
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
    }

    private class PostorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        
        public PostorderIterator()
        {
            nodeStack = new LinkedStack();
            currentNode = root;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || currentNode != null;
        }
        
        public T next()
        {
            BinaryNode<T> nextNode = null;
            BinaryNode<T> leftChild = null;
            BinaryNode<T> rightChild = null;
            
            //Find leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                leftChild = currentNode.getLeftChild();
                if (leftChild == null)
                    currentNode = currentNode.getRightChild();
                else
                    currentNode = leftChild;
            }
            
            //Get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                //Assertion: nextNode != null b/c stack was not empty
                // before the pop
                BinaryNode<T> parent = null;
                if (!nodeStack.isEmpty())
                {
                    parent = nodeStack.peek();
                    if (nextNode == parent.getLeftChild())
                        currentNode = parent.getRightChild();
                    else
                        currentNode = null;
                } else
                    currentNode = null;
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
    }
    
    private class LevelOrderIterator implements Iterator<T>
    {
        private QueueInterface<BinaryNode<T>> nodeQueue;
                
        public LevelOrderIterator()
        {
            nodeQueue = new LinkedQueue();
            if (root != null)
                nodeQueue.enqueue(root);
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext()
        {
            return !nodeQueue.isEmpty();
        }
        
        public T next()
        {
            BinaryNode<T> currentNode = null;
            
            if (hasNext())
            {
                currentNode = nodeQueue.dequeue();
                BinaryNode<T> leftChild = currentNode.getLeftChild();
                BinaryNode<T> rightChild = currentNode.getRightChild();
                
                if (leftChild != null)
                    nodeQueue.enqueue(leftChild);
                if (rightChild != null)
                    nodeQueue.enqueue(rightChild);   
            } else
                throw new NoSuchElementException();
                
            return currentNode.getData();
        }
    }
}
