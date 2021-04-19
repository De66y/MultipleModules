package jdbc;

import java.sql.SQLException;

public class BookShelfApp {

    public static void main(String[] args) {
        try {
            Transaction.selectAll(ConnectionDatabase.makeConnectionToDatabase());
            Transaction.findABook(ConnectionDatabase.makeConnectionToDatabase(), "lord fo the rings"); //Niet hoofdletter gevoelig
        } catch (SQLException e) { //Voor de finally uit de Querieclass in method
            e.printStackTrace();
        }
    }
}
