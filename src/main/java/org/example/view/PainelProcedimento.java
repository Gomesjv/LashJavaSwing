package org.example.view;

import org.example.controller.ProcedimentoController;
import org.example.model.Procedimento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelProcedimento extends JPanel {
    private JTextField txtTecnica, txtDuracao, txtValor;
    private DefaultTableModel tableModel;
    private final ProcedimentoController controller = new ProcedimentoController();

    public PainelProcedimento() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Main.COR_FUNDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        panelForm.setBackground(Main.COR_PAINEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Main.COR_BORDA), " Novo Procedimento ", 0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(5, 10, 5, 10)
        ));

        panelForm.add(new JLabel("Nome Técnica:")); txtTecnica = new JTextField(12); panelForm.add(txtTecnica);
        panelForm.add(new JLabel("Duração (Min):")); txtDuracao = new JTextField(5); panelForm.add(txtDuracao);
        panelForm.add(new JLabel("Valor Base (R$):")); txtValor = new JTextField(6); panelForm.add(txtValor);

        JButton btnSalvar = Main.criarBotaoEstilizado("Cadastrar Procedimento");
        btnSalvar.addActionListener(e -> salvar());
        panelForm.add(btnSalvar);

        add(panelForm, BorderLayout.NORTH);

        String[] colunas = {"ID", "Técnica", "Duração Estimada", "Valor Base (R$)"};
        tableModel = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(tableModel);
        Main.estilizarTabela(tabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(Main.COR_BORDA));
        add(scrollPane, BorderLayout.CENTER);

        carregarDados();
    }

    public void carregarDados() {
        tableModel.setRowCount(0);
        try {
            List<Procedimento> lista = controller.listar();
            for (Procedimento p : lista) {
                tableModel.addRow(new Object[]{p.getId(), p.getTecnica(), p.getDuracao() + " min", "R$ " + p.getValor()});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void salvar() {
        try {
            controller.cadastrar(txtTecnica.getText(), txtDuracao.getText(), txtValor.getText());
            JOptionPane.showMessageDialog(this, "Procedimento cadastrado com sucesso!");
            txtTecnica.setText(""); txtDuracao.setText(""); txtValor.setText("");
            carregarDados();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}