package org.example.controller;

import org.example.dao.ProcedimentoDAO;
import org.example.model.Procedimento;
import java.sql.SQLException;
import java.util.List;

public class ProcedimentoController {
    private final ProcedimentoDAO dao = new ProcedimentoDAO();

    public void cadastrar(String tecnica, String duracao, String valor) throws Exception {
        if (tecnica == null || tecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome da técnica!");
        }
        dao.salvar(new Procedimento(tecnica, duracao, valor));
    }

    public List<Procedimento> listar() throws SQLException {
        return dao.listarTodos();
    }
}