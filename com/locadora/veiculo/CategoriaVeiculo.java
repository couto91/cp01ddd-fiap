package com.locadora.veiculo;

/**
 * Enumeração das categorias de veículos com seus respectivos valores de diária
 * Tabela de preços:
 * - ECONOMICO: R$ 80,00/dia
 * - INTERMEDIARIO: R$ 120,00/dia
 * - EXECUTIVO: R$ 200,00/dia
 * - MOTO: R$ 50,00/dia
 * - VAN: R$ 180,00/dia
 */
public enum CategoriaVeiculo {
    ECONOMICO(80.00),
    INTERMEDIARIO(120.00),
    EXECUTIVO(200.00),
    MOTO(50.00),
    VAN(180.00);

    private final double valorDiaria;

    CategoriaVeiculo(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }
}
