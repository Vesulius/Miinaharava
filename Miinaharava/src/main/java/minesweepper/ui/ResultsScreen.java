package minesweepper.ui;

import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import minesweepper.logic.App;

public class ResultsScreen {
    private App app;
    private Scene scene;

    public ResultsScreen(App app, boolean loss, List<String[]> runs) {
        this.app = app;
        
        BorderPane pane = new BorderPane();
        pane.setPrefSize(500, 500);
        
        ListView<String> listView = new ListView();
        
        runs.forEach((run) -> {
            listView.getItems().add(Arrays.toString(run));
        });
        
        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setFont(Font.font(30));
        
        Button button = new Button("NEW GAME");
        button.setOnAction(e -> {
            this.app.selectGame();
        });
        
        if (loss) {
            text.setText("GAME OVER!");
        } else {
            text.setText("VICTORY!");
        }
        
        pane.setCenter(text);
        pane.setCenter(listView);
        pane.setBottom(button);
        
        this.scene = new Scene(pane);
    }
   
    public Scene getScene() {
        return scene;
    }
}
