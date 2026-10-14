package org.example.model;

public class Estoque {
    private Integer id;
    private String item;
    private String categoria;
    private String qtd;
    private String qtdMin;
    private String validade;
    private String custo;

    public Estoque() {}

    public Estoque(String item, String categoria, String qtd, String qtdMin, String validade, String custo) {
        this.item = item;
        this.categoria = categoria;
        this.qtd = qtd;
        this.qtdMin = qtdMin;
        this.validade = validade;
        this.custo = custo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getQtd() { return qtd; }
    public void setQtd(String qtd) { this.qtd = qtd; }
    public String getQtdMin() { return qtdMin; }
    public void setQtdMin(String qtdMin) { this.qtdMin = qtdMin; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getCusto() { return custo; }
    public void setCusto(String custo) { this.custo = custo; }
}