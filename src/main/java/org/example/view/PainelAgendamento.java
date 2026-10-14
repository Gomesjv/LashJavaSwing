package org.example.view;

import org.example.controller.AgendamentoController;
import org.example.model.Agendamento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PainelAgendamento extends JPanel {
    private JComboBox<String> cbCliente, cbProcedimento, cbFuncionario, cbStatus;
    private JTextField txtDataHora, txtValorCobrado, txtValorSinal;
    private DefaultTableModel tableModel;
    private final AgendamentoController controller = new AgendamentoController();

    public PainelAgendamento() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Main.COR_FUNDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Main.COR_PAINEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Main.COR_BORDA), " Novo Agendamento ",
                        0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(10, 15, 10, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelForm.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        cbCliente = new JComboBox<>();
        panelForm.add(cbCliente, gbc);

        gbc.gridx = 2; gbc.weightx = 0; panelForm.add(new JLabel("Procedimento:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        cbProcedimento = new JComboBox<>();
        panelForm.add(cbProcedimento, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; panelForm.add(new JLabel("Lash Designer:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        cbFuncionario = new JComboBox<>();
        panelForm.add(cbFuncionario, gbc);

        gbc.gridx = 2; gbc.weightx = 0; panelForm.add(new JLabel("Data/Hora:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        txtDataHora = new JTextField("07/10/2026 14:00");
        panelForm.add(txtDataHora, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; panelForm.add(new JLabel("Valor Cobrado (R$):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtValorCobrado = new JTextField("160.00");
        panelForm.add(txtValorCobrado, gbc);

        gbc.gridx = 2; gbc.weightx = 0; panelForm.add(new JLabel("Sinal/Reserva (R$):"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        txtValorSinal = new JTextField("30.00");
        panelForm.add(txtValorSinal, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; panelForm.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        cbStatus = new JComboBox<>(new String[]{"Agendado", "Em Atendimento", "Concluído", "Cancelado"});
        panelForm.add(cbStatus, gbc);

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBtn.setBackground(Main.COR_PAINEL);
        JButton btnAgendar = Main.criarBotaoEstilizado("Confirmar Agendamento");
        btnAgendar.addActionListener(this::salvarAgendamento);
        panelBtn.add(btnAgendar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        panelForm.add(panelBtn, gbc);

        add(panelForm, BorderLayout.NORTH);

        String[] colunas = {"ID", "Cliente", "Procedimento", "Lash Designer", "Data/Hora", "Status", "Valor (R$)", "Sinal (R$)"};
        tableModel = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(tableModel);
        Main.estilizarTabela(tabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(Main.COR_BORDA));
        add(scrollPane, BorderLayout.CENTER);

        carregarDados();
    }

    public void carregarCombos() {
        cbCliente.removeAllItems();
        for (String nome : controller.buscarNomesClientes()) {
            cbCliente.addItem(nome);
        }

        cbProcedimento.removeAllItems();
        for (String tec : controller.buscarNomesProcedimentos()) {
            cbProcedimento.addItem(tec);
        }

        cbFuncionario.removeAllItems();
        for (String func : controller.buscarNomesFuncionarios()) {
            cbFuncionario.addItem(func);
        }
    }

    public void carregarDados() {
        carregarCombos();
        tableModel.setRowCount(0);
        try {
            List<Agendamento> lista = controller.listar();
            for (Agendamento a : lista) {
                tableModel.addRow(new Object[]{
                        a.getId(), a.getCliente(), a.getProcedimento(),
                        a.getFuncionario(), a.getDataHora(), a.getStatus(),
                        a.getValor(), a.getSinal()
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void salvarAgendamento(ActionEvent e) {
        try {
            String clienteNome = cbCliente.getSelectedItem() != null ? cbCliente.getSelectedItem().toString() : "";
            String procNome = cbProcedimento.getSelectedItem() != null ? cbProcedimento.getSelectedItem().toString() : "";
            String funcNome = cbFuncionario.getSelectedItem() != null ? cbFuncionario.getSelectedItem().toString() : "";

            controller.cadastrar(
                    cbCliente.getSelectedIndex(),
                    cbProcedimento.getSelectedIndex(),
                    cbFuncionario.getSelectedIndex(),
                    funcNome,
                    txtDataHora.getText(),
                    cbStatus.getSelectedItem().toString(),
                    txtValorCobrado.getText(),
                    txtValorSinal.getText(),
                    clienteNome,
                    procNome
            );

            JOptionPane.showMessageDialog(this, "Agendamento salvo com sucesso!");
            carregarDados();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}