
package minesweepper.logic;

import java.util.List;
import minesweepper.data.*;

public class AppService {
    
    private Database database;
    private RunRegister register;
    private int mines;
    private String username;
    private int victoryCount;
    
     
    public AppService() throws ClassNotFoundException {
        this.database = new Database();
        this.database.initialize();
        this.register = new RunRegister(this.database);
    }
    
    public void newGame(int height, int width, String username) {
        this.username = username;
        this.mines = this.mineCounter(height, width);
        this.victoryCount = height * width - this.mines;
        if (this.mines == 0 && height + width != 2) {
            this.mines++;
        }
    }

    public void endGame(double time) throws ClassNotFoundException {
        int score = (int) (Math.pow(mines * 10, 2) / (time + 1)) + 1;
        this.register.addRun(this.username, score, time);
    }

    
    public List<String[]> getRuns() throws ClassNotFoundException {
        return this.register.getRuns();
    }
    
    public int getMines() {
        return this.mines;
    }
    
    public int mineCounter(int height, int width) {
        int minesCount = Math.abs(height * width / 10);
        if (minesCount == 0 && height + width != 2) {
            minesCount++;
        }
        return minesCount;
    }
    
    public boolean checkVictoryCounter() {
        this.victoryCount--;
        return this.victoryCount == 0;
    }
}
