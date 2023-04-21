package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class Board extends JPanel  {

    private final int [][] tiles;
    private final ArrayList<int[]> path;
    private Pipe[][] board;

    public Board(int size) {

        MazeGenerator maze = new MazeGenerator(size);
        tiles = maze.getMaze();                //array mazu kde su cisla podla tvaru
        path = maze.getPath();           // arraylist cesty
        createPipes(tiles.length);
    }

    private void createPipes(int size){

        this.board = new Pipe[tiles.length][tiles.length];
        setLayout(new GridLayout(size,size));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.board[i][j] = new Pipe(tiles[i][j]);
                this.add(this.board[i][j]);
            }
        }
    }

    private void rotateStartEnd(){
        // rotacia STARTU
        int[] temp1 = path.get(0);
        int[] temp2 = path.get(path.size()-(path.size()-1));
        if(temp1[0]!=temp2[0]){
            this.board[temp1[0]][temp1[1]].setOrientation(0);
            this.board[temp1[0]][temp1[1]].repaint();
        } else {
            this.board[temp1[0]][temp1[1]].setOrientation(1);
            this.board[temp1[0]][temp1[1]].repaint();
        }
        // rotacia CIELA
        int[] temp3 = path.get(path.size()-1);
        int[] temp4 = path.get(path.size()-2);
        if(temp3[0]!=temp4[0]){
            this.board[temp4[0]][temp4[1]].setOrientation(0);
            this.board[temp4[0]][temp4[1]].repaint();
        } else {
            this.board[temp4[0]][temp4[1]].setOrientation(1);
            this.board[temp4[0]][temp4[1]].repaint();
        }

    }
    public ArrayList<int[]> getPath() {
        return path;
    }

    public Pipe[][] getBoard() {
        return board;
    }
}
