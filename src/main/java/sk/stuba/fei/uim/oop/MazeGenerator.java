package sk.stuba.fei.uim.oop;

import java.util.*;
import java.util.Random;

public class MazeGenerator {

    private int size;
    private int[][] maze;
    private Random rand;
    private boolean[][] visited;
    private ArrayList<int[]> backTrackList;

    public MazeGenerator(int size) {
        this.size = size;
        this.maze = new int[size][size];
        this.rand = new Random();
        this.visited = new boolean[size][size];
        this.backTrackList = new ArrayList<int[]>();
        generateMaze();
        //printMaze();
    }

    private void generateMaze() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = 1;
            }
        }
        int startRow = rand.nextInt(3);
        int startCol = rand.nextInt(3);
        maze[startRow][startCol] = 0;
        backTrackList.add(new int[]{startRow, startCol});

        dfs(startRow, startCol, size);
        setStartEnd();
        shapeSearch();

    }

    private void dfs(int row, int col, int size) {


        // Define the order in which to visit neighboring cells
        boolean end = false;
        boolean finish = false;
        int count = 0;
        int newRow = 0;
        int newCol = 0;
        boolean pathCarved = false;
        boolean otherNeighbours = false;
        int[][] neighbors = {{-2, 0}, {2, 0}, {0, 2}, {0, -2}};
        Collections.shuffle(Arrays.asList(neighbors), rand);
        //stop recursion when i am finish
        if (row < size && row >= (size - 2) && col < size && col >= (size - 2)) {
            end = true;
            pathCarved = true;
        }

        while (!pathCarved) {
            for (int[] neighbor : neighbors) {
                count++;
                if (row + neighbor[0] >= 0 || col + neighbor[1] >= 0 || row + neighbor[0] < size || col + neighbor[1] < size) {
                    newRow = row + neighbor[0];
                    newCol = col + neighbor[1];
//                    if (newRow != row) {
//                        if(newCol - 1 == Integer.parseInt(null)){
//                            if(newCol+1 == 0) otherNeighbours =true;
//                        }
//                        else if (newCol - 1 == 0 || newCol + 1 == 0) {
//                            otherNeighbours = true;
//                        }
//                    }
                    if (newRow < size && newRow >= 0 && newCol < size && newCol >= 0 && maze[newRow][newCol] == 1) {
                        maze[newRow][newCol] = 0;
                        if (newRow != row) {
                            if (neighbor[0] == 2) {
                                maze[newRow - 1][newCol] = 0;
                                //backTrackList.add(new int[]{newRow - 1, newCol});

                            } else {
                                maze[newRow + 1][newCol] = 0;
                                //backTrackList.add(new int[]{newRow + 1, newCol});
                            }
                        }
                        if (newCol != col) {
                            if (neighbor[1] == 2) {
                                maze[newRow][newCol - 1] = 0;
                               // backTrackList.add(new int[]{newRow, newCol - 1});
                            } else {
                                maze[newRow][newCol + 1] = 0;
                                //backTrackList.add(new int[]{newRow, newCol + 1});
                            }
                        }
                        if (newRow < size && newRow >= (size - 2) && newCol < size && newCol >= (size - 2)) {
                            backTrackList.add(new int[]{newRow, newCol});
                            end = true;
                            pathCarved = true;
                            finish = true;
                            break;
                        }
                        pathCarved = true;
                        break;
                    }
                }
                if (count == 4) {
                    //vratim sa na poslednu poziciu
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
                if (!finish) {
                    backTrackList.add(new int[]{newRow, newCol});// tu davam novu poziciu do arraylistu
                    dfs(newRow, newCol, size);
                }
            }
        }
    }

    //setne start a end na 2
    private void setStartEnd() {
        int[] start = backTrackList.get(0);
        int[] end = backTrackList.get(backTrackList.size() - 1);
        maze[start[0]][start[1]] = 2;
        maze[end[0]][end[1]] = 2;

    }

    private void printMaze() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (maze[i][j] == 1) {
                    System.out.print(" I ");
                } else if (maze[i][j] == 2) {
                    System.out.print(" X ");
                } else if (maze[i][j] == 0) {
                    System.out.print(" 0 ");
                }
            }
            if (i == 10) break;
            System.out.println();
        }
    }

    //setne cetu v maze na 0 alebo 7 podla tvaru cesty
    private void shapeSearch() {
        for (int i = 1; i < backTrackList.size() - 1; i++) {
            int[] temp0 = backTrackList.get(i - 1);
            int[] temp1 = backTrackList.get(i);
            int[] temp2 = backTrackList.get(i + 1);
            //ak je rovny trubko
            if (temp0[0] == temp1[0] && temp0[0] == temp2[0] || temp0[1] == temp1[1] && temp1[1] == temp2[1]) {
                maze[temp1[0]][temp1[1]] = 0;
                // maze[temp0[0]][temp0[1]]=3;

            }
            //ak je Lkovy trubko
            else {
                maze[temp1[0]][temp1[1]] = 7;
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public ArrayList<int[]> getBackTrackList() {
        return backTrackList;
    }

    public static void main(String[] args) {
        new MazeGenerator(10);


    }
}


