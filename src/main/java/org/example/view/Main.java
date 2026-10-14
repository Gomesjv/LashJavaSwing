package org.example.view;

import org.example.config.ConexaoSQLite;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Main extends JFrame {
    public static final Color COR_PRIMARIA = new Color(156, 39, 176);    
    public static final Color COR_HOVER = new Color(123, 31, 162);        
    public static final Color COR_FUNDO = new Color(250, 248, 251);       
    public static final Color COR_PAINEL = Color.WHITE;                  
    public static final Color COR_TEXTO = new Color(50, 50, 50);          
    public static final Color COR_BORDA = new Color(230, 220, 235);       

    public static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_NEGRITO = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 14);

    public Main() {
        setTitle("LashStudio - Gestão Completa (MVC)");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);

        ConexaoSQLite.criarTabelas();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(FONTE_NEGRITO);
        tabbedPane.setBackground(COR_FUNDO);

        PainelAgendamento painelAgendamento = new PainelAgendamento();
        PainelClienteAnamnese painelCliente = new PainelClienteAnamnese();
        PainelProcedimento painelProcedimento = new PainelProcedimento();
        PainelFuncionario painelFuncionario = new PainelFuncionario();
        PainelEstoque painelEstoque = new PainelEstoque();

        tabbedPane.addTab(" Agenda & Atendimentos ", painelAgendamento);
        tabbedPane.addTab(" Clientes & Anamnese ", painelCliente);
        tabbedPane.addTab(" Procedimentos ", painelProcedimento);
        tabbedPane.addTab(" Equipe / Funcionários ", painelFuncionario);
        tabbedPane.addTab(" Estoque de Insumos ", painelEstoque);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                if (index == 0) painelAgendamento.carregarDados();
                else if (index == 1) painelCliente.carregarDados();
                else if (index == 2) painelProcedimento.carregarDados();
                else if (index == 3) painelFuncionario.carregarDados();
                else if (index == 4) painelEstoque.carregarDados();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
        }

        UIManager.put("Label.font", FONTE_PADRAO);
        UIManager.put("Button.font", FONTE_NEGRITO);
        UIManager.put("TextField.font", FONTE_PADRAO);
        UIManager.put("ComboBox.font", FONTE_PADRAO);

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

    public static JButton criarBotaoEstilizado(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(FONTE_NEGRITO);
        btn.setBackground(COR_PRIMARIA);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_PRIMARIA, 1, true),
                new EmptyBorder(8, 16, 8, 16)
        ));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(COR_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(COR_PRIMARIA);
            }
        });
        return btn;
    }

    public static void estilizarTabela(JTable tabela) {
        tabela.setFont(FONTE_PADRAO);
        tabela.setRowHeight(32);
        tabela.setShowVerticalLines(false);
        tabela.setGridColor(new Color(240, 240, 240));
        tabela.setSelectionBackground(new Color(243, 229, 245));
        tabela.setSelectionForeground(COR_TEXTO);

        JTableHeader header = tabela.getTableHeader();
        header.setFont(FONTE_NEGRITO);
        header.setBackground(new Color(245, 235, 248));
        header.setForeground(COR_TEXTO);
        header.setPreferredSize(new Dimension(header.getWidth(), 36));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}