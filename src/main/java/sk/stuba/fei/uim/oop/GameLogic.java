package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Board board;
    private int boardSize;
    private JFrame frame;
    private JLabel sizeLabel;
    private ArrayList<int[]> path;

    public GameLogic(JFrame frame) {
        this.frame = frame;
        this.boardSize = INITIAL_BOARD_SIZE;
        this.newBoard(this.boardSize);
        this.frame.add(this.board);
        this.sizeLabel = new JLabel();
        this.updateSizeLabel();
        this.path = board.getPath();
    }

    private void newBoard(int size) {
        this.frame.repaint();
        this.board = new Board(size);
        this.path = board.getPath();
        this.board.addMouseListener(this);
        this.board.addMouseMotionListener(this);
    }

    private void restartGame() {
        this.frame.remove(this.board);
        this.newBoard(this.boardSize);
        this.frame.add(this.board);
        this.frame.revalidate();
    }

    private void updateSizeLabel() {
        this.sizeLabel.setText("Size is " + this.boardSize);
        this.frame.revalidate();
        this.frame.repaint();
    }

//    private void checkConnection(){
//        for(int i=0; i< path.size();i++){
//            if(board.getBoard())
//        }
//    }
    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);

        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            this.boardSize = ((JSlider) e.getSource()).getValue();
            this.updateSizeLabel();
            this.restartGame();
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
    }

    @Override // BUTTON
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();

        if (command.equals("resetButton")) {
            this.restartGame();
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        } else if (command.equals("checkButton")) {
            board.checkConnections();
            this.board.repaint();
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                frame.dispose();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        Component position = this.board.getComponentAt(e.getX(), e.getY());//rename
        if(!(position instanceof Pipe)) return;
        {
            this.board.eraseConnection();
            ((Pipe)position).setisHighlight(true);
                this.board.repaint();
                //this.frame.revalidate();
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Component position = this.board.getComponentAt(e.getX(), e.getY());
        if (!(position instanceof Pipe)) return;
        else {

            int temp = ((Pipe) position).getOrientation();
            temp++;
            if (temp > 3) {
                temp = 0;
                ((Pipe) position).setOrientation(temp);
                this.frame.repaint();
            } else {
                ((Pipe) position).setOrientation(temp);
                this.frame.repaint();
            }
        }
    }

    public JLabel getSizeLabel() {
        return sizeLabel;
    }
}
