package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Board board;
    private int boardSize;
    private final JFrame frame;
    private final JLabel sizeLabel;
    private final JLabel winsLabel;
    private int numberOfWins;

    public GameLogic(JFrame frame) {
        this.numberOfWins=0;
        this.frame = frame;
        this.boardSize = INITIAL_BOARD_SIZE;
        this.newBoard(this.boardSize);
        this.frame.add(this.board);
        this.sizeLabel = new JLabel();
        this.winsLabel = new JLabel();
        this.updateWinsLabel();
        this.updateSizeLabel();
    }

    private void newBoard(int size) {
        this.frame.repaint();
        this.board = new Board(size);
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

    private void updateWinsLabel(){
        this.winsLabel.setText("Wins: " + numberOfWins);
        this.frame.repaint();
        this.frame.revalidate();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);

        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            this.boardSize = ((JSlider) e.getSource()).getValue();
            this.updateSizeLabel();
            this.numberOfWins=0;
            this.updateWinsLabel();
            this.restartGame();
            this.frame.setFocusable(true);
            this.frame.requestFocus();
        }
    }

    @Override
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

            if (board.checkConnections()) {
                numberOfWins++;
                this.updateWinsLabel();
                this.restartGame();
                this.frame.setFocusable(true);
                this.frame.requestFocus();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                frame.dispose();
                break;
            case KeyEvent.VK_ENTER:
                board.checkConnections();
                this.board.repaint();
                this.frame.setFocusable(true);
                this.frame.requestFocus();

                if (board.checkConnections()) {
                    numberOfWins++;
                    this.updateWinsLabel();
                    this.restartGame();
                    this.frame.setFocusable(true);
                    this.frame.requestFocus();
                }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        Component position = this.board.getComponentAt(e.getX(), e.getY());//rename
        if (!(position instanceof Pipe)) return;
        {
            this.board.eraseConnection();
            ((Pipe) position).setIsHighlight(true);
        }
        this.board.repaint();
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
            }
            ((Pipe) position).setOrientation(temp);
            this.frame.repaint();
        }
    }

    public JLabel getSizeLabel() {
        return sizeLabel;
    }
    public JLabel getWinsLabel() {
        return winsLabel;
    }
}
