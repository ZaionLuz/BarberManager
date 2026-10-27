package com.barbermanager.database;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conexao =
                     ConnectionFactory.getConnection()) {

            System.out.println(
                    "Conectado ao MySQL com sucesso!"
            );

        } catch (SQLException e) {
            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }
    }
}
