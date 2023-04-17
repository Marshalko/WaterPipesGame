package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {
    private int shape;
    private boolean isHighlight;

    public Pipe(int shape) {
        this.shape = shape;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border to each panel
        this.setPreferredSize(new Dimension(50, 50)); // Set the size of each panel musi sa scalovat
    }

    protected void paintComponent(Graphics g) {
        int height = getHeight();
        int thicknes = (int) (height * 0.4);
        int middleX = getHeight() / 2;
        int startY = 0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(thicknes));
        int x = getWidth() / 2;
        //ROVNA TRUBKA
        if (shape == 0) {
            if (this.isHighlight) {
                this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                g.setColor(Color.yellow);
                g.drawLine(x, 0, x, height);
                this.isHighlight = false;
            } else {
                g.setColor(Color.YELLOW);
                g.drawLine(x, 0, x, height);
            }
        }
        //ZAKRUTA
        if (shape == 7) {
            if (this.isHighlight) {
                this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                g.setColor(Color.orange);
                g.drawLine(x, 0, x, height / 2);
                g.drawLine(0, x, x, x);
                this.isHighlight = false;
            } else {
                g.setColor(Color.orange);
                g.drawLine(x, 0, x, height / 2);
                g.drawLine(0, x, x, x);
            }
        }
        //START
        if (shape == 2) {
            if (this.isHighlight) {
                this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                g.setColor(Color.red);
                g.drawLine(x, 0, x, height);
                this.isHighlight = false;
            } else {
                g.setColor(Color.red);
                g.drawLine(x, 0, x, height);
            }
        }
        //CIEL
        if (shape == 4) {
            if (this.isHighlight) {
                this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                g.setColor(Color.green);
                g.drawLine(x, 0, x, height);
                this.isHighlight = false;
            } else {
                g.setColor(Color.green);
                g.drawLine(x, 0, x, height);
            }
        }
        //PRAZDNE
        if (shape == 1) {
            if (this.isHighlight) {
                this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                this.isHighlight = false;
            }
        }

    }

    public void rotate(Pipe pipe){

    }



    public void setisHighlight(boolean ishighlight) {
        this.isHighlight = ishighlight;
    }
}
