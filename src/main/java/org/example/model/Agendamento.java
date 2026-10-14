package org.example.model;

public class Agendamento {
    private Integer id;
    private String cliente;
    private String procedimento;
    private String funcionario;
    private String dataHora;
    private String status;
    private String valor;
    private String sinal;

    public Agendamento() {}

    public Agendamento(String cliente, String procedimento, String funcionario, String dataHora, String status, String valor, String sinal) {
        this.cliente = cliente;
        this.procedimento = procedimento;
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.status = status;
        this.valor = valor;
        this.sinal = sinal;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getProcedimento() { return procedimento; }
    public void setProcedimento(String procedimento) { this.procedimento = procedimento; }
    public String getFuncionario() { return funcionario; }
    public void setFuncionario(String funcionario) { this.funcionario = funcionario; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
    public String getSinal() { return sinal; }
    public void setSinal(String sinal) { this.sinal = sinal; }
}