package org.example.model;

public class Procedimento {
    private Integer id;
    private String tecnica;
    private String duracao;
    private String valor;

    public Procedimento() {}

    public Procedimento(String tecnica, String duracao, String valor) {
        this.tecnica = tecnica;
        this.duracao = duracao;
        this.valor = valor;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) { this.tecnica = tecnica; }
    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}