package com.YunussEmree.PlayerCounter.Database;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
@Lazy
public class MySqlHelper implements DbHelper {

    String url = "jdbc:mysql://localhost:3306/players";
    String user = "root";
    String password = "password";


    @Override
    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = java.sql.DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        Connection connection = getConnection();

        try {
            connection.close();
            System.out.println("Connection to MySQL database closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(int count, String time) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playercount (count, time) VALUES (?, ?)");
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, time);
            preparedStatement.executeUpdate();
            System.out.println("Data saved to MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public ResultSet getDataFromDB(int limit) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playercount ORDER BY id DESC LIMIT ?");
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Data retrieved from MySQL database");
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public void deleteData(int id) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playercount WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Data deleted from MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateData(int id, int count, String time) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playercount SET count=?, time=? WHERE id=?");
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, time);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Data updated in MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void createTable() {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE playercount (id INT AUTO_INCREMENT PRIMARY KEY, count INT, time VARCHAR(255))");
            preparedStatement.executeUpdate();
            System.out.println("Table created in MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void dropTable() {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE playercount");
            preparedStatement.executeUpdate();
            System.out.println("Table dropped from MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }



}
