package com.barbermanager.dao;

import com.barbermanager.database.ConnectionFactory;
import com.barbermanager.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void cadastrar(Cliente cliente)
            throws SQLException {

        String sql =
                "INSERT INTO cliente (nome, telefone) VALUES (?, ?)";

        try (Connection conexao =
                     ConnectionFactory.getConnection();

             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());

            stmt.executeUpdate();
        }
    }


    public List<Cliente> listar() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nome, telefone FROM cliente";

        try (Connection conexao =
                     ConnectionFactory.getConnection();

             PreparedStatement stmt =
                     conexao.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");

                Cliente cliente = new Cliente(nome, telefone);
                cliente.setId(id);

                clientes.add(cliente);
            }
        }

        return clientes;
    }

}
