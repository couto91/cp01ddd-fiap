package com.locadora.veiculo;

/**
 * Representa um Veículo da locadora
 * Entidade com identidade única (Placa)
 * 
 * Regras:
 * - Placa deve ser única no sistema
 * - Status indica se está disponível ou alugado
 * - Valor da diária depende da categoria
 */
public class Veiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private CategoriaVeiculo categoria;
    private boolean disponivel;

    public Veiculo(int id, String placa, String marca, String modelo, int ano, CategoriaVeiculo categoria) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.categoria = categoria;
        this.disponivel = true;
    }

    public String getPlaca() {
        return placa;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public double getValorDiaria() {
        return categoria.getValorDiaria();
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void exibir() {
        String status = disponivel ? "Disponível" : "Alugado";
        System.out.println("ID: " + id + " | Placa: " + placa + " | " + marca + " " + modelo + " (" + ano + ")" +
                         " | Categoria: " + categoria + " | R$ " + String.format("%.2f", getValorDiaria()) + "/dia | Status: " + status);
    }
}
