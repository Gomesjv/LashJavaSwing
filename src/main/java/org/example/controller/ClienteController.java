package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String instagram, boolean temAlergia, String mapping) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome do cliente!");
        }

        String alergiaStr = temAlergia ? "Sim" : "Não";
        Cliente cliente = new Cliente(nome, cpf, telefone, instagram, alergiaStr, mapping);
        
        clienteDAO.salvar(cliente);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }
}