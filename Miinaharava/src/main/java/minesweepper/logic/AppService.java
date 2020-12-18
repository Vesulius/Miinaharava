
package minesweepper.logic;

import java.util.List;
import minesweepper.data.*;

public class AppService {
    
    private Database database;
    private RunRegister register;
    private int mines;
    private String username;
     
    public AppService() throws ClassNotFoundException {
        this.database = new Database();
        this.database.initialize();
        this.register = new RunRegister(this.database);
    }
    
    public void newGame(int height, int width, String username) {
        this.username = username;
        this.mines = Math.abs(height * width / 10);
        if (this.mines == 0 && height + width != 2) {
            this.mines++;
        }
    }

    public void endGame(double time) throws ClassNotFoundException {
        int score = ( int) (Math.pow(mines * 10, 2) / (time + 1));
        this.register.addRun(this.username, score, time);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<String[]> getRuns() throws ClassNotFoundException {
        return this.register.getRuns();
    }
    
    public int mineCounter(int height, int width) {
        this.mines = Math.abs(height * width / 10);
        if (mines == 0 && height + width != 2) {
            mines++;
        }
        return this.mines;
    }
}
