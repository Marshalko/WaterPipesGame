package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private final int[][] tiles;
    private final ArrayList<int[]> path;
    private Pipe[][] board;

    public Board(int size) {

        MazeGenerator maze = new MazeGenerator(size);
        tiles = maze.getMaze();
        path = maze.getBackTrackList();
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

        int[] temp1 = path.get(0);
        int[] temp2 = path.get(path.size() - (path.size() - 1));
        if (temp1[0] != temp2[0]) {
            if (temp1[0] < temp2[0]) {
                this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.DOWN);
            } else
                this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.UP);

        } else
            this.board[temp1[0]][temp1[1]].setOutputDirection(OutputDirection.RIGHT);

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
        while (i < (path.size()) - 1) {
            i++;
            temp1 = board[path.get(i)[0]][path.get(i)[1]].getInputDirection();
            temp2 = board[path.get(i)[0]][path.get(i)[1]].getOutputDirection();
            temp3 = board[path.get(i - 1)[0]][path.get(i - 1)[1]].getInputDirection();
            temp4 = board[path.get(i - 1)[0]][path.get(i - 1)[1]].getOutputDirection();

            if (path.get(i)[0] != path.get(i - 1)[0]) {
                if (path.get(i)[0] > path.get(i - 1)[0]) {
                    if (checkConnectionsV2(temp1, temp2, temp3, temp4, i, OutputDirection.UP, OutputDirection.DOWN))
                        break;
                } else if (path.get(i)[0] < path.get(i - 1)[0]) {
                    if (checkConnectionsV2(temp1, temp2, temp3, temp4, i, OutputDirection.DOWN, OutputDirection.UP))
                        break;
                }
            } else if (path.get(i)[1] != path.get(i - 1)[1]) {
                if (path.get(i)[1] < path.get(i - 1)[1]) {
                    if (checkConnectionsV3(temp1, temp2, temp3, temp4, i, OutputDirection.RIGHT, OutputDirection.LEFT))
                        break;
                } else if (path.get(i)[1] > path.get(i - 1)[1]) {
                    if (checkConnectionsV3(temp1, temp2, temp3, temp4, i, OutputDirection.LEFT, OutputDirection.RIGHT))
                        break;
                }
            }
        }
        return board[path.get(path.size() - 1)[0]][path.get(path.size() - 1)[1]].isConnected();
    }

    private boolean checkConnectionsV3(OutputDirection temp1, OutputDirection temp2, OutputDirection temp3, OutputDirection temp4, int i, OutputDirection right, OutputDirection left) {
        return checkConnectionsV4(temp1, temp2, temp3, temp4, i, right, left);
    }

    private boolean checkConnectionsV4(OutputDirection temp1, OutputDirection temp2, OutputDirection temp3, OutputDirection temp4, int i, OutputDirection right, OutputDirection left) {
        if ((temp1 == right || temp2 == right) && (temp3 == left || temp4 == left)) {
            board[path.get(i)[0]][path.get(i)[1]].setConnected(true);
            board[path.get(i)[0]][path.get(i)[1]].repaint();
        } else return true;
        return false;
    }

    private boolean checkConnectionsV2(OutputDirection temp1, OutputDirection temp2, OutputDirection temp3, OutputDirection temp4, int i, OutputDirection down, OutputDirection up) {
        return checkConnectionsV4(temp1, temp2, temp3, temp4, i, down, up);
    }

    public void eraseConnection() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) this.board[i][j].setConnected(false);
        }
    }

}
