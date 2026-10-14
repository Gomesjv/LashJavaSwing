package org.example.controller;

import org.example.dao.EstoqueDAO;
import org.example.model.Estoque;
import java.sql.SQLException;
import java.util.List;

public class EstoqueController {
    private final EstoqueDAO dao = new EstoqueDAO();

    public void cadastrar(String item, String categoria, String qtd, String qtdMin, String validade, String custo) throws Exception {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome do item!");
        }
        dao.salvar(new Estoque(item, categoria, qtd, qtdMin, validade, custo));
    }

    public List<Estoque> listar() throws SQLException {
        return dao.listarTodos();
    }
}