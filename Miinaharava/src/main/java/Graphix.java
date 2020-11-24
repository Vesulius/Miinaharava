
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Graphix extends Application {

    public Tile[][] tiles;

    public Graphix() {

    }

    @Override
    public void start(Stage stage) {
        stage.setScene(this.boardSceneCreator());
        stage.show();
    }

    public Scene boardSceneCreator() {
        Pane root = new Pane();
        root.setPrefSize(500, 500);

        BoardGenerator boardGenerator = new BoardGenerator();
        int[][] board = boardGenerator.generateBoard(10, 10, 10);

        this.tiles = new Tile[10][10];

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                Tile tile = new Tile(y, x, board[x][y], this);
                tiles[y][x] = tile;
                root.getChildren().add(tile);
            }
        }

        return new Scene(root);
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

    public class Tile extends StackPane {

        public int mines;
        public int x;
        public int y;

        public Graphix graphix;
        public Rectangle rectangle = new Rectangle(50, 50);
        public Text text = new Text();

        public Tile(int y, int x, int mines, Graphix graphix) {
            this.y = y;
            this.x = x;
            this.mines = mines;
            this.graphix = graphix;

            if (mines == -1) {
                text.setText("M");
            } else {
                text.setText(String.valueOf(mines));
            }

            this.text.setVisible(false);
            this.text.setFont(Font.font(22));

            if (this.mines == -1) {
                this.text.setFill(Color.CORAL);
            } else {
                this.text.setFill(Color.WHITE);
            }

            this.rectangle.setFill(Color.GRAY);
            this.rectangle.setStroke(Color.W+HITE);

            setTranslateY(y * 50);
            setTranslateX(x * 50);

            getChildren().addAll(this.rectangle, this.text);

            setOnMouseClicked(e -> reveal());
        }

        public void reveal() {
            if (this.text.isVisible()) {
                return;
            }
            text.setVisible(true);

            if (this.mines == -1) {
                System.out.println("Game over!");
            } else if (this.mines == 0) {
                this.rectangle.setFill(Color.WHITE);
                Graphix.revealSurrounding(this.y, this.x, this.graphix.tiles);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
