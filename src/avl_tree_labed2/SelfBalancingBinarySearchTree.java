package avl_tree_labed2;

//import avl_tree_labed2.VentanaAnimacion;
import java.util.LinkedList;
import java.util.Queue;

public class SelfBalancingBinarySearchTree {

    private SBBSTNode root;

    public SelfBalancingBinarySearchTree() {
        root = null;
    }

    /* This funtion check if tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    public SBBSTNode getRoot() {
        return root;
    }

    /* Clear the tree */
    public void clear() {
        root = null;
    }

    /**
     * This funtion insert the new data in the tree
     *
     * @param data
     */
    public void insert(int data) {
        root = insert(data, root);
    }

    /**
     *
     * Delete a specific data from the tree
     *
     * @param data
     */
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    /**
     *
     * Get height of the tree
     *
     * @param t
     *
     */
    private int height(SBBSTNode t) {
        return t == null ? -1 : t.height;
    }

    /* Function to max of left/right node */
    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

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
            ; // Duplicate; do nothing
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    SBBSTNode deleteNode(SBBSTNode root, int data) {

        if (root == null) {
            return root;
        }

        // If the data to be deleted is smaller than
        // the root's data, the data to delete belong to the left branch
        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } // If the data to be deleted is greater than the
        // root's data, the data to delete belong to the richt branch
        else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } // if the root data is same as the data to be delected, is the node to be
        // eliminated
        else {

            // Firts case: if the node have one child or not
            if ((root.left == null) || (root.right == null)) {
                SBBSTNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                {
                    root = temp; // Copy the contents of
                }                                 // the non-empty child
            } else {

                // Node with two childs
                // successor (smallest in the right subtree)
                SBBSTNode temp = minValueNode(root.right);

                root.data = temp.data;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.data);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // update the height of the current node
        root.height = max(height(root.left), height(root.right)) + 1;

        // get the balance
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateWithLeftChild(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateWithRightChild(root.left);
            return rotateWithLeftChild(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateWithRightChild(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateWithLeftChild(root.right);
            return rotateWithRightChild(root);
        }

        return root;
    }

    SBBSTNode minValueNode(SBBSTNode node) {
        SBBSTNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Get Balance factor of node N
    int getBalance(SBBSTNode N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    private SBBSTNode rotateWithLeftChild(SBBSTNode k2) {
        SBBSTNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private SBBSTNode rotateWithRightChild(SBBSTNode k1) {
        SBBSTNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private SBBSTNode doubleWithLeftChild(SBBSTNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private SBBSTNode doubleWithRightChild(SBBSTNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    /**
     *
     * search an element of the tree
     *
     * @param val
     *
     */
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

    public void inorder() {
        inorder(root);
    }

    private void inorder(SBBSTNode node) {
        if (node != null) {
            inorder(node.left);
            VentanaAnimacion.resultado += node.data + " ";
            inorder(node.right);
        }
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(SBBSTNode node) {
        if (node != null) {
            VentanaAnimacion.resultado += node.data + " ";
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(SBBSTNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            VentanaAnimacion.resultado += node.data + " ";
        }
    }

    void PorNivelesRec() {
        Queue<SBBSTNode> queue = new LinkedList<SBBSTNode>(); // Create an empty queue
        queue.add(root); // Enqueue root
        PorNivelesRec(queue); // Callback funtion
    }

    void PorNivelesRec(Queue<SBBSTNode> queue) {

        if (queue.size() > 0) {
            SBBSTNode temp = queue.poll(); // print from of queue and remove it
            VentanaAnimacion.resultado += temp.data + " ";
            if (temp.left != null) { // Enqueue left child
                queue.add(temp.left);
            }
            if (temp.right != null) {// Enqueue right child
                queue.add(temp.right);
            }
            PorNivelesRec(queue); // Callback funtion
        }
    }
}
