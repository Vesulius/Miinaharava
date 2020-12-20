package minesweepper.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseButton;

public class Tile extends StackPane {

    // mines = number of surrounding mines. -1 = mine 
    private int mines;
    private ArrayList<Tile> neibourghs = new ArrayList<Tile>();

    private AppUi ui;
    private Rectangle rectangle = new Rectangle(50, 50);
    private Text text;

    public Tile(int mines, AppUi ui) {
        this.mines = mines;
        this.ui = ui;

        this.text = new Text();
        this.text.setVisible(false);
        this.text.setFont(Font.font(22));

        this.rectangle.setFill(Color.GRAY);
        this.rectangle.setStroke(Color.WHITE);

        this.getChildren().addAll(this.rectangle, this.text);
        
    // If revealed tiles mines == 0, then all the adjacent tiles will be revealed. This prosess is recursive.
    // If the tiles text == F, that means the tile has been marked and won't be revealed by clicking it. 
    // It will be revealed however by the recursive prosess explaned above
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                mark();
            } else {
                if (!this.text.getText().equals("F")) {
                    try {
                        reveal();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }
    
    public void reveal() throws ClassNotFoundException {
        if (this.text.isVisible() && !this.text.getText().equals("F")) {
            return;
        }
        this.setTextToMine();
        this.text.setVisible(true);
        this.ui.checkVictory();
        if (this.mines == -1) {
            this.ui.endGame(false);
        } else if (this.mines == 0) {
            this.text.setText("");
            this.rectangle.setFill(Color.LIGHTGREY);
            this.neibourghs.forEach(Tile -> {
                try {
                    Tile.reveal();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    
    public void mark() {
        if (this.text.getText().equals("F")) {
            this.text.setVisible(false);
            this.setTextToMine();
        } else if (this.text.isVisible()) {
            return;
        } else {
            this.text.setText("F");
            this.text.setFill(Color.BLACK);
            this.text.setVisible(true);
        }
    }

    public int getMines() {
        return mines;
    }
    
    public void setTextToMine() {
        if (mines == -1) {
            this.text.setText("M");
            this.text.setFill(Color.RED);
        } else {
            this.text.setText(String.valueOf(mines));
            this.text.setFill(Color.WHITE);
        }
    }
    
    public void isMine() {
        this.mines = -1;
        this.neibourghs.forEach((mine) -> {
            if (mine.getMines() != -1) {
                mine.mines++;
            }
        });
    }
    
    public void addNeighbour(Tile tile) {
        this.neibourghs.add(tile);
    } 
}
