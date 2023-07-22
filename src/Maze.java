import java.util.Random;

public class Maze {

    public static String[][] mazeGenerator(String[][] finalMaze, int rowIndex, int columnIndex,
                                           int startColumn, int startRow, int endColumn, int endRow) {

        Random random = new Random();

        String[] possibleMazeElements = new String[]{" ", " ", " ", "X"};

        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < columnIndex; j++) {
                int x = random.nextInt(possibleMazeElements.length);
                finalMaze[j][i] = possibleMazeElements[x];
            }
        }

        finalMaze[startColumn][startRow] = "S";
        finalMaze[endColumn][endRow] = "E";

        return finalMaze;
    }

    public static String toString(String[][] finalMaze, int rowIndex, int columnIndex) {
        StringBuilder builder = new StringBuilder();

        for (int currentRow = 0; currentRow < rowIndex; currentRow++) {

            builder.append("|");

            for (int currentColumn = 0; currentColumn < columnIndex; currentColumn++) {
                builder.append(String.format("%s", finalMaze[currentColumn][currentRow]));
            }
            builder.append("|\n");
        }
        return builder.toString();
    }

    public static int getParameters() {
        Random random = new Random();
        int i = 5 + random.nextInt(6);
        return i;
    }

    public static int getRandomColumn(int columnIndex) {
        Random random = new Random();
        int randomColumn = random.nextInt(columnIndex);
        return randomColumn;
    }

    public static int getRandomRow(int rowIndex) {
        Random random = new Random();
        int randomRow = random.nextInt(rowIndex);
        return randomRow;
    }
}