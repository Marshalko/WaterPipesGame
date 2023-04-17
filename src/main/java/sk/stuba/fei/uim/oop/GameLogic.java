package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Board board;
    private int boardSize;
    private JFrame frame;


    public GameLogic(JFrame frame) {
    this.frame = frame;
    this.boardSize = INITIAL_BOARD_SIZE;
    this.newBoard(this.boardSize);
    this.frame.add(this.board);
    }


    private void newBoard(int size){
        this.frame.repaint();
        this.board = new Board(size);
        this.board.addMouseListener(this);
        this.board.addMouseMotionListener(this);
    }
    private void restartGame(){
        this.frame.remove(this.board);
        this.newBoard(this.boardSize);
        this.frame.add(this.board);
        this.frame.revalidate();

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);

        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            this.boardSize = ((JSlider) e.getSource()).getValue();
            this.restartGame();
            System.out.println("zmena na" + boardSize);
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
    }

    @Override // BUTTON
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();

        if(command.equals("resetButton")){
            System.out.println("Stlacil si reset");
            this.restartGame();
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
        else if(command.equals("checkButton")) {
            System.out.println("stlacil si check");
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                System.out.println("stlacil si R");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("stalcil si esc");
        }
    }

    public Board getBoard() {
        return board;
    }

    public JFrame getFrame() {
        return frame;
    }
}
