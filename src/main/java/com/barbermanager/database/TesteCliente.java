package com.barbermanager.database;

import com.barbermanager.dao.ClienteDAO;
import com.barbermanager.model.Cliente;

import java.sql.SQLException;

public class TesteCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(
                "João Silva",
                "88999999999"
        );

        ClienteDAO dao = new ClienteDAO();

        try {
            dao.cadastrar(cliente);
            System.out.println(
                    "Cliente cadastrado com sucesso!"
            );

        } catch (SQLException e) {
            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }
    }
}
