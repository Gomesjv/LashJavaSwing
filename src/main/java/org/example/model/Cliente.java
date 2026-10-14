package org.example.model;

public class Cliente {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String instagram;
    private String alergia;
    private String mapping;

    public Cliente() {}

    public Cliente(String nome, String cpf, String telefone, String instagram, String alergia, String mapping) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.instagram = instagram;
        this.alergia = alergia;
        this.mapping = mapping;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }

    public String getAlergia() { return alergia; }
    public void setAlergia(String alergia) { this.alergia = alergia; }

    public String getMapping() { return mapping; }
    public void setMapping(String mapping) { this.mapping = mapping; }
}