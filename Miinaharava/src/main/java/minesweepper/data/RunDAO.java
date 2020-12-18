package minesweepper.data;

import java.util.List;

public interface RunDAO {
    
    void addRun(String username, int score, double time) throws Exception;
    
    List getRuns() throws Exception;
}
