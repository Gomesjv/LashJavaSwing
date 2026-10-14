package org.example.dao;

import org.example.config.ConexaoSQLite;
import org.example.model.Estoque;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    public void salvar(Estoque e) throws SQLException {
        String sql = "INSERT INTO estoque (item, categoria, qtd, qtd_min, validade, custo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, e.getItem());
            pstmt.setString(2, e.getCategoria());
            pstmt.setString(3, e.getQtd());
            pstmt.setString(4, e.getQtdMin());
            pstmt.setString(5, e.getValidade());
            pstmt.setString(6, e.getCusto());
            pstmt.executeUpdate();
        }
    }

    public List<Estoque> listarTodos() throws SQLException {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM estoque ORDER BY id DESC";
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setId(rs.getInt("id"));
                e.setItem(rs.getString("item"));
                e.setCategoria(rs.getString("categoria"));
                e.setQtd(rs.getString("qtd"));
                e.setQtdMin(rs.getString("qtd_min"));
                e.setValidade(rs.getString("validade"));
                e.setCusto(rs.getString("custo"));
                lista.add(e);
            }
        }
        return lista;
    }
}