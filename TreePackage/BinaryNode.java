package TreePackage;


/**
 * BinaryNode.java - implementation of a Node for a binary tree
 */
class BinaryNode<T>
{
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    
    public BinaryNode()
    {
        this(null);
    }
    
    public BinaryNode(T dataPortion)
    {
        this(dataPortion, null, null);
    }
    
    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                       BinaryNode<T> newRightChild)
    {
        data = dataPortion;
        leftChild = newLeftChild;
        rightChild = newRightChild;
    }
    
    //Retrieves the data portion of this node
    // @return The object in the data portion of the node
    public T getData()
    {
        return data;
    }
    
    //Sets the data portion of this node
    // @param newData The data object
    public void setData(T newData)
    {
        data = newData;
    }
    
    //Retrieves the left child of this node
    // @return A reference to this node's left child
    public BinaryNode<T> getLeftChild()
    {
        return leftChild;
    }
    
    //Sets this node's left child to a given node
    // @param newLeftChild A node that will be the left child
    public void setLeftChild(BinaryNode<T> newLeftChild)
    {
        leftChild = newLeftChild;
    }
    
    //Detects whether this node has a left child
    // @return True if the node has a left child
    public boolean hasLeftChild()
    {
        return leftChild != null;
    }
    
    //Retrieves the right child of this node
    // @return A reference to this node's right child
    public BinaryNode<T> getRightChild()
    {
        return rightChild;
    }
    
    //Sets this node's right child to a given node
    // @param newRightChild A node that will be the right child
    public void setRightChild(BinaryNode<T> newRightChild)
    {
        rightChild = newRightChild;
    }
    
    //Detects whether this node has a right child
    // @return True if the node has a right child
    public boolean hasRightChild()
    {
        return rightChild != null;
    }
    
    //Detects whether this node is a leaf
    // @return True if the node is a leaf
    public boolean isLeaf()
    {
        return (leftChild == null) && (rightChild == null);
    }
    
    //return a new copy of this BinaryNode
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if (leftChild != null)
            newRoot.setLeftChild(leftChild.copy());
        if (rightChild != null)
            newRoot.setRightChild(rightChild.copy());
        
        return newRoot;
    }
    
    public int getHeight()
    {
        return getHeight(this); //call private method
    }
    
    private int getHeight(BinaryNode<T> node)
    {
        int height = 0;
        
        if (node != null)
            height = 1 + Math.max(getHeight(node.getLeftChild()),
                                  getHeight(node.getRightChild()));
                                  
        return height;
    }
    
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;
        
        if (leftChild != null)
            leftNumber = leftChild.getNumberOfNodes();
            
        if (rightChild != null)
            rightNumber = rightChild.getNumberOfNodes();
            
        return 1 + leftNumber + rightNumber;
    }
}
