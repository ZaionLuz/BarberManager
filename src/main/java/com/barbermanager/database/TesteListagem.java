package com.barbermanager.database;

import com.barbermanager.dao.ClienteDAO;
import com.barbermanager.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class TesteListagem {

    public static void main(String[] args) {

        ClienteDAO dao = new ClienteDAO();

        try {
            List<Cliente> clientes = dao.listar();

            for (Cliente cliente : clientes) {

                System.out.println(
                        "ID: " + cliente.getId()
                                + " | Nome: " + cliente.getNome()
                                + " | Telefone: " + cliente.getTelefone()
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
