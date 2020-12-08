package minesweepper.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;

public class Tile extends StackPane {

    // mines = number of surrounding mines. -1 = mine 
    public int mines;
    public int x;
    public int y;
    public ArrayList<Tile> neibourghs = new ArrayList<Tile>();

    public BoardScreen board;
    public Rectangle rectangle = new Rectangle(50, 50);
    public Text text = new Text();

    public Tile(int mines, BoardScreen board) {
        this.mines = mines;
        this.board = board;

        this.setText();
        this.text.setVisible(false);
        this.text.setFont(Font.font(22));

        this.rectangle.setFill(Color.GRAY);
        this.rectangle.setStroke(Color.WHITE);

        this.getChildren().addAll(this.rectangle, this.text);
        
    // If revealed tiles mines == 0, then all the adjacent tiles will be revealed. This prosess is recursive.
    // If the tiles text == F, that means the tile has been marked and won't be revealed by clicking it. 
    // It will be revealed by the recursive prosess explaned above
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                mark();
            } else {
                if (!this.text.getText().equals("F")) {
                    reveal();
                }
            }
        });
    }
    

    public void reveal() {
        if (this.text.getText().equals("F")) {
            this.setText();
        } else if (this.text.isVisible()) {
            return;
        }
        text.setVisible(true);
        this.board.checkVictory(false);
        
        if (this.mines == -1) {
            System.out.println("GAME OVER!");
            this.board.checkVictory(true);
        } else if (this.mines == 0) {
            this.text.setFill(Color.LIGHTGREY);
            this.rectangle.setFill(Color.LIGHTGREY);
            this.neibourghs.forEach(Tile -> Tile.reveal());
        }
    }
    
    public void mark() {
        if (this.text.getText().equals("F")) {
            this.text.setVisible(false);
            this.setText();
        } else if (this.text.isVisible()) {
            return;
        } else {
            this.text.setText("F");
            this.text.setFill(Color.BLACK);
            this.text.setVisible(true);
        }
    }
    
    private void setText() {
        if (mines == -1) {
            this.text.setText("M");
            this.text.setFill(Color.RED);
        } else {
            this.text.setText(String.valueOf(mines));
            this.text.setFill(Color.WHITE);
        }
    }
}
