package org.example.controller;

import org.example.dao.FuncionarioDAO;
import org.example.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private final FuncionarioDAO dao = new FuncionarioDAO();

    public void cadastrar(String nome, String cpf, String cargo, String telefone, String comissao) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome do funcionário!");
        }
        dao.salvar(new Funcionario(nome, cpf, cargo, telefone, comissao));
    }

    public List<Funcionario> listar() throws SQLException {
        return dao.listarTodos();
    }
}