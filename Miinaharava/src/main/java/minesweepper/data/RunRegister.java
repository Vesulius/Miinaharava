package minesweepper.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunRegister {
    private Database database;

    public RunRegister(Database database) {
        this.database = database;
    }
    
    public void addRun(String username, int score, double time) throws ClassNotFoundException {
        if (username.isBlank()) {
            username = "The Nameless One";
        }
        try {
            Statement statement = this.database.getConnection().createStatement();
            String query = "INSERT INTO Runs (username, score, time) VALUES ('%s', '%s', '%s')";
            statement.execute(String.format(query, username, score, time));
        } catch (SQLException e) {
            System.out.println("Can not insert new run. Error: " + e.getMessage());
        }
    }
    
    public List getRuns() throws ClassNotFoundException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        try {
            Statement statement = this.database.getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT id, username, score, time FROM Runs ORDER BY score DESC");
            
            while (results.next()) {
                String username = results.getString("username");
                int score = results.getInt("score");
                double time = results.getDouble("time");
                String[] row = {username, String.valueOf(score), String.valueOf(time)};
                list.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Can not insert new run. Error: " + e.getMessage());
        }
        
        return list;
    }
    
    // This method is just for testing and won't be used in the functioning program
    public void testRuns() throws ClassNotFoundException {
        this.addRun("ChickenDinner", 1000, 999.99);
        this.addRun("Silver", 999, 999.98);
        this.addRun("Lastboy", 0, 0);
    }
}
