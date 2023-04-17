package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;

public class Board extends JPanel implements ActionListener, EventListener, MouseListener, KeyListener {

    private Player p;
    private MazeGenerator maze;
    private int [][] tiles;
    private ArrayList<int[]> path;


    public Board(int size) {
     maze = new MazeGenerator(size) ;
     tiles =maze.getMaze();                //array mazu kde su cisla podla tvaru
     path = maze.getBackTrackList();           // arraylist cesty
     createPipes();

    }

    private void createPipes(){
        setLayout(new GridLayout(tiles.length,tiles[0].length));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                Pipe panel = new Pipe(tiles[i][j]);
                add(panel);
                this.repaint();// Add the panel to the main panel
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
