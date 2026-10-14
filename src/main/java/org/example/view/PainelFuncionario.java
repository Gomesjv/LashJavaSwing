package org.example.view;

import org.example.controller.FuncionarioController;
import org.example.model.Funcionario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelFuncionario extends JPanel {
    private JTextField txtNome, txtCpf, txtCargo, txtTelefone, txtComissao;
    private DefaultTableModel tableModel;
    private final FuncionarioController controller = new FuncionarioController();

    public PainelFuncionario() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Main.COR_FUNDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelForm.setBackground(Main.COR_PAINEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Main.COR_BORDA), " Cadastrar Profissional ", 0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(5, 10, 5, 10)
        ));

        panelForm.add(new JLabel("Nome:")); txtNome = new JTextField(10); panelForm.add(txtNome);
        panelForm.add(new JLabel("CPF:")); txtCpf = new JTextField(8); panelForm.add(txtCpf);
        panelForm.add(new JLabel("Cargo:")); txtCargo = new JTextField(10); panelForm.add(txtCargo);
        panelForm.add(new JLabel("Telefone:")); txtTelefone = new JTextField(8); panelForm.add(txtTelefone);
        panelForm.add(new JLabel("Comissão (%):")); txtComissao = new JTextField(4); panelForm.add(txtComissao);

        JButton btnSalvar = Main.criarBotaoEstilizado("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        panelForm.add(btnSalvar);

        add(panelForm, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "CPF", "Cargo", "Telefone", "Comissão (%)"};
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
            List<Funcionario> lista = controller.listar();
            for (Funcionario f : lista) {
                tableModel.addRow(new Object[]{f.getId(), f.getNome(), f.getCpf(), f.getCargo(), f.getTelefone(), f.getComissao() + "%"});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void salvar() {
        try {
            controller.cadastrar(txtNome.getText(), txtCpf.getText(), txtCargo.getText(), txtTelefone.getText(), txtComissao.getText());
            JOptionPane.showMessageDialog(this, "Funcionário salvo com sucesso!");
            txtNome.setText(""); txtCpf.setText(""); txtCargo.setText(""); txtTelefone.setText(""); txtComissao.setText("");
            carregarDados();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}