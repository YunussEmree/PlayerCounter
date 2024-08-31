package com.YunussEmree.PlayerCounter.Database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DbHelper {

    void saveData(int count, String time);

    ResultSet getDataFromDB(int limit);

    void deleteData(int id);

    void updateData(int id, int count, String time);

    void createTable();

    void dropTable();

    void closeConnection();

    Connection getConnection();
}
