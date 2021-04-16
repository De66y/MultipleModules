package jdbc;

import java.sql.SQLException;

public class BookShelfApp {

    public static void main(String[] args) {
        try {
            Queries.selectAll(ConnectionDatabase.makeConnectionToDatabase());
            Queries.findABook(ConnectionDatabase.makeConnectionToDatabase(), "lord of the rings"); //Niet hoofdletter gevoelig
        } catch (SQLException e) { //Voor de finally uit de Querieclass in method
            e.printStackTrace();
        }
    }
}
