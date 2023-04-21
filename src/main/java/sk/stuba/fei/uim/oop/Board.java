package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class Board extends JPanel {

    private final int[][] tiles;
    private final ArrayList<int[]> path;
    private Pipe[][] board;

    public Board(int size) {

        MazeGenerator maze = new MazeGenerator(size);
        tiles = maze.getMaze();                //array mazu kde su cisla podla tvaru
        path = maze.getBackTrackList();           // arraylist cesty
        createPipes(tiles.length);
        rotateStartEnd();
    }

    private void createPipes(int size) {

        this.board = new Pipe[tiles.length][tiles.length];
        setLayout(new GridLayout(size, size));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.board[i][j] = new Pipe(tiles[i][j]);
                this.add(this.board[i][j]);
            }
        }
    }

    private void rotateStartEnd() {
        // rotacia STARTU
        int[] temp1 = path.get(0);
        int[] temp2 = path.get(path.size() - (path.size() - 1));
        if (temp1[0] != temp2[0]) {
            if (temp1[0] < temp2[0]) {
                this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.DOWN);
            } else
                this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.UP);

        } else
            this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.RIGHT);


        // rotacia CIELA
        int[] temp3 = path.get(path.size() - 1);
        int[] temp4 = path.get(path.size() - 2);
        if (temp3[0] != temp4[0]) {
            if (temp3[1] < temp4[1])
                this.board[temp3[0]][temp3[1]].setOutputDirection(OutputDirection.UP);
            else
                this.board[temp3[0]][temp3[1]].setOutputDirection(OutputDirection.DOWN);
        } else
            this.board[temp3[0]][temp3[1]].setOutputDirection(OutputDirection.LEFT);

    }

    public boolean checkConnections() {
        OutputDirection temp1;
        OutputDirection temp2;
        OutputDirection temp3;
        OutputDirection temp4;
        int i = 0;
        while (i < (path.size())-1) {
            i++;
            temp1 = board[path.get(i)[0]][path.get(i)[1]].getInputDircetion();
            temp2 = board[path.get(i)[0]][path.get(i)[1]].getOutputDirection();
            temp3 = board[path.get(i - 1)[0]][path.get(i - 1)[1]].getInputDircetion();
            temp4 = board[path.get(i - 1)[0]][path.get(i - 1)[1]].getOutputDirection();

            if (path.get(i)[0] != path.get(i - 1)[0]) {       //POD SEBOU
                if (path.get(i)[0] > path.get(i - 1)[0]) {    // POD nim
                    if ((temp1 == OutputDirection.UP || temp2 == OutputDirection.UP) && (temp3 == OutputDirection.DOWN || temp4 == OutputDirection.DOWN)) {
                        board[path.get(i)[0]][path.get(i)[1]].setConnected(true);
                        board[path.get(i)[0]][path.get(i)[1]].repaint();
                    } else break;
                } else if (path.get(i)[0] < path.get(i - 1)[0]) {   // NAD nim
                    if ((temp1 == OutputDirection.DOWN || temp2 == OutputDirection.DOWN) && (temp3 == OutputDirection.UP || temp4 == OutputDirection.UP)) {
                        board[path.get(i)[0]][path.get(i)[1]].setConnected(true);
                        board[path.get(i)[0]][path.get(i)[1]].repaint();
                    } else break;
                }
            } else if (path.get(i)[1] != path.get(i - 1)[1]) {// VEDLA SEBA
                if (path.get(i)[1] < path.get(i - 1)[1]) { // NALAVO
                    if ((temp1 == OutputDirection.RIGHT || temp2 == OutputDirection.RIGHT) && (temp3 == OutputDirection.LEFT || temp4 == OutputDirection.LEFT)) {
                        board[path.get(i)[0]][path.get(i)[1]].setConnected(true);
                        board[path.get(i)[0]][path.get(i)[1]].repaint();
                    } else break;
                } else if (path.get(i)[1] > path.get(i - 1)[1]) { // NAPRAVO
                    if ((temp1 == OutputDirection.LEFT || temp2 == OutputDirection.LEFT) && (temp3 == OutputDirection.RIGHT || temp4 == OutputDirection.RIGHT)) {
                        board[path.get(i)[0]][path.get(i)[1]].setConnected(true);
                        board[path.get(i)[0]][path.get(i)[1]].repaint();
                    } else break;
                }
            }
        }
        return board[path.get(path.size() - 1)[0]][path.get(path.size() - 1)[1]].isConnected();
    }

    public void eraseConnection(){
        for(int i=0;i<tiles.length;i++){
            for(int j=0; j<tiles.length;j++) this.board[i][j].setConnected(false);
        }
    }
    public ArrayList<int[]> getPath() {
        return path;
    }

    public Pipe[][] getBoard() {
        return board;
    }
}
