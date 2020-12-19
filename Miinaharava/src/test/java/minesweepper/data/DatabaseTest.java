package minesweepper.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {

    public Database database;
    

    @Before
    public void setup() {
        this.database = new Database();
    }

    @Test
    public void createdDatabase() {
        assertTrue(this.database != null);
    }

    @Test
    public void canGetConnection() throws ClassNotFoundException {
        assertTrue(this.database.getConnection() instanceof Connection);
    }

    @Test
    public void canCreateTable() throws ClassNotFoundException, SQLException {
        this.database.initialize();
        DatabaseMetaData dbData = this.database.getConnection().getMetaData();
        ResultSet resultset = dbData.getTables(null, null, "runs", null);
        assertTrue(resultset.next());
    }
}
