import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Maze Solver");
        System.out.println("Would you like to create a single maze and solve it?" +
                " Or simulate 100 mazes being solved? (single/simulate)");
        int i = 0;

        while (i == 0) {
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("single")) {
                single();
                i++;

            } else if (choice.equalsIgnoreCase("simulate")) {

                //Decided to leave the Simulation of 100 mazes to only 50. This was due to memory restrictions on my laptop.

                int[] array = new int[50];
                int total = 0;
                int count = 0;

                for (int z = 0; z < 50; z++) {
                    int dist = simulate();

                    if (array[z] != -1 && array[z] != -2) {
                        total += dist;
                        count++;
                    }
                }

                int average = total / count;

                System.out.println("The average shortest path to solve the maze is " + average);

                i++;
            } else {
                System.out.println("Not a valid entry. Please try again.");
            }
        }
    }

    public static void single() {
        int rowIndex = Maze.getParameters();
        int columnIndex = Maze.getParameters();

        System.out.println("Columns: " + columnIndex + "\nRows: " + rowIndex);

        int startColumn = Maze.getRandomColumn(columnIndex);
        int startRow = Maze.getRandomRow(rowIndex);
        int endColumn = Maze.getRandomColumn(columnIndex);
        int endRow = Maze.getRandomRow(rowIndex);

        while (endColumn == startColumn && endRow == startRow) {
            endColumn = Maze.getRandomColumn(columnIndex);
            endRow = Maze.getRandomRow(rowIndex);
        }

        String[][] maze = new String[columnIndex][rowIndex];
        Maze.mazeGenerator(maze, rowIndex, columnIndex, startColumn, startRow, endColumn, endRow);

        System.out.println(Maze.toString(maze, rowIndex, columnIndex));

        int min_dist = Solve.findShortestPathLength(maze, startColumn, startRow, endColumn, endRow);

        if (min_dist != -1) {
            System.out.println("The shortest distance is " + min_dist);
            System.out.println("");
        } else {
            System.out.println("This maze is not possible\n");
        }

        int currentDist = 0;

        Solve.createShortestPath(maze, startColumn, startRow, endColumn, endRow, currentDist, min_dist);
    }

    public static int simulate() {
        int rowIndex = Maze.getParameters();
        int columnIndex = Maze.getParameters();

        int startColumn = Maze.getRandomColumn(columnIndex);
        int startRow = Maze.getRandomRow(rowIndex);
        int endColumn = Maze.getRandomColumn(columnIndex);
        int endRow = Maze.getRandomRow(rowIndex);

        while (endColumn == startColumn && endRow == startRow) {
            endColumn = Maze.getRandomColumn(columnIndex);
            endRow = Maze.getRandomRow(rowIndex);
        }

        String[][] maze = new String[columnIndex][rowIndex];
        Maze.mazeGenerator(maze, rowIndex, columnIndex, startColumn, startRow, endColumn, endRow);

        int min_dist = Solve.findShortestPathLength(maze, startColumn, startRow, endColumn, endRow);

        if (min_dist == -1) {
            System.out.println("Generated maze is not possible\n");
        }

        int currentDist = 0;

        Solve.createShortestPath(maze, startColumn, startRow, endColumn, endRow, currentDist, min_dist);

        return min_dist;
    }
}