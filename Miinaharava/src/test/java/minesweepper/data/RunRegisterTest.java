package minesweepper.data;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RunRegisterTest {
    
    public RunRegister register;
    
    @Before
    public void setup() throws ClassNotFoundException {
        Database db = new Database();
        db.deletePath();
        db.initialize();
        this.register = new RunRegister(db);
    }
    
    @Test
    public void createdRunRegister() {
        assertTrue(this.register != null);
    }
    
    @Test
    public void canInsertRuns() throws ClassNotFoundException {
        this.register.addRun("ChickenDinner", 1000, 999.99);
        this.register.addRun("Silver", 999, 999.98);
        this.register.addRun("Lastboy", 0, 0);
        assertTrue(this.register.getRuns() != null);
    }
    
    @Test
    public void runsInRightOrder() throws ClassNotFoundException {
        this.register.addRun("ChickenDinner", 1000, 999.99);
        this.register.addRun("Silver", 999, 999.98);
        this.register.addRun("Lastboy", 0, 0);
        List<String[]> list = this.register.getRuns();
        assertTrue(list.get(0)[0].equals("ChickenDinner") && list.get(1)[0].equals("Silver") && list.get(2)[0].equals("Lastboy"));
    }
}
