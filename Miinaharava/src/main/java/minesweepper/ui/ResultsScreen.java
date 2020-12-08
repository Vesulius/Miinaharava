package minesweepper.ui;

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import minesweepper.logic.Graphix;


public class ResultsScreen {
    private final Graphix graphix;

    public ResultsScreen(Graphix graphix) {
        this.graphix = graphix;
    }
    
    public Scene resultSceneCreator(boolean loss) {
        BorderPane pane = new BorderPane();
        pane.setPrefSize(500, 500);
        
        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setFont(Font.font(30));
        
        Button button = new Button("NEW GAME");
        button.setOnAction(e -> {
            this.graphix.selectGame();
        });
        
        if (loss) {
            text.setText("GAME OVER!");
        } else {
            text.setText("VICTORY!");
        }
        
        pane.setCenter(text);
        pane.setBottom(button);
        
        return new Scene(pane);
    }
}
