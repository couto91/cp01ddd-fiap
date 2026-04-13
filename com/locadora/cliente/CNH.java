package com.locadora.cliente;

import java.time.LocalDate;

/**
 * Representa a CNH (Carteira Nacional de Habilitação) - Objeto de Valor
 * Imutável - após criada não pode ser modificada
 */
public class CNH {
    private final String numero;
    private final LocalDate dataVencimento;

    public CNH(String numero, LocalDate dataVencimento) {
        this.numero = numero;
        this.dataVencimento = dataVencimento;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Verifica se a CNH está válida (não vencida)
     * Regra de Negócio: Não pode alugar com CNH vencida
     */
    public boolean validarVencimento() {
        return LocalDate.now().isBefore(dataVencimento) || LocalDate.now().isEqual(dataVencimento);
    }

    @Override
    public String toString() {
        return "CNH " + numero + " (Vencimento: " + dataVencimento + ")";
    }
}
