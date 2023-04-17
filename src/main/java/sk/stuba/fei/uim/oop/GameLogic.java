package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Board board;
    private int boardSize;
    private JFrame frame;
    private JLabel sizeLabel;


    public GameLogic(JFrame frame) {
    this.frame = frame;
    this.boardSize = INITIAL_BOARD_SIZE;
    this.newBoard(this.boardSize);
    this.frame.add(this.board);
    this.sizeLabel = new JLabel();
    this.updateSizeLabel();
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

    private void updateSizeLabel(){
        this.sizeLabel.setText("Size is "+ this.boardSize);
        this.frame.revalidate();
        this.frame.repaint();
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);

        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            this.boardSize = ((JSlider) e.getSource()).getValue();
            this.updateSizeLabel();
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
                restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                frame.dispose();
        }
    }

    public JLabel getSizeLabel() {
        return sizeLabel;
    }
}
