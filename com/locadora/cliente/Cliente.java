package com.locadora.cliente;

/**
 * Representa um Cliente da locadora
 * Entidade com identidade única (CPF)
 * 
 * Regras:
 * - CPF deve ser único no sistema
 * - CNH não pode estar vencida para alugar
 */
public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
    private CNH cnh;

    public Cliente(String nome, String cpf, String telefone, Endereco endereco, CNH cnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = "";
        this.endereco = endereco;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public CNH getCnh() {
        return cnh;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void atualizarEndereco(Endereco novoEndereco) {
        this.endereco = novoEndereco;
    }

    /**
     * Verifica se o cliente pode alugar
     * Regra: CNH não pode estar vencida
     */
    public boolean validarCNH() {
        return cnh.validarVencimento();
    }

    public void exibir() {
        System.out.println("Nome: " + nome + " | CPF: " + cpf + " | Tel: " + telefone);
        if (!email.isEmpty()) {
            System.out.println("Email: " + email);
        }
        System.out.println("Endereço: " + endereco);
        System.out.println("CNH: " + cnh);
    }
}
