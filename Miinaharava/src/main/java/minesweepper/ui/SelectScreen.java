package minesweepper.ui;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SelectScreen {
    private AppUi app;
    private Scene scene;
    private Square[][] squares = new Square[20][20];
    private GridPane grid;
    public TextField username;
    
    public SelectScreen(AppUi app) {
        this.app = app;
        this.grid = new GridPane();
        this.grid.setPrefSize(500, 500);
        this.username = new TextField();

        Text text = new Text("  Write username:");
        text.setFont(Font.font(22));
        
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                Square square = new Square(y, x, this);
                squares[y][x] = square;
                grid.add(square, y, x);
            }
        }
        HBox hbox = new HBox();
        hbox.setSpacing(150);
        hbox.getChildren().addAll(text, username);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, this.grid);
        
        this.scene = new Scene(vbox); 
    }
    
    private void select(int y, int x) {
        this.app.newGame(y, x, this.username.getText());
    }
    
    private void hower(int y, int x, Color color) {
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
        
        public Square(int x, int y, SelectScreen selectScreen) {
            this.y = y;
            this.x = x;
            this.selectScreen = selectScreen;
            this.setHeight(25);
            this.setWidth(25);
            this.setFill(Color.DIMGRAY);
            this.setStroke(Color.WHITE);
            
            this.setOnMouseEntered(e -> {
                this.select();
            });
            
            this.setOnMouseExited(e -> {
                this.deselect();
            });
            
            this.setOnMouseClicked(e -> {
                this.selectScreen.select(this.y + 1, this.x + 1);
            });
        }   
        
        private void select() {
            this.selectScreen.hower(y, x, Color.LIGHTGREY);
        }
        
        private void deselect() {
            this.selectScreen.hower(y, x, Color.DIMGREY);
        }
    }
}
