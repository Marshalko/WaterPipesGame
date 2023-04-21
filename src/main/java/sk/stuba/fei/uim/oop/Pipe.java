package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

enum  OutputDirection{UP,DOWN,LEFT,RIGHT}
public class Pipe extends JPanel {
    private final int shape;
    private boolean isConnected;
    private boolean isHighlight;
    private int orientation;
    private OutputDirection input;
    private OutputDirection output;


    public Pipe(int shape) {
        Random random = new Random();
        this.orientation = random.nextInt(3);
        this.shape = shape;
        this.isConnected = false;
        this.isHighlight = false;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(50, 50));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int height = getHeight();
        int thicknes = (int) (height * 0.4);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(thicknes));
        int x = getWidth() / 2;

        if (this.isHighlight) {
            this.setBackground(Color.pink);
            this.isHighlight = false;
        }
        else if(this.isConnected){
            this.setBackground(Color.cyan);
            //this.isConnected = false;
        }
        else this.setBackground(Color.white);
        switch (shape) {
            // ROVNA TRUBKA
            case 0: {
                switch (orientation) {
                    case 0:
                    case 2: {
                         this.input = OutputDirection.LEFT;
                         this.output = OutputDirection.RIGHT;
                        g.setColor(Color.YELLOW);
                        g.drawLine(0, x, height, x);
                        break;
                    }
                    case 1:
                    case 3: {
                        input = OutputDirection.UP;
                        output = OutputDirection.DOWN;
                        g.setColor(Color.YELLOW);
                        g.drawLine(x, 0, x, height);
                        break;
                    }
                }
                break;
            }
            // ZAKTRUTA TRUBKA
            case 7: {
                switch (orientation) {
                    //UP LEFT
                    case 0: {
                        this.input  = OutputDirection.UP;
                        this.output = OutputDirection.LEFT;
                        g.setColor(Color.orange);
                        g.drawLine(x, 0, x, height / 2);
                        g.drawLine(0, x, x, x);
                        break;//
                    }
                    //UP RIGHT
                    case 1: {
                        this.input  = OutputDirection.UP;
                        this.output = OutputDirection.RIGHT;
                        g.setColor(Color.orange);
                        g.drawLine(x, 0, x, x);
                        g.drawLine(x, x, height, x);
                        break;
                    }
                    // DOWN RIGHT
                    case 2: {
                        this.input  = OutputDirection.RIGHT;
                        this.output = OutputDirection.DOWN;
                        g.setColor(Color.orange);
                        g.drawLine(x, height, x, x);
                        g.drawLine(x, x, height, x);
                        break;
                    }
                    // LEFT DOWN
                    case 3: {
                        this.input  = OutputDirection.DOWN;
                        this.output = OutputDirection.LEFT;
                        g.setColor(Color.orange);
                        g.drawLine(x, height, x, x);
                        g.drawLine(0, x, x, x);
                        break;
                    }
                }
                break;
            }
            //PRAZDNE POLE
            case 1: {
                break;
            }
            //START
            case 2: {

                this.setBackground(Color.red);
                break;
            }
            //CIEL
            case 4: {
                this.setBackground(Color.green);
                break;
            }
        }
    }


    public int getShape() {
        return shape;
    }
    public OutputDirection getInputDircetion(){ return input;}
    public OutputDirection getOutputDirection(){return output;}
    public void  setOutputDirection(OutputDirection outputDirection){
        this.output=outputDirection;
    }
    public int getOrientation() {
        return orientation;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setisHighlight(boolean ishighlight) {
        this.isHighlight = ishighlight;
    }
}
