package minesweepper.ui;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ResultsScreen {
    private AppUi app;
    private Scene scene;

    public ResultsScreen(AppUi app, boolean loss, List<String[]> runs) {
        this.app = app;
        
        ListView<Text> listView = new ListView();
        Text runValues = new Text("Username:     Score:       Time:");
        listView.getItems().add(runValues);
        
        Color fade = Color.RED;
        
        int placement = 1;
        for (String[] run: runs) {
            Text text = new Text(placement + "  " + run[0] + "  " + run[1] + "  " + run[2]);
            text.setVisible(true);
            text.setFill(fade);
            fade = fade.darker();
            listView.getItems().add(text);
            placement++;
        }
        
        Text victoryText = new Text();
        victoryText.setFill(Color.BLACK);
        victoryText.setFont(Font.font(23));
        if (loss) {
            victoryText.setText("GAME OVER!");
        } else {
            victoryText.setText("VICTORY!");
        }
        
        Button button = new Button("NEW GAME");
        button.autosize();
        button.setOnAction(e -> {
            this.app.selectGame();
        });
        
        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.getChildren().addAll(new Text(""), victoryText, button);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, listView);
        
        this.scene = new Scene(vbox, Color.BLACK);
    }
   
    public Scene getScene() {
        return scene;
    }
}
