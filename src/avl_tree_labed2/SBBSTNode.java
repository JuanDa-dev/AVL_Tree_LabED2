package avl_tree_labed2;

public class SBBSTNode {

    SBBSTNode left, right;
    int data;
    int height;

    public SBBSTNode() {
        left = null;
        right = null;
        data = 0;
        height = 0;
    }

    public SBBSTNode(int n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }

    /**
     *
     * This funtion count all the full nodes of the tree
     *
     * @param node
     *
     */
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
