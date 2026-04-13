package com.locadora.cliente;

/**
 * Representa um endereço (Objeto de Valor)
 * Imutável - após criado não pode ser modificado
 */
public class Endereco {
    private final String rua;
    private final String numero;
    private final String bairro;
    private final String cidade;
    private final String cep;

    public Endereco(String rua, String numero, String bairro, String cidade, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + " - " + cidade + " (" + cep + ")";
    }
}
