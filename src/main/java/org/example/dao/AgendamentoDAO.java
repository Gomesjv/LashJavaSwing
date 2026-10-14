package org.example.dao;

import org.example.config.ConexaoSQLite;
import org.example.model.Agendamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    public void salvar(Agendamento a) throws SQLException {
        String sql = "INSERT INTO agendamentos (cliente, procedimento, funcionario, data_hora, status, valor, sinal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, a.getCliente());
            pstmt.setString(2, a.getProcedimento());
            pstmt.setString(3, a.getFuncionario());
            pstmt.setString(4, a.getDataHora());
            pstmt.setString(5, a.getStatus());
            pstmt.setString(6, a.getValor());
            pstmt.setString(7, a.getSinal());
            pstmt.executeUpdate();
        }
    }

    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM agendamentos ORDER BY id DESC";
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agendamento a = new Agendamento();
                a.setId(rs.getInt("id"));
                a.setCliente(rs.getString("cliente"));
                a.setProcedimento(rs.getString("procedimento"));
                a.setFuncionario(rs.getString("funcionario"));
                a.setDataHora(rs.getString("data_hora"));
                a.setStatus(rs.getString("status"));
                a.setValor(rs.getString("valor"));
                a.setSinal(rs.getString("sinal"));
                lista.add(a);
            }
        }
        return lista;
    }
}