package org.example.controller;

import org.example.dao.AgendamentoDAO;
import org.example.model.Agendamento;
import org.example.config.ConexaoSQLite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {
    private final AgendamentoDAO dao = new AgendamentoDAO();

    public void cadastrar(int clienteIdx, int procIdx, int funcIdx, String funcNome, String dataHora, String status, String valor, String sinal, String clienteNome, String procNome) throws Exception {
        if (clienteIdx <= 0 || procIdx <= 0) {
            throw new IllegalArgumentException("Selecione o Cliente e o Procedimento!");
        }
        String funcionarioFinal = funcIdx > 0 ? funcNome : "Não Informado";
        dao.salvar(new Agendamento(clienteNome, procNome, funcionarioFinal, dataHora, status, valor, sinal));
    }

    public List<Agendamento> listar() throws SQLException {
        return dao.listarTodos();
    }

    public List<String> buscarNomesClientes() {
        List<String> lista = new ArrayList<>();
        lista.add("Selecione...");
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT nome FROM clientes ORDER BY nome")) {
            while (rs.next()) lista.add(rs.getString("nome"));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<String> buscarNomesProcedimentos() {
        List<String> lista = new ArrayList<>();
        lista.add("Selecione...");
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT tecnica FROM procedimentos ORDER BY tecnica")) {
            while (rs.next()) lista.add(rs.getString("tecnica"));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<String> buscarNomesFuncionarios() {
        List<String> lista = new ArrayList<>();
        lista.add("Selecione...");
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT nome FROM funcionarios ORDER BY nome")) {
            while (rs.next()) lista.add(rs.getString("nome"));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}