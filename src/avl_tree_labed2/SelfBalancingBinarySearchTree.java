/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_tree_labed2;

import avl_tree_labed2.VentanaAnimacion;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Mateo
 */
public class SelfBalancingBinarySearchTree {

    private SBBSTNode root;

    /* Constructor */
    public SelfBalancingBinarySearchTree() {
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    public SBBSTNode getRoot() {
        return root;
    }

    /* Make the tree logically empty */
    public void clear() {
        root = null;
    }

    /* Function to insert data */
    public void insert(int data) {
        root = insert(data, root);
    }
    
    public void delete(int data){
        root = deleteNode(root, data);
    }

    /* Function to get height of node */
    private int height(SBBSTNode t) {
        return t == null ? -1 : t.height;
    }

    /* Function to max of left/right node */
    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    /* Function to insert data recursively */
    private SBBSTNode insert(int x, SBBSTNode t) {
        if (t == null) {
            t = new SBBSTNode(x);
        } else if (x < t.data) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x < t.left.data) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (x > t.data) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x > t.right.data) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        } else
           ;  // Duplicate; do nothing
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    SBBSTNode deleteNode(SBBSTNode root, int data)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (data < root.data)
            root.left = deleteNode(root.left, data);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (data > root.data)
            root.right = deleteNode(root.right, data);
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                SBBSTNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
 
                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                                // the non-empty child
            }
            else
            {
 
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                SBBSTNode temp = minValueNode(root.right);
 
                // Copy the inorder successor's data to this node
                root.data = temp.data;
 
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.data);
            }
        }
 
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateWithLeftChild(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = rotateWithRightChild(root.left);
            return rotateWithLeftChild(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateWithRightChild(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rotateWithLeftChild(root.right);
            return rotateWithRightChild(root);
        }
 
        return root;
    }
    SBBSTNode minValueNode(SBBSTNode node)
    {
        SBBSTNode current = node;
 
        /* loop down to find the leftmost leaf */
        while (current.left != null)
        current = current.left;
 
        return current;
    }
    
    // Get Balance factor of node N
    int getBalance(SBBSTNode N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    /* Rotate binary tree node with left child */
    private SBBSTNode rotateWithLeftChild(SBBSTNode k2) {
        SBBSTNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private SBBSTNode rotateWithRightChild(SBBSTNode k1) {
        SBBSTNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child
     */
    private SBBSTNode doubleWithLeftChild(SBBSTNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child
     */
    private SBBSTNode doubleWithRightChild(SBBSTNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    /* Functions to search for an element */
    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(SBBSTNode r, int val) {
        boolean found = false;
        while ((r != null) && !found) {
            int rval = r.data;
            if (val < rval) {
                r = r.left;
            } else if (val > rval) {
                r = r.right;
            } else {
                found = true;
                VentanaAnimacion.resultado += r.height + " ";
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }

    private void inorder(SBBSTNode r) {
        if (r != null) {
            inorder(r.left);
            VentanaAnimacion.resultado += r.data + " ";
            inorder(r.right);
        }
    }

    /* Function for preorder traversal */
    public void preorder() {
        preorder(root);
    }

    private void preorder(SBBSTNode r) {
        if (r != null) {
            VentanaAnimacion.resultado += r.data + " ";
            preorder(r.left);
            preorder(r.right);
        }
    }

    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }

    private void postorder(SBBSTNode r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            VentanaAnimacion.resultado += r.data + " ";
        }
    }

    void PorNivelesRec() {
        Queue<SBBSTNode> q = new LinkedList<SBBSTNode>(); //Creamos la cola
        q.add(root);  //Se añade el primer nodo a la cola
        PorNivelesRec(q); // Se llama al metodo y damos como parametro la cola que acabamos de crear
    }

    void PorNivelesRec(Queue<SBBSTNode> q) {

        if (q.size() > 0) { //Condicional para controlar las llamadas recursivas
            SBBSTNode temp = q.poll(); //Recuperamos la informacion de la cola
            VentanaAnimacion.resultado += temp.data + " ";
            if (temp.left != null) {//Si el hijo izquierdo NO es nulo entonces se agrega a la cola
                q.add(temp.left);
            }
            if (temp.right != null) {//Si el hijo derecho NO es nulo entonces se agrega a la cola
                q.add(temp.right);
            }
            PorNivelesRec(q); //Aqui se realiza la llamada recursiva
        }
    }
}
