package avl_tree_labed2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Animacion extends JPanel {

    SelfBalancingBinarySearchTree t;

    public Animacion(SelfBalancingBinarySearchTree t) {
        this.t = t;
    }

    private void draw(Graphics g, int x, int y, SBBSTNode n, SelfBalancingBinarySearchTree t) {
        int Diameter = 30;
        int RADIO = Diameter / 2;
        int Width = 50;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (n == null) {
        } else {
            int EXTRA = n.full_nodes(n) * (Width / 2);
            g.drawOval(x, y, Diameter, Diameter);
            g.setColor(new Color(25, 25, 112)); // 66, 65, 105
            g.fillOval(x, y, Diameter, Diameter);
            if (n.left != null) {
                g.drawLine(x + RADIO, y + RADIO, x - Width - EXTRA + RADIO, y + Width + RADIO);
            }
            if (n.right != null) {
                g.drawLine(x + RADIO, y + RADIO, x + Width + EXTRA + RADIO, y + Width + RADIO);
            }
            g.setColor(Color.white);
            g.drawString(n.data + "", x + 12, y + 18);
            draw(g, x - Width - EXTRA, y + Width, n.left, t);
            draw(g, x + Width + EXTRA, y + Width, n.right, t);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255, 255)); // 255, 255, 255, 255
        g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        draw_tree(g, t);
        repaint();
    }

    public void draw_tree(Graphics g, SelfBalancingBinarySearchTree t) {
        // jPanel1.removeAll();
        // jPanel1.updateUI();showNodesPanelraphics g = node_showNodesPanelraphics();

        draw(g, (getWidth() - 1) / 2, 50, t.getRoot(), t);
    }
}
