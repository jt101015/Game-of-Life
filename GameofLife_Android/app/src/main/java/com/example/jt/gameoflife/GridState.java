package com.example.jt.gameoflife;

/**
 * Created by jt on 2/21/17.
 */

import android.util.Log;

import java.util.ArrayList;

public class GridState {

    private int[][] grid;
    public static final int COLUMN_NUMBER = 12;
    public static final int ROW_NUMBER = 12;
    private static final int LIVE = 1;
    private static final int DEAD = 0;
    private static final int UNDERPOPULATION_NEIGHBOR_NUMBER = 2;
    private static final int OVERPOPULATION_NEIGHBOR_NUMBER = 3;
    private static final int REPRODUCTION_NEIGHBOR_NUMBER = 3;


    public GridState() {
        this.grid = new int[COLUMN_NUMBER][ROW_NUMBER];
        initGrid();

    }

    public boolean isLive(int i, int j) {

        if ((i >= ROW_NUMBER) || (j >= COLUMN_NUMBER)) { return false; }

        if (LIVE == grid[i][j]) return true;

        return false;

    }

    public ArrayList<Integer> getStateArrayList(){

        int i = 0, j = 0;
        ArrayList<Integer> gridStateList = new ArrayList<Integer>();

        for (i = 0; i < ROW_NUMBER; i++) {
            for (j = 0; j < COLUMN_NUMBER; j++) {
                gridStateList.add(grid[i][j]);
            }
        }

        return gridStateList;
    }

    public void setState(ArrayList<Integer> gridStateList){

        int i = 0, j = 0, k = 0;

        for (i = 0; i < ROW_NUMBER; i++) {
            for (j = 0; j < COLUMN_NUMBER; j++) {
                grid[i][j] = gridStateList.get(k);
                k++;
            }
        }
        return;
    }

    private int countNeighbor(int i, int j) {

        int [][] direction = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int k = 0;
        int newI = 0;
        int newJ = 0;
        int neighborNum = 0;

        for (k = 0; k < direction.length; k++) {

            newI = i + direction[k][0];
            newJ = j + direction[k][1];

            if ((newI < 0) || (newI >= ROW_NUMBER)) { continue; }
            if ((newJ < 0) || (newJ >= COLUMN_NUMBER)) { continue; }

            if (LIVE == grid[newI][newJ]) { neighborNum++; }
        }

        return neighborNum;

    }

    public void nextGeneration() {

        int[][] newGrid = new int [ROW_NUMBER][COLUMN_NUMBER];
        int i = 0;
        int j = 0;
        int neighborNum = 0;

        for (i = 0; i < ROW_NUMBER; i++){

            for (j = 0; j < COLUMN_NUMBER; j++) {

                neighborNum = countNeighbor(i, j);
                newGrid[i][j] = grid[i][j];

                if (LIVE == grid[i][j]) {

                    if ((neighborNum < UNDERPOPULATION_NEIGHBOR_NUMBER) || (neighborNum > OVERPOPULATION_NEIGHBOR_NUMBER)) {
                        newGrid[i][j] = DEAD;
                    }
                } else {

                    if (REPRODUCTION_NEIGHBOR_NUMBER == neighborNum) {
                        newGrid[i][j] = LIVE;
                    }
                }
            }
        }

        grid = newGrid;

        return;

    }

    public void updateState(int i, int j){

        if ((i >= ROW_NUMBER) || (j >= COLUMN_NUMBER)) { return; }

        if (DEAD == grid[i][j]) {

            grid[i][j] = LIVE;

        } else {

            grid[i][j] = DEAD;

        }

        return;

    }


    public void initGrid(){
        int i = 0, j = 0;

        for (i = 0; i < ROW_NUMBER; i++){
            for (j = 0; j < COLUMN_NUMBER; j++){
                grid[i][j] = DEAD;
            }
        }
    }

}
