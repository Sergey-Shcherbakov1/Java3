package Lesson2;

//1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)

import java.sql.*;

public class Lesson2_1 {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        create();
        insert();
        select();
        update();
        delete();

        disconnect();

    }

    private static void create() {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS employees " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "Name STRING," +
                    "Salary DECIMAL(5,2))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert() {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO employees (ID, Name, Salary) VALUES (?, ?, ?);");
            connection.setAutoCommit(false);
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "Employee" + i);
                preparedStatement.setFloat(3, 100.00f + i);
                //почему-то не добавляет дробную часть числа, выдает лишь целую...
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void select() {
        try {
            statement = connection.createStatement();
            String SQLCommand = "SELECT * FROM employees WHERE Salary > 106";
            resultSet = statement.executeQuery(SQLCommand);
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + " " + resultSet.getString("name") +
                                " " + resultSet.getInt("Salary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        try {
            statement.executeUpdate("UPDATE employees SET Name = 'manager' where ID > 7");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void delete() {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch("DELETE FROM employees WHERE ID >= 5 AND ID <= 7");
            statement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:MainDataBase.db");
        statement = connection.createStatement();
    }

    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
