package minesweepper.logic;

import java.util.Random;
import minesweepper.ui.Tile;
import minesweepper.ui.BoardScreen;

public class BoardGenerator {

    private BoardScreen boardScreen;
    
    public BoardGenerator(BoardScreen screen) {
        this.boardScreen = screen;
    }
    
    // This class shoud be refactored
    public Tile[][] generateBoard(int width, int heigth, int mines) {
        int[][] board = new int[heigth + 2][width + 2];
        int[][] returnBoard = new int[heigth][width];

        int[][] mineBoard = this.generateMines(board, mines);
        int[][] counterBoard = this.countMarkers(mineBoard);
        
        Tile[][] tiles = new Tile[heigth][width];
        // Here we combine the mine board and the minecounter board 
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if (mineBoard[i + 1][j + 1] == -1) {
                    tiles[i][j] = new Tile(-1, this.boardScreen);
                } else {
                    tiles[i][j] = new Tile(counterBoard[i + 1][j + 1], this.boardScreen);
                }
            }
        }
        
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                if (x - 1 >= 0 && y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x - 1]);
                }
                if (y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x]);
                }
                if (x + 1 < tiles[0].length && y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x + 1]);
                }

                if (x - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y][x - 1]);
                }
                if (x + 1 < tiles[0].length) {
                    tiles[y][x].neibourghs.add(tiles[y][x + 1]);
                }

                if (x - 1 >= 0 && y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x - 1]);
                }
                if (y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x]);
                }
                if (x + 1 < tiles[0].length && y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x + 1]);
                }
            }
        }
        return tiles;
    }

    public int[][] generateMines(int[][] board, int amount) {
        if (amount > Math.pow(board.length, 2)) {
            return null;
        }

        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int y = 1 + random.nextInt(board.length - 2);
            int x = 1 + random.nextInt(board[0].length - 2);
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
