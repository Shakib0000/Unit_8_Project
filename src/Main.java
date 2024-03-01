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
        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[row].length; column++) {
                System.out.print("(" + row + ", " + column + ") -> ");
                rowCoordinates.add(row);
                columnCoordinates.add(column);
                if (row + 1 < maze.length && maze[row + 1][column].equals(".")) {
                    boolean tileWalked = false;
                    for (int i = 0; i < rowCoordinates.size(); i++) {
                        if (rowCoordinates.get(i) == row + 1 && columnCoordinates.get(i) == column) {
                            tileWalked = true;
                        }
                    }
                    if (!tileWalked) {
                        row++;
                        column--;
                    }
                }
                else if (row - 1 >= 0 && maze[row-1][column].equals(".")) {
                    boolean tileWalked = false;
                    for (int i = 0; i < rowCoordinates.size(); i++) {
                        if (rowCoordinates.get(i) == row - 1 && columnCoordinates.get(i) == column) {
                            tileWalked = true;
                        }
                    }
                    if (!tileWalked) {
                        row--;
                        column--;
                    }
                }
                else if (column - 1 >= 0 && maze[row][column - 1].equals(".")) {
                    boolean tileWalked = false;
                    for (int i = 0; i < rowCoordinates.size(); i++) {
                        if (rowCoordinates.get(i) == row && columnCoordinates.get(i) == column - 1) {
                            tileWalked = true;
                        }
                    }
                    if (!tileWalked) {
                        column-=2;
                    }
                }
            }
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