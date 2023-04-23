package sk.stuba.fei.uim.oop;

import java.util.*;
import java.util.Random;

public class MazeGenerator {

    private final int size;
    private final int[][] maze;
    private final Random rand;
    private final ArrayList<int[]> backTrackList;
    private final ArrayList<int[]> path;

    public MazeGenerator(int size) {
        this.size = size;
        this.maze = new int[size][size];
        this.rand = new Random();
        this.backTrackList = new ArrayList<>();
        this.path = new ArrayList<>();
        generateMaze();
    }

    private void generateMaze() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = 1;
            }
        }
        int startRow = rand.nextInt(size);
        int startCol = 0;
        maze[startRow][startCol] = 0;
        backTrackList.add(new int[]{startRow, startCol});

        dfs(startRow, startCol, size);
        setStartEnd();
        shapeSearch();
        fillPath();
        removeExcesivePaths();
    }

    private void dfs(int row, int col, int size) {

        boolean end = false;
        int count = 0;
        int newRow = 0;
        int newCol = 0;
        boolean pathCarved = false;
        int[][] neighbors = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Collections.shuffle(Arrays.asList(neighbors), rand);

        while (!pathCarved) {
            for (int[] neighbor : neighbors) {
                count++;
                if (row + neighbor[0] >= 0 || col + neighbor[1] >= 0 || row + neighbor[0] < size || col + neighbor[1] < size) {
                    newRow = row + neighbor[0];
                    newCol = col + neighbor[1];

                    if (newRow < size && newRow >= 0 && newCol < size && newCol >= 0 && maze[newRow][newCol] == 1) {
                        maze[newRow][newCol] = 0;

                        if (newCol >= (size - 1)) {
                            backTrackList.add(new int[]{newRow, newCol});
                            end = true;
                            pathCarved = true;
                            break;
                        }
                        pathCarved = true;
                        break;
                    }
                }
                if (count == 4) {

                    if (backTrackList.size() > 1) {

                        backTrackList.remove(backTrackList.size() - 1);
                        int[] lastElement = backTrackList.get(backTrackList.size() - 1);

                        end = true;
                        pathCarved = true;
                        dfs(lastElement[0], lastElement[1], size);
                    }
                    if (backTrackList.size() == 1) {
                        pathCarved = true;
                        end = true;
                        break;
                    }
                }
            }
            if (!end) {
                backTrackList.add(new int[]{newRow, newCol});
                dfs(newRow, newCol, size);
            }
        }
    }


    private void setStartEnd() {
        int[] start = backTrackList.get(0);
        int[] end = backTrackList.get(backTrackList.size() - 1);
        maze[start[0]][start[1]] = 2;
        maze[end[0]][end[1]] = 4;
    }

    private void fillPath() {
        for (int i = 0; i < backTrackList.size() - 1; i++) {
            int[] temp0 = backTrackList.get(i);
            int[] temp1 = backTrackList.get(i + 1);
            if (temp0[0] == temp1[0]) {
                if (temp0[1] < temp1[1]) {
                    path.add(backTrackList.get(i));
                    path.add(new int[]{temp0[0], temp0[1] + 1});
                } else {
                    path.add(new int[]{temp0[0], temp0[1] - 1});
                    path.add(backTrackList.get(i));
                }
            }
            if (temp0[1] == temp1[1]) {
                path.add(backTrackList.get(i));
                if (temp0[0] < temp1[0]) {
                    path.add(new int[]{(temp0[0] + 1), temp0[1]});
                } else {
                    path.add(new int[]{(temp0[0] - 1), temp0[1]});
                }
            }
        }
        path.add(backTrackList.get(backTrackList.size() - 1));
    }

    private void shapeSearch() {
        for (int i = 1; i < backTrackList.size() - 1; i++) {
            int[] temp0 = backTrackList.get(i - 1);
            int[] temp1 = backTrackList.get(i);
            int[] temp2 = backTrackList.get(i + 1);

            if (temp0[0] == temp1[0] && temp0[0] == temp2[0] || temp0[1] == temp1[1] && temp1[1] == temp2[1]) {
                maze[temp1[0]][temp1[1]] = 0;
            } else {
                maze[temp1[0]][temp1[1]] = 7;
            }
        }
    }

    private void removeExcesivePaths() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean found = false;
                for (int[] pos : path) {
                    if (pos[0] == i && pos[1] == j) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    maze[i][j] = 1;
                }
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public ArrayList<int[]> getBackTrackList() {
        return backTrackList;
    }
}



