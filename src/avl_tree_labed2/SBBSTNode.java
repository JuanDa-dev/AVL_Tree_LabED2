/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_tree_labed2;

/**
 *
 * @author Mateo
 */
public class SBBSTNode {

    SBBSTNode left, right;
    int data;
    int height;

    /* Constructor */
    public SBBSTNode() {
        left = null;
        right = null;
        data = 0;
        height = 0;
    }

    /* Constructor */
    public SBBSTNode(int n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }
        public int full_nodes(SBBSTNode node) {

        if (node == null) {
            return 0;
        } else {
            if (node.left != null && node.right != null) {
                return full_nodes(node.left) + full_nodes(node.right) + 1;
            }
            return full_nodes(node.left) + full_nodes(node.right);
        }

    }
    
}
