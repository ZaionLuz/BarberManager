package com.barbermanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection()
            throws SQLException {

        String url = "jdbc:mysql://localhost:3306/barbermanager";
        String usuario = "root";
        String senha = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(
                url, usuario, senha
        );
    }
}
