package jdbc;

import java.sql.*;

public class Queries {

    //Standaard
    public static void selectAll(Connection connection) throws SQLException { //Deze throws voor de finally
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Book"); //Soort van iterator waar je doorheen kunt loopen.

            while(result.next()) {
                int i = result.getInt("id");             //De input is de naam van de colum, in dit geval dus id
                String t = result.getString("title");    //De input is de naam van de colum, in dit geval dus title
                String a = result.getString("author");   //De input is de naam van de colum, in dit geval dus author

                Book book = Book.builder()                  //Dit kan ik gebruiken door de lombok dependency
                        .id(i).title(t).author(a).build();  //Dit zijn de fields van de  class Book
                System.out.println(book);                   //Hetzelfde als: System.out.println(book.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();                         //Resultset hangt aan statement, statement aan de connection.
            }                                               //Sluit je de connectie, dan sluit je dus alles
        }
    }

    //setAutocommit extra. Je kunt zelf bepalen of en wanneer je de query daadwerkelijk uitvoert
    public static void findABook(Connection connection, String searchTitle) throws SQLException{ //Deze throws voor de finally
        try {
            connection.setAutoCommit(false);   //Hiermee zorg je ervoor dat de query niet automatisch wordt uitgevoerd. commit() en rollback() horen hierbij.

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Book WHERE title= '"+ searchTitle +"'");

            if(result.next()) {
                int i = result.getInt("id");
                String t = result.getString("title");
                String a = result.getString("author");

                Book book = Book.builder()
                        .id(i).title(t).author(a).build();
                System.out.println(book);

                connection.commit(); //De query daadwerkelijk uitvoeren op de database
            } else {
                System.out.printf("Book: %s -> doesn't exist", searchTitle);
            }
            //connection.commit();     Bij Bram stond dit hier

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();      //Hier draaien we de query die uit willen voeren terug
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
