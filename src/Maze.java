import java.util.ArrayList;

public class Maze {
    private String[][] maze = Main.getMaze("data/MazeData");
    private ArrayList<Integer> rowCoordinates;
    private ArrayList<Integer> columnCoordinates;
    private ArrayList<Integer> deadEndRowCoordinates;
    private ArrayList<Integer> deadEndColumnCoordinates;
    private int row;
    private int column;
    private int endRow;
    private int endColumn;
    private boolean moved;

    public Maze(String[][] maze, ArrayList<Integer> rowCoordinates, ArrayList<Integer> columnCoordinates, ArrayList<Integer> deadEndRowCoordinates, ArrayList<Integer> deadEndColumnCoordinates, int row, int column, int endRow, int endColumn) {
        this.maze = maze;
        this.rowCoordinates = rowCoordinates;
        this.columnCoordinates = columnCoordinates;
        this.deadEndRowCoordinates = deadEndRowCoordinates;
        this.deadEndColumnCoordinates = deadEndColumnCoordinates;
        this.row = row;
        this.column = column;
        this.endRow = endRow;
        this.endColumn = endColumn;
        moved = false;
    }

    public String solveMaze() {
        String output = "";
        while (row != endRow || column != endColumn) {
            rowCoordinates.add(row);
            columnCoordinates.add(column);
            moved = false;
            if (!moved && row - 1 >= 0 && maze[row - 1][column].equals(".")) {
                move("up");
            }
            if (!moved && row + 1 < maze.length && maze[row + 1][column].equals(".")) {
                move("down");
            }
            if (!moved && column - 1 >= 0 && maze[row][column - 1].equals(".")) {
                move("left");
            }
            if (!moved && column + 1 < maze[row].length && maze[row][column + 1].equals(".")) {
                move("right");
            }
            if (!moved) {
                returnToStart();
            }
        }
        rowCoordinates.add(endRow);
        columnCoordinates.add(endColumn);
        for (int i = 0; i < rowCoordinates.size() - 1; i++) {
            output += "(" + rowCoordinates.get(i) + ", " + columnCoordinates.get(i) + ") ---> ";
        }
        output += "(" + rowCoordinates.get(rowCoordinates.size() - 1) + ", " + columnCoordinates.get(columnCoordinates.size() - 1) + ")";
        return output;
    }

    public String solveMaze(String testOutput) {
        String actualOutput = "";
        while (row != endRow || column != endColumn) {
            rowCoordinates.add(row);
            columnCoordinates.add(column);
            moved = false;
            if (!moved && row - 1 >= 0 && maze[row - 1][column].equals(".")) {
                move("up");
            }
            if (!moved && row + 1 < maze.length && maze[row + 1][column].equals(".")) {
                move("down");
            }
            if (!moved && column - 1 >= 0 && maze[row][column - 1].equals(".")) {
                move("left");
            }
            if (!moved && column + 1 < maze[row].length && maze[row][column + 1].equals(".")) {
                move("right");
            }
            if (!moved) {
                returnToStart();
            }
        }
        rowCoordinates.add(endRow);
        columnCoordinates.add(endColumn);
        for (int i = 0; i < rowCoordinates.size() - 1; i++) {
            actualOutput += "(" + rowCoordinates.get(i) + ", " + columnCoordinates.get(i) + ") ---> ";
        }
        actualOutput += "(" + rowCoordinates.get(rowCoordinates.size() - 1) + ", " + columnCoordinates.get(columnCoordinates.size() - 1) + ")";
        if (testOutput.equals(actualOutput)) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAIL");
        }
        return actualOutput;
    }

    public void move(String direction) {
        boolean visitedTile = false;
        if (direction.equals("up")) {
            for (int i = 0; i < rowCoordinates.size(); i++) {
                if (rowCoordinates.get(i) == row - 1 && columnCoordinates.get(i) == column) {
                    visitedTile = true;
                }
            }
            for (int i = 0; i < deadEndRowCoordinates.size(); i++) {
                if (deadEndRowCoordinates.get(i) == row - 1 && deadEndColumnCoordinates.get(i) == column) {
                    visitedTile = true;
                }
            }
            if (!visitedTile) {
                row--;
                moved = true;
            }
        }
        else if (direction.equals("down")) {
            for (int i = 0; i < rowCoordinates.size(); i++) {
                if (rowCoordinates.get(i) == row + 1 && columnCoordinates.get(i) == column) {
                    visitedTile = true;
                }
            }
            for (int i = 0; i < deadEndRowCoordinates.size(); i++) {
                if (deadEndRowCoordinates.get(i) == row + 1 && deadEndColumnCoordinates.get(i) == column) {
                    visitedTile = true;
                }
            }
            if (!visitedTile) {
                row++;
                moved = true;
            }
        }
        else if (direction.equals("left")) {
            for (int i = 0; i < rowCoordinates.size(); i++) {
                if (rowCoordinates.get(i) == row && columnCoordinates.get(i) == column - 1) {
                    visitedTile = true;
                }
            }
            for (int i = 0; i < deadEndRowCoordinates.size(); i++) {
                if (deadEndRowCoordinates.get(i) == row && deadEndColumnCoordinates.get(i) == column - 1) {
                    visitedTile = true;

                }
            }
            if (!visitedTile) {
                column--;
                moved = true;
            }
        }
        else if (direction.equals("right")) {
            for (int i = 0; i < rowCoordinates.size(); i++) {
                if (rowCoordinates.get(i) == row && columnCoordinates.get(i) == column + 1) {
                    visitedTile = true;

                }
            }
            for (int i = 0; i < deadEndRowCoordinates.size(); i++) {
                if (deadEndRowCoordinates.get(i) == row && deadEndColumnCoordinates.get(i) == column + 1) {
                    visitedTile = true;

                }
            }
            if (!visitedTile) {
                column++;
                moved = true;
            }
        }
    }

    public void returnToStart() {
        rowCoordinates = new ArrayList<Integer>();
        columnCoordinates = new ArrayList<Integer>();
        deadEndRowCoordinates.add(row);
        deadEndColumnCoordinates.add(column);
        row = 0;
        column = 0;
    }
}
