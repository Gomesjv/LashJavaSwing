package org.example.dao;

import org.example.config.ConexaoSQLite;
import org.example.model.Procedimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoDAO {
    public void salvar(Procedimento p) throws SQLException {
        String sql = "INSERT INTO procedimentos (tecnica, duracao, valor) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getTecnica());
            pstmt.setString(2, p.getDuracao());
            pstmt.setString(3, p.getValor());
            pstmt.executeUpdate();
        }
    }

    public List<Procedimento> listarTodos() throws SQLException {
        List<Procedimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM procedimentos ORDER BY id DESC";
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Procedimento p = new Procedimento();
                p.setId(rs.getInt("id"));
                p.setTecnica(rs.getString("tecnica"));
                p.setDuracao(rs.getString("duracao"));
                p.setValor(rs.getString("valor"));
                lista.add(p);
            }
        }
        return lista;
    }
}