
import java.util.Random;

public class BoardGenerator {

    public BoardGenerator() {

    }

    public int[][] generateBoard(int y, int x, int mines) {
        int[][] board = new int[y + 2][x + 2];
        int[][] returnBoard = new int[y][x];

        int[][] mineBoard = this.generateMines(board, mines);
        int[][] counterBoard = this.countMarkers(mineBoard);

        // Here we combine the mine board and the minecounter board 
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (mineBoard[i + 1][j + 1] == -1) {
                    returnBoard[i][j] = -1;
                } else {
                    returnBoard[i][j] = counterBoard[i + 1][j + 1];
                }
            }
        }

        return returnBoard;
    }

    public int[][] generateMines(int[][] board, int amount) {
        if (amount > Math.pow(board.length, 2)) {
            return null;
        }

        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int y = 1 + random.nextInt(board.length - 1);
            int x = 1 + random.nextInt(board[0].length - 1);
            if (board[y][x] == -1) {
                i--;
            } else {
                board[y][x] = -1;
            }
        }
        return board;
    }

    public int[][] countMarkers(int[][] minesBoard) {
        int[][] returnBoard = new int[minesBoard.length][minesBoard[0].length];

        for (int y = 1; y < minesBoard.length - 1; y++) {
            for (int x = 1; x < minesBoard[0].length - 1; x++) {
                if (minesBoard[y][x] == -1) {
                    returnBoard[y + 1][x] = returnBoard[y + 1][x] + 1;
                    returnBoard[y + 1][x - 1] = returnBoard[y + 1][x - 1] + 1;
                    returnBoard[y + 1][x + 1] = returnBoard[y + 1][x + 1] + 1;

                    returnBoard[y][x - 1] = returnBoard[y][x - 1] + 1;
                    returnBoard[y][x + 1] = returnBoard[y][x + 1] + 1;

                    returnBoard[y - 1][x - 1] = returnBoard[y - 1][x - 1] + 1;
                    returnBoard[y - 1][x] = returnBoard[y - 1][x] + 1;
                    returnBoard[y - 1][x + 1] = returnBoard[y - 1][x + 1] + 1;
                }
            }
        }
        return returnBoard;
    }

    // This method is just for testing and wont be used in the functioning program
    public void print(int[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(" " + board[y][x]);
            }
            System.out.println("");
        }
    }
}
