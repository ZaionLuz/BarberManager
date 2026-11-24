package com.barbermanager.service;

import com.barbermanager.dao.ClienteDAO;
import com.barbermanager.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(String nome, String telefone) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone do cliente é obrigatório.");
        }

        Cliente cliente = new Cliente(nome.trim(), telefone.trim());
        try {
            clienteDAO.cadastrar(cliente);
        } catch (SQLException e) {
            throw new Exception("Erro ao salvar cliente no banco de dados: " + e.getMessage(), e);
        }
    }

    public List<Cliente> listarClientes() throws Exception {
        try {
            return clienteDAO.listar();
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar clientes no banco de dados: " + e.getMessage(), e);
        }
    }
}