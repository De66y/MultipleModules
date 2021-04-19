package jdbc;

import java.sql.*;
import static jdbc.DatabaseProperties.*;

public class ConnectionDatabase {
    private static final String URL = get("url");
    private static final String USERNAME = get("gebruikersnaam");
    private static final String PASSWORD = get("wachtwoord");

    public static Connection makeConnectionToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
