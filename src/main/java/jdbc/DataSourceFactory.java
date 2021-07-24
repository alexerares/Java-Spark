package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {
    private static Connection connection;


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db-school?serverTimezone=UTC","root","Mitipiti");
        connection.setAutoCommit(false);
        return connection;
    }
    public boolean createTable() throws SQLException {
        String query="create table if not exists PERSONS (ID INT, NAME VARCHAR(45))";
        return connection.createStatement().executeUpdate(query)==0;
    }
}
