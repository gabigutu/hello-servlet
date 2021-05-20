package com.helloservlet.HelloServlet;

import com.helloservlet.HelloServlet.model.Something;
import com.helloservlet.HelloServlet.model.User;

import java.lang.reflect.Field;
import java.sql.*;

public class MysqlDatabase implements DatabaseConnector {

    private static MysqlDatabase mysqlDatabase;
    private Connection connection;

    private MysqlDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ancdb", "root", "D@t@tables");
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("Connection to MySQL cannot be established: " + exception.getMessage());
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            System.err.println("MySQL Driver Class cannot be found: " + exception.getMessage());
        }
    }

    public void insertRow(String tableName, Something something) {
        Statement statement = null;
        String query = "";
        try {
            statement = connection.createStatement();

            StringBuilder columnNamesBuilder = new StringBuilder();
            StringBuilder valuesBuilder = new StringBuilder();
            if (something instanceof User) {
                Field[] fields = User.class.getDeclaredFields();
                for (Field field : fields) {
                    columnNamesBuilder.append(field.getName()).append(", "); // e.g.: "username, "
                    valuesBuilder.append('\'').append(field.get(something)).append("', "); // e.g.: "vasile, "
                }
            }
            // trailing comma: "id, username, password, date_created, "
            String columnNames = columnNamesBuilder.toString().trim();
            columnNames = columnNames.substring(0, columnNames.length() - 1);
            String valuesSb = valuesBuilder.toString().trim();
            valuesSb = valuesSb.substring(0, valuesSb.length() - 1);
            query = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES " + "( " + valuesSb +
                    ")";
            int rowsInserted = statement.executeUpdate(query);
            System.out.println("Rows inserted " + rowsInserted);


        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("SQL exception: " + exception.getMessage());
            System.err.println("Query: " + query);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
            System.err.println("Exception: " + exception.getMessage());
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("Closing MySQL causes exception: " + exception.getMessage());
        }
    }

    public static MysqlDatabase getInstance() {
        if (mysqlDatabase == null) {
            mysqlDatabase = new MysqlDatabase();
        }
        return mysqlDatabase;
    }


}
