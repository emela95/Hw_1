/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Lewis and Chase, Chiemela Nwoke
 * @version 4.0
 */
import java.util.Random;
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T>
                                        implements BinarySearchTreeADT<T>
{
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() 
    {
        super();
    }
    
    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary
     *        search tree
     */
    public LinkedBinarySearchTree(T element) 
    {
        super(element);
        
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");
    }
    
    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its natural order.  Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) 
    {
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");

        Comparable<T> comparableElement = (Comparable<T>)element;

        if (isEmpty())
            root = new BinaryTreeNode<T>(element);
        else 
        {
            if (comparableElement.compareTo(root.getElement()) < 0)
            {
                if (root.getLeft() == null) 
                    this.getRootNode().setLeft(new BinaryTreeNode<T>(element));
                else
                    addElement(element, root.getLeft());
            }
            else
            {
                if (root.getRight() == null) 
                    this.getRootNode().setRight(new BinaryTreeNode<T>(element));
                else
                    addElement(element, root.getRight());
            }
        }
        modCount++;
    }
    
    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its natural order.  Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    private void addElement(T element, BinaryTreeNode<T> node) 
    {
        Comparable<T> comparableElement = (Comparable<T>)element;
        
        if (comparableElement.compareTo(node.getElement()) < 0)
        {
            if (node.getLeft() == null) 
                node.setLeft(new BinaryTreeNode<T>(element));
            else
                addElement(element, node.getLeft());
        }
        else
        {
            if (node.getRight() == null) 
                node.setRight(new BinaryTreeNode<T>(element));
            else
                addElement(element, node.getRight());
        }
    }
        
        
    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it.  Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public T removeElement(T targetElement)
                                  throws ElementNotFoundException 
    {
        T result = null;

        if (isEmpty())
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        else
        {
            BinaryTreeNode<T> parent = null;
            if (((Comparable<T>)targetElement).equals(root.element)) 
            {
                result =  root.element;
                BinaryTreeNode<T> temp = replacement(root);
                if (temp == null)
                    root = null;
                else 
                {
                    root.element = temp.element;
                    root.setRight(temp.right);
                    root.setLeft(temp.left);
                }

                modCount--;
            }
            else 
            {                
                parent = root;
                if (((Comparable)targetElement).compareTo(root.element) < 0)
                    result = removeElement(targetElement, root.getLeft(), parent);
                else
                    result = removeElement(targetElement, root.getRight(), parent);
            }
        }
        
        return result;
    }
                
    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it.  Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @param node the node from which to search
     * @param parent the parent of the node from which to search
     * @throws ElementNotFoundException if the target element is not found
     */
    private T removeElement(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
    throws ElementNotFoundException 
    {
        T result = null;
        
        if (node == null)
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        else
        {
            if (((Comparable<T>)targetElement).equals(node.element)) 
            {
                result =  node.element;
                BinaryTreeNode<T> temp = replacement(node);
                if (parent.right == node)
                    parent.right = temp;
                else 
                    parent.left = temp;

                modCount--;
            }
            else 
            {                
                parent = node;
                if (((Comparable)targetElement).compareTo(node.element) < 0)
                    result = removeElement(targetElement, node.getLeft(), parent);
                else
                    result = removeElement(targetElement, node.getRight(), parent);
            }
        }
        
        return result;
    }
    
    /**
     * Returns a reference to a node that will replace the one
     * specified for removal.  In the case where the removed node has 
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removed
     * @return a reference to the replacing node
     */
    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) 
    {
        BinaryTreeNode<T> result = null;
        
        if ((node.left == null) && (node.right == null))
            result = null;
        
        else if ((node.left != null) && (node.right == null))
            result = node.left;
        
        else if ((node.left == null) && (node.right != null))
            result = node.right;
        
        else
        {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            
            while (current.left != null)
            {
                parent = current;
                current = current.left;
            }
            
            current.left = node.left;
            if (node.right != current)
            {
                parent.left = current.right;
                current.right = node.right;
            }
            
            result = current;
        }
        
        return result;
    }
    
    /**
     * Removes elements that match the specified target element from 
     * the binary search tree. Throws a ElementNotFoundException if 
     * the specified target element is not found in this tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void removeAllOccurrences(T targetElement)
                   throws ElementNotFoundException 
    {
        removeElement(targetElement);
        
        try
        {
            while (contains((T)targetElement))
                removeElement(targetElement);
        }
        
        catch (Exception ElementNotFoundException)
        {
        }
    }

    /**
     * Removes the node with the least value from the binary search
     * tree and returns a reference to its element.  Throws an
     * EmptyCollectionException if this tree is empty. 
     *
     * @return a reference to the node with the least value
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException 
    {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        else 
        {
            if (root.left == null) 
            {
                result = root.element;
                root = root.right;
            }
            else 
            {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.left;
                while (current.left != null) 
                {
                    parent = current;
                    current = current.left;
                }
                result =  current.element;
                parent.left = current.right;
            }

            modCount--;
        }
 
        return result;
    }

    /**
     * Removes the node with the highest value from the binary
     * search tree and returns a reference to its element.  Throws an
     * EmptyCollectionException if this tree is empty. 
     *
     * @return a reference to the node with the highest value
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException 
    {
        // TODO: May need to implement.


        T treeResult = null;

        if (isEmpty())
            throw new EmptyCollectionException ("Empty tree");
        else
        {
            if (root.right == null)
            {
                treeResult =  root.element;
                root = root.left;
            } //if
            else
            {
                BinaryTreeNode<T> parentTree = root;
                BinaryTreeNode<T> currentTree = root.right;

                while (currentTree.right != null)
                {
                    parentTree = currentTree;
                    currentTree = currentTree.right;
                }

                treeResult =  currentTree.element;
                parentTree.right = currentTree.left;
            }

            modCount--;
        }

        return treeResult;

    }



    /**
     * Returns the element with the least value in the binary search
     * tree. It does not remove the node from the binary search tree. 
     * Throws an EmptyCollectionException if this tree is empty.
     *
     * @return the element with the least value
     * @throws EmptyCollectionException if the tree is empty
     */

    @Override
    public T findMin() throws EmptyCollectionException 
    {
        // TODO: May need to implement.
        T treeResult = null;

        if (isEmpty())
            throw new EmptyCollectionException ("Empty tree");
        else
        {
            BinaryTreeNode<T> current = root;

            while (current.left != null)
                current = current.left;

            treeResult = current.element;
        }

        return treeResult;


    }

    /**
     * Returns the element with the highest value in the binary
     * search tree.  It does not remove the node from the binary
     * search tree.  Throws an EmptyCollectionException if this 
     * tree is empty.
     *
     * @return the element with the highest value
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T findMax() throws EmptyCollectionException 
    {
        // TODO: May need to implement.
        T treeResult = null;

        if (isEmpty())
            throw new EmptyCollectionException ("Empty tree");
        else
        {
            BinaryTreeNode<T> current = root;

            while (current.right != null)
                current = current.right;

            treeResult = current.element;
        }

        return treeResult;
    }

    // TODO: Implement find.
    public T find(T targetElement)
    {
        if(isEmpty() == true)
        {
            throw new EmptyCollectionException("BinaryTree");
        }

        if(!(targetElement instanceof Comparable))
        {
            throw new NonComparableElementException("BinaryTree");
        }
        BinaryTreeNode<T> temp = findNode(targetElement, root);
        if(temp == null)
        {
            throw new ElementNotFoundException("BinaryTree");
        }
        return temp.getElement();
    }

    // TODO: Implement contains.
    public boolean contains(T targetElement)
    {
        boolean isFound = false;
        try
        {
            if(find(targetElement) != null)
            {
                isFound = true;
            }
            return isFound;
        }
        catch(Exception e)
        {
            return isFound;
        }

    }
    
    /**
     * Returns the left subtree of the root of this tree.
     *
     * @return a link to the left subtree fo the tree
     */
    @Override
    public LinkedBinarySearchTree<T> getLeft()
    { 
        // TODO: May need to implement.

        if(root == null)
            return null;

        LinkedBinarySearchTree<T> leftTree = new LinkedBinarySearchTree<T>();

        leftTree.root = root.getLeft();


        return leftTree;

    }
    
    /**
     * Returns the right subtree of the root of this tree.
     *
     * @return a link to the right subtree of the tree
     */
    @Override
    public LinkedBinarySearchTree<T> getRight()
    {
        // TODO: May need to implement.

        if(root == null)
            return null;

        LinkedBinarySearchTree<T> rightTree = new LinkedBinarySearchTree<T>();

        rightTree.root = root.getRight();

        return rightTree;

    }
    
    /**
     * Returns a reference to the specified target element if it is
     * found in this tree.  
     *
     * @param targetElement the element being sought in the tree
     * @param next the tree node to begin searching on
     */
    private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next) 
    {
        // TODO: May need to implement.

        if (next == null)
            return null;

        if (next.getElement().equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findNode(targetElement, next.getLeft());

        if (temp == null)
            temp = findNode(targetElement, next.getRight());

        return temp;
    }

    public static class Tester {

        public static void main(String[] args) {
            LinkedBinarySearchTree<Integer> data = new LinkedBinarySearchTree<Integer>();
            for( int i = 0 ; i < 20; i++) {
                Random rand = new Random();
                int  num = rand.nextInt(40) + 1;
                data.addElement(num);
            }
            //Test for toString
            System.out.print("Tree toString(): " + data.toString());

            //Test for getHeight method
            System.out.println("Height is :" + data.getHeight());
            //Test for getLeft method
            System.out.println("left" + data.getLeft());
            //Test for getRight method
            System.out.println("right is: "+ data.getRight());
            //Get the max 
            System.out.println("Max is: " + data.findMax());
            System.out.print("Tree toString(): " + data.toString());


            int i;
            //Test for iterator
            while(data.iteratorPreOrder().hasNext()) {
                i = data.iteratorPreOrder().next();
                System.out.print(i);
                if(!data.contains(i)){
                    System.out.print("Contains doesn't work");
                }
            }
        }
    }
}