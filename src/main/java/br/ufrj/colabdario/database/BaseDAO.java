package br.ufrj.colabdario.database;

import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;

public class BaseDAO {
    public Connection getConnection() {

     try {
         return DriverManager.getConnection(
                 "jdbc:postgresql://localhost:5432/colabdario", "postgres", "postgres");
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
    }
}