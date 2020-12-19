
package minesweepper.ui;
        
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
        
public class BoardScreen {
    
    private Scene scene;
    private static Text timertext;
    
    public BoardScreen(int heigth, int width, int mines, Text text, GridPane grid) {
        
        BoardScreen.timertext = text;
        
        Text spacetext = new Text("  Time: ");
        spacetext.setFont(Font.font(22));
        
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.getChildren().addAll(spacetext, timertext);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, grid);
        
        this.scene = new Scene(vbox);
        
    }   
    
    public Scene getScene() {
        return this.scene;
    }
}
    



