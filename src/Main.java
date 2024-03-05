import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] maze = getMaze("data/MazeData");
        ArrayList<Integer> rowCoordinates = new ArrayList<Integer>();
        ArrayList<Integer> columnCoordinates = new ArrayList<Integer>();
        int row = 0;
        int column = 0;
        int endRow = maze.length - 1;
        int endColumn = maze[0].length - 1;
        System.out.println("(" + row + ", " + column + ")");

        while (row != endRow || column != endColumn) {
            rowCoordinates.add(row);
            columnCoordinates.add(column);
            boolean moved = false;
            if (!moved && row - 1 >= 0 && maze[row-1][column].equals(".")) {
                boolean visitedTile = false;
                for (int i = 0; i < rowCoordinates.size(); i++) {
                    if (rowCoordinates.get(i) == row - 1 && columnCoordinates.get(i) == column) {
                        visitedTile = true;
                    }
                }
                if (!visitedTile) {
                    row--;
                    moved = true;
                }
            }
            if (!moved && row + 1 < maze.length && maze[row+1][column].equals(".")) {
                boolean visitedTile = false;
                for (int i = 0; i < rowCoordinates.size(); i++) {
                    if (rowCoordinates.get(i) == row + 1 && columnCoordinates.get(i) == column) {
                        visitedTile = true;
                    }
                }
                if (!visitedTile) {
                    row++;
                    moved = true;
                }
            }
            if (!moved && column - 1 >= 0 && maze[row][column-1].equals(".")) {
                boolean visitedTile = false;
                for (int i = 0; i < rowCoordinates.size(); i++) {
                    if (rowCoordinates.get(i) == row && columnCoordinates.get(i) == column - 1) {
                        visitedTile = true;
                    }
                }
                if (!visitedTile) {
                    column--;
                    moved = true;
                }
            }
            if (!moved && column + 1 < maze[row].length && maze[row][column+1].equals(".")) {
                boolean visitedTile = false;
                for (int i = 0; i < rowCoordinates.size(); i++) {
                    if (rowCoordinates.get(i) == row && columnCoordinates.get(i) == column + 1) {
                        visitedTile = true;

                    }
                }
                if (!visitedTile) {
                    column++;
                    moved = true;
                }
            }
            if (!moved) {
                rowCoordinates = new ArrayList<Integer>();
                columnCoordinates = new ArrayList<Integer>();
                rowCoordinates.add(row);
                columnCoordinates.add(column);
                row = 0;
                column = 0;
            }
            System.out.println("(" + row + ", " + column + ")");
        }
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }

}