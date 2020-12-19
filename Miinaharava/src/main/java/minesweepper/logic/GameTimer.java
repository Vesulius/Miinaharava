package minesweepper.logic;

import java.util.TimerTask;
import javafx.scene.text.Text;


public class GameTimer {
    
    private static double interval;
    private static Text text;
    
    public GameTimer(Text text) {
        interval = 0;
        GameTimer.text = text;
    }
    
    public void startTimer() {
        final java.util.Timer time = new java.util.Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (interval > 100000) {
                    time.cancel();
                    time.purge();
                } else {
                    text.setText(String.valueOf(Math.ceil(interval * 10) / 10));
                    setInterval();
                }
            }
        }, 0, 100);
    }
    
    private static void setInterval() {
        interval += 0.1;
    }
    
    public void endTimer() {
        interval += 100001;
    }
    
    public double getTime() {
        return Double.valueOf(text.getText());
    }
}
