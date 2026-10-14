package org.example.dao;

import org.example.config.ConexaoSQLite;
import org.example.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, instagram, alergia, mapping) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, cliente.getInstagram());
            pstmt.setString(5, cliente.getAlergia());
            pstmt.setString(6, cliente.getMapping());
            pstmt.executeUpdate();
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id DESC";

        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setInstagram(rs.getString("instagram"));
                c.setAlergia(rs.getString("alergia"));
                c.setMapping(rs.getString("mapping"));
                clientes.add(c);
            }
        }
        return clientes;
    }
}