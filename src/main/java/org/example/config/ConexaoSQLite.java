package org.example.config;

import javax.swing.*;
import java.sql.*;

public class ConexaoSQLite {
    private static final String URL = "jdbc:sqlite:lashstudio.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do SQLite não encontrado no classpath!");
        }
    }

    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            stmt.execute("PRAGMA busy_timeout = 5000;");
        }
        return conn;
    }

    public static void criarTabelas() {
        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT, cpf TEXT, telefone TEXT, instagram TEXT, alergia TEXT, mapping TEXT);";

        String sqlProcedimentos = "CREATE TABLE IF NOT EXISTS procedimentos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "tecnica TEXT, duracao TEXT, valor TEXT);";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlClientes);
            stmt.execute(sqlProcedimentos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar ou criar as tabelas no SQLite:\n" + e.getMessage(),
                    "Erro SQLite", JOptionPane.ERROR_MESSAGE);
        }
    }
}