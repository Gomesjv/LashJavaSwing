package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelClienteAnamnese extends JPanel {
    private JTextField txtNome, txtCpf, txtTelefone, txtInstagram;
    private JCheckBox chkAlergia;
    private JComboBox<String> cbMapping;
    private DefaultTableModel tableModel;

    private final ClienteController controller;

    public PainelClienteAnamnese() {
        this.controller = new ClienteController();

        setLayout(new BorderLayout(15, 15));
        setBackground(Main.COR_FUNDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelGrid = new JPanel(new GridLayout(1, 2, 15, 15));
        panelGrid.setBackground(Main.COR_FUNDO);

        JPanel pCliente = new JPanel(new GridLayout(5, 2, 8, 8));
        pCliente.setBackground(Main.COR_PAINEL);
        pCliente.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Main.COR_BORDA), " Dados do Cliente ", 0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(10, 15, 10, 15)
        ));

        pCliente.add(new JLabel("Nome Completo:")); txtNome = new JTextField(); pCliente.add(txtNome);
        pCliente.add(new JLabel("CPF:")); txtCpf = new JTextField(); pCliente.add(txtCpf);
        pCliente.add(new JLabel("Telefone/Whats:")); txtTelefone = new JTextField(); pCliente.add(txtTelefone);
        pCliente.add(new JLabel("Instagram:")); txtInstagram = new JTextField("@"); pCliente.add(txtInstagram);

        JButton btnSalvarCliente = Main.criarBotaoEstilizado("Cadastrar Cliente");
        btnSalvarCliente.addActionListener(e -> salvarCliente());
        pCliente.add(new JLabel()); pCliente.add(btnSalvarCliente);

        JPanel pAnamnese = new JPanel(new GridLayout(5, 2, 8, 8));
        pAnamnese.setBackground(Main.COR_PAINEL);
        pAnamnese.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Main.COR_BORDA), " Ficha de Anamnese / Mapping ", 0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(10, 15, 10, 15)
        ));

        chkAlergia = new JCheckBox("Alergia a Cola");
        chkAlergia.setBackground(Main.COR_PAINEL);
        pAnamnese.add(new JLabel("Alergia:")); pAnamnese.add(chkAlergia);

        pAnamnese.add(new JLabel("Estilo Mapping:"));
        cbMapping = new JComboBox<>(new String[]{"Boneca", "Gatinho", "Esquilo", "Natural"});
        pAnamnese.add(cbMapping);

        panelGrid.add(pCliente);
        panelGrid.add(pAnamnese);

        add(panelGrid, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "CPF", "Telefone", "Instagram", "Alergia Cola?", "Mapping Preferido"};
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
            List<Cliente> clientes = controller.listarClientes();
            for (Cliente c : clientes) {
                tableModel.addRow(new Object[]{
                        c.getId(), c.getNome(), c.getCpf(), c.getTelefone(), c.getInstagram(), c.getAlergia(), c.getMapping()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarCliente() {
        try {
            controller.cadastrarCliente(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtInstagram.getText(),
                    chkAlergia.isSelected(),
                    cbMapping.getSelectedItem().toString()
            );

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparFormulario();
            carregarDados();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtInstagram.setText("@");
        chkAlergia.setSelected(false);
    }
}