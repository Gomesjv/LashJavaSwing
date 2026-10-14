package org.example.model;

public class Funcionario {
    private Integer id;
    private String nome;
    private String cpf;
    private String cargo;
    private String telefone;
    private String comissao;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String cargo, String telefone, String comissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.telefone = telefone;
        this.comissao = comissao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getComissao() { return comissao; }
    public void setComissao(String comissao) { this.comissao = comissao; }
}