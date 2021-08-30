/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_tree_labed2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Mateo
 */
public class Animacion extends JPanel {

    SelfBalancingBinarySearchTree t;
    public Animacion(SelfBalancingBinarySearchTree t) {
        this.t = t;
    }

    private void pintar(Graphics g, int x, int y, SBBSTNode n, SelfBalancingBinarySearchTree t) {
        int DIAMETRO = 30;
        int RADIO = DIAMETRO / 2;
        int ANCHO = 50;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (n == null) {
        } else {
            int EXTRA = n.full_nodes(n) * (ANCHO / 2);
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.setColor(new Color(66, 65, 105));
            g.fillOval(x, y, DIAMETRO, DIAMETRO);
            if (n.left != null) {
                g.drawLine(x + RADIO, y + RADIO, x - ANCHO - EXTRA + RADIO, y + ANCHO + RADIO);
            }
            if (n.right != null) {
                g.drawLine(x + RADIO, y + RADIO, x + ANCHO + EXTRA + RADIO, y + ANCHO + RADIO);
            }
            g.setColor(Color.white);
            g.drawString(n.data + "", x + 12, y + 18);
            pintar(g, x - ANCHO - EXTRA, y + ANCHO, n.left, t);
            pintar(g, x + ANCHO + EXTRA, y + ANCHO, n.right, t);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255, 255));
        g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        pintarArbol(g, t);
        repaint();
    }

    public void pintarArbol(Graphics g,SelfBalancingBinarySearchTree t) {
        //jPanel1.removeAll();
        //jPanel1.updateUI();showNodesPanelraphics g = node_showNodesPanelraphics();

        pintar(g, (getWidth() - 1) / 2, 50, t.getRoot(), t);
    }
}
