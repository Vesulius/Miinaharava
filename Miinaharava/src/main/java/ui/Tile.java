package ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        this.rectangle.setStroke(Color.WHITE);

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
            Graphix.revealSurrounding(this.y, this.x, this.graphix.board.tiles);
        }
    }
}
