package org.example.view;

import org.example.controller.EstoqueController;
import org.example.model.Estoque;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelEstoque extends JPanel {
    private JTextField txtItem, txtCategoria, txtQtd, txtQtdMin, txtValidade, txtCusto;
    private DefaultTableModel tableModel;
    private final EstoqueController controller = new EstoqueController();

    public PainelEstoque() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Main.COR_FUNDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelForm = new JPanel(new GridLayout(2, 6, 8, 8));
        panelForm.setBackground(Main.COR_PAINEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Main.COR_BORDA), " Entrada de Material / Insumo ", 0, 0, Main.FONTE_TITULO, Main.COR_PRIMARIA),
                new EmptyBorder(10, 15, 10, 15)
        ));

        panelForm.add(new JLabel("Item/Produto:")); txtItem = new JTextField(); panelForm.add(txtItem);
        panelForm.add(new JLabel("Categoria:")); txtCategoria = new JTextField(); panelForm.add(txtCategoria);
        panelForm.add(new JLabel("Qtd Atual:")); txtQtd = new JTextField(); panelForm.add(txtQtd);

        panelForm.add(new JLabel("Qtd Mínima:")); txtQtdMin = new JTextField(); panelForm.add(txtQtdMin);
        panelForm.add(new JLabel("Validade Cola:")); txtValidade = new JTextField("DD/MM/AAAA"); panelForm.add(txtValidade);
        panelForm.add(new JLabel("Custo Unit (R$):")); txtCusto = new JTextField(); panelForm.add(txtCusto);

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBtn.setBackground(Main.COR_FUNDO);
        JButton btnSalvar = Main.criarBotaoEstilizado("Adicionar ao Estoque");
        btnSalvar.addActionListener(e -> salvar());
        panelBtn.add(btnSalvar);

        JPanel containerTop = new JPanel(new BorderLayout(0, 10));
        containerTop.setBackground(Main.COR_FUNDO);
        containerTop.add(panelForm, BorderLayout.CENTER);
        containerTop.add(panelBtn, BorderLayout.SOUTH);

        add(containerTop, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome Item", "Categoria", "Qtd Estoque", "Qtd Mínima", "Validade Abertura", "Custo Unit."};
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
            List<Estoque> lista = controller.listar();
            for (Estoque e : lista) {
                tableModel.addRow(new Object[]{e.getId(), e.getItem(), e.getCategoria(), e.getQtd(), e.getQtdMin(), e.getValidade(), "R$ " + e.getCusto()});
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void salvar() {
        try {
            controller.cadastrar(txtItem.getText(), txtCategoria.getText(), txtQtd.getText(), txtQtdMin.getText(), txtValidade.getText(), txtCusto.getText());
            JOptionPane.showMessageDialog(this, "Item salvo no estoque!");
            txtItem.setText(""); txtCategoria.setText(""); txtQtd.setText(""); txtQtdMin.setText(""); txtValidade.setText("DD/MM/AAAA"); txtCusto.setText("");
            carregarDados();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}