package minesweepper.ui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import minesweepper.logic.Graphix;


public class SelectScreen {
    private final Graphix graphix;
    private Scene scene;
    private Square[][] squares = new Square[20][20];
    private GridPane grid;

    public SelectScreen(Graphix graphix) {
        this.graphix = graphix;
        
        this.grid = new GridPane();
        this.grid.setPrefSize(500, 500);
        
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                Square square = new Square(y, x, this);
//                if (y < 5 || x < 5) {
//                    square.setFill(Color.BLACK);
//                }
                squares[y][x] = square;
                grid.add(square, y, x);
            }
        }
        
        Text text = new Text("WELCOME TO MINESWEEPPER");
        text.setFill(Color.BLACK);
        text.setFont(Font.font(30));
        
        
        this.scene = new Scene(this.grid); 
    }
    
    public void select(int y, int x) {
        this.graphix.newGame(y, x);
    }
    
    public void hower(int y, int x, Color color) {
        for (int i = 0; i < y + 1; i++) {
            for (int j = 0; j < x + 1; j++) {
                this.squares[j][i].setFill(color);
            }
        }
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private class Square extends Rectangle {
        private int y;
        private int x;
        private SelectScreen selectScreen;
        
        //this list includes the squares up and left of this square
        private Graphix graphix;
        
        public Square(int x, int y, SelectScreen selectScreen) {
            this.y = y;
            this.x = x;
            this.selectScreen = selectScreen;
            this.setHeight(25);
            this.setWidth(25);
            this.setFill(Color.GRAY);
            this.setStroke(Color.WHITE);
            
            this.setOnMouseEntered(e -> {
                this.select();
            });
            
            this.setOnMouseExited(e -> {
                this.deselect();
            });
            
            this.setOnMouseClicked(e -> {
                this.setFill(Color.BLUE);
                this.selectScreen.select(this.y + 1, this.x + 1);
            });
        }   
        
        private void select() {
            this.selectScreen.hower(y, x, Color.LIGHTGREY);
            if (!(this.y < 5 || this.x < 5)) {
                this.setFill(Color.LIGHTGREY);
            }
        }
        
        private void deselect() {
            this.selectScreen.hower(y, x, Color.GREY);
            if (!(this.y < 5 || this.x < 5)) {
                this.setFill(Color.GREY);
            }
        }
    }
}
