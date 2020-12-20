package minesweepper.data;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Database {
    
    public Database() {

    }
    
    public void deletePath() {
                Path path = FileSystems.getDefault().getPath("app.db");
        try {
            Files.delete(path);
        } catch (IOException x) {
            System.out.println("Can not interact with filesystem. Error: " + x.getMessage());
        }
    }
    
    public void initialize() throws ClassNotFoundException {
        try {
            Statement statement = getConnection().createStatement(); 
            statement.execute("CREATE TABLE IF NOT EXISTS Runs (id INTEGER PRIMARY KEY, username TEXT NOT NULL, score INTEGER, time DECIMAL (5, 2))");
        } catch (Exception e) {
            System.out.println("Can not create table. Error: " + e.getMessage());
        }
    }

    public Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
        } catch (SQLException e) {
            System.out.println("Can not connect. Error: " + e.getMessage());
        }
        return connection;
    }
}
