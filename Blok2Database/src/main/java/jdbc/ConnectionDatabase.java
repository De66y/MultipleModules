package jdbc;

import java.sql.*;

public class ConnectionDatabase {
    private static final String URL = "jdbc:mysql://localhost/jdbcBooks?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "d3v3l0p3r";

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
