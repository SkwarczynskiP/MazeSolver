public class Solve {

    public static boolean canMove(String[][] mat, boolean[][] visited, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
                (mat[x][y] == " " || mat[x][y] == "E") && !visited[x][y];
    }

    public static int findShortestPath(String[][] mat, boolean[][] visited,
                                       int i, int j, int x, int y, int min_dist, int dist) {
        if (i == x && j == y) {
            return Integer.min(dist, min_dist);
        }

        visited[i][j] = true;

        //test can move down
        if (canMove(mat, visited, i + 1, j)) {

            min_dist = findShortestPath(mat, visited, i + 1, j, x, y, min_dist, dist + 1);
        }

        //test can move right
        if (canMove(mat, visited, i, j + 1)) {

            min_dist = findShortestPath(mat, visited, i, j + 1, x, y, min_dist, dist + 1);
        }

        //test can move up
        if (canMove(mat, visited, i - 1, j)) {

            min_dist = findShortestPath(mat, visited, i - 1, j, x, y, min_dist, dist + 1);
        }

        //test can move left
        if (canMove(mat, visited, i, j - 1)) {

            min_dist = findShortestPath(mat, visited, i, j - 1, x, y, min_dist, dist + 1);
        }

        //backtracking
        visited[i][j] = false;

        return min_dist;
    }

    public static int findShortestPathLength(String[][] mat, int i, int j, int x, int y) {

        if (mat == null || mat.length == 0 || mat[i][j] == "X" || mat[x][y] == "X") {
            return -2;
        }

        int m = mat.length;
        int n = mat[0].length;
        int min_dist;

        boolean[][] visited = new boolean[m][n];

        min_dist = findShortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0);

        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;
    }

    private static boolean move(String[][] mat, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
                (mat[x][y] == " " || mat[x][y] == "E");
    }

    private static boolean isSolved(String[][] mat, int i, int j, int x, int y) {

        int columns = mat.length;
        int rows = mat[0].length;

        if(i == x && j == y) {
            mat[i][j] = "!";

            System.out.println(Maze.toString(mat, rows,columns));

            return true;
        } else {
            return false;
        }
    }
    public static void createShortestPath(String[][] mat, int i, int j, int x, int y, int currentDist, int distance) {

        //test can move down
        if (move(mat, i + 1, j) && currentDist <= distance &&
                !isSolved(mat, i + 1, j, x, y)) {

            i++;
            mat[i][j] = "*";

            createShortestPath(mat, i, j, x, y, currentDist + 1, distance);

            if(!isSolved(mat, i, j, x, y)) {
                mat[i][j] = " ";
                i--;
            }
        }

        //test can move right
        if (move(mat, i, j + 1) && currentDist <= distance &&
                !isSolved(mat, i, j + 1, x, y)) {

            j++;
            mat[i][j] = "*";

            createShortestPath(mat, i, j, x, y, currentDist + 1, distance);

            if(!isSolved(mat, i, j, x, y)) {
                mat[i][j] = " ";
                j--;
            }
        }

        //test can move up
        if (move(mat, i - 1, j) && currentDist <= distance &&
                !isSolved(mat, i - 1, j, x, y)) {

            i--;
            mat[i][j] = "*";

            createShortestPath(mat, i, j, x, y, currentDist + 1, distance);

            if(!isSolved(mat, i, j, x, y)) {
                mat[i][j] = " ";
                i++;
            }
        }

        //tests can move left
        if (move(mat, i, j - 1) && currentDist <= distance &&
                !isSolved(mat, i, j - 1, x, y)) {

            j--;
            mat[i][j] = "*";

            createShortestPath(mat, i, j, x, y, currentDist + 1, distance);

            if(!isSolved(mat, i, j, x, y)) {
                mat[i][j] = " ";
                j++;
            }
        }
    }
}
