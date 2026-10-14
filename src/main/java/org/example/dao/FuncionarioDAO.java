package org.example.dao;

import org.example.config.ConexaoSQLite;
import org.example.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void salvar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, telefone, comissao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCpf());
            pstmt.setString(3, f.getCargo());
            pstmt.setString(4, f.getTelefone());
            pstmt.setString(5, f.getComissao());
            pstmt.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios ORDER BY id DESC";
        try (Connection conn = ConexaoSQLite.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setTelefone(rs.getString("telefone"));
                f.setComissao(rs.getString("comissao"));
                lista.add(f);
            }
        }
        return lista;
    }
}