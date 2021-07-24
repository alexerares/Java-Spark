package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Connection connection = dataSourceFactory.getConnection();
        boolean table = dataSourceFactory.createTable();
        System.out.println(table);
//        try (Statement stmt = connection.createStatement()) {
//            String query = "insert into persons values(2, 'Ovidiu');";
//            stmt.executeUpdate(query);
//        }
//        connection.commit();
//        List<jdbc.Person> personList = new ArrayList<>();
//        try (Statement stmt = connection.createStatement()) {
//            String selectSQL = "select * from PERSONS";
//            try (ResultSet resultSet = stmt.executeQuery(selectSQL)) {
//                while (resultSet.next()) {
//                    jdbc.Person person = new jdbc.Person();
//                    person.setId(resultSet.getInt("id"));
//                    person.setName(resultSet.getString("name"));
//                    personList.add(person);
//                }
//            }
//        }
//        System.out.println(personList);

//        String preparedStatement = "update PERSONS set name=? where id=?";
//        try (PreparedStatement ptmt = connection.prepareStatement(preparedStatement)) {
//                ptmt.setString(1,"Rares");
//                ptmt.setInt(2,1);
//                ptmt.executeUpdate();
//            }
//        connection.commit();
//        try (Statement stmt = connection.createStatement()) {
//            String tableSql = "CREATE TABLE IF NOT EXISTS employees"
//                    + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
//                    + "position varchar(30), salary double)";
//            stmt.execute(tableSql);
//        }
//        String callableStatement = "{call insertEmployee(?,?,?,?)}";
//        try (CallableStatement cstmt = connection.prepareCall(callableStatement)) {
//            cstmt.setString(2, "Ana");
//            cstmt.setString(3, "Developer");
//            cstmt.setDouble(4, 2000);
//            cstmt.registerOutParameter(1, Types.INTEGER);
//            cstmt.execute();
//        }
//        connection.commit();
    }
}

