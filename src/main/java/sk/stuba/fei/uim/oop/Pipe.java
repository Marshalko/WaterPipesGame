package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {
    private boolean highlight;
    private int shape;

    public Pipe(int shape) {
        this.shape = shape;
        if (shape == 2) this.setBackground(Color.red);
        if (shape == 0) this.setBackground(Color.GRAY);
        if (shape == 7) this.setBackground(Color.darkGray);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border to each panel
        this.setPreferredSize(new Dimension(50, 50)); // Set the size of each panel musi sa scalovat
    }

    protected void paintComponent(Graphics g){
        int height = getHeight();
        int thicknes = (int) (height*0.4);
        int middleX = getHeight()/2;
        int startY =0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(thicknes));
        int x = getWidth() / 2;
        if(shape==0) {
            g.setColor(Color.YELLOW);
            g.drawLine(x, 0, x, height);
        }
        if(shape==7){
            g.setColor(Color.orange);
            g.drawLine(x,0,x,height/2);
            g.drawLine(0,x,x,x);
        }
        if(shape==2){
            g.setColor(Color.red);
            g.drawLine(x,0,x,height);
        }
    }
}
