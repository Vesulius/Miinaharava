package ui;



import javafx.application.Application;
import javafx.stage.Stage;

public class Graphix extends Application {
    
    public Board board;

    public Graphix() {
        this.board = new Board(new Tile[10][10], this);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(board.boardSceneCreator());
        stage.show();
    }



    // This method is placeholder. The board should have been made into a graph, not a 2-dimensional array
    public static void revealSurrounding(int y, int x, Tile[][] tiles) {
        if (x - 1 >= 0 && y + 1 < tiles.length) {
            tiles[y + 1][x - 1].reveal();
        }
        if (y + 1 < tiles.length) {
            tiles[y + 1][x].reveal();
        }
        if (x + 1 < tiles[0].length && y + 1 < tiles.length) {
            tiles[y + 1][x + 1].reveal();
        }

        if (x - 1 >= 0) {
            tiles[y][x - 1].reveal();
        }
        if (x + 1 < tiles[0].length) {
            tiles[y][x + 1].reveal();
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            tiles[y - 1][x - 1].reveal();
        }
        if (y - 1 >= 0) {
            tiles[y - 1][x].reveal();
        }
        if (x + 1 < tiles[0].length && y - 1 >= 0) {
            tiles[y - 1][x + 1].reveal();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
