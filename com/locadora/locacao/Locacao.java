package com.locadora.locacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.locadora.cliente.Cliente;
import com.locadora.veiculo.Veiculo;

/**
 * Representa uma Locação (aluguel) de veículo
 * Entidade que gerencia o contrato entre Cliente e Veículo
 */
public class Locacao {
    private String id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;
    private double valorBase;
    private double multa;

    /**
     * Construtor - registra uma nova locação
     * Valida as regras de negócio antes de criar
     */
    public Locacao(String id, Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, int diasAluguel) {
        // R1: Validar se veículo está disponível
        if (!veiculo.isDisponivel()) {
            throw new IllegalArgumentException("Erro: Veículo " + veiculo.getPlaca() + " já está alugado!");
        }

        // R2: Validar se CNH está válida
        if (!cliente.validarCNH()) {
            throw new IllegalArgumentException("Erro: CNH do cliente " + cliente.getNome() + " está vencida!");
        }

        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataRetirada.plusDays(diasAluguel);
        this.dataRealDevolucao = null;
        this.valorBase = veiculo.getValorDiaria() * diasAluguel;
        this.multa = 0;

        veiculo.setDisponivel(false);
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public double getValorBase() {
        return valorBase;
    }

    public double getMulta() {
        return multa;
    }

    /**
     * Calcula multa por atraso
     * R5: Multa de R$50 por dia de atraso
     */
    private void calcularMulta() {
        if (dataRealDevolucao == null) {
            multa = 0;
            return;
        }

        if (dataRealDevolucao.isAfter(dataPrevistaDevolucao)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
            multa = diasAtraso * 50.00;
        } else {
            multa = 0;
        }
    }

    /**
     * Registra a devolução do veículo
     * Calcula automaticamente a multa se houver atraso
     */
    public void registrarDevolucao(LocalDate dataReal) {
        this.dataRealDevolucao = dataReal;
        calcularMulta();
        veiculo.setDisponivel(true);
    }

    public double calcularValorTotal() {
        return valorBase + multa;
    }

    public long calcularDiasAtraso() {
        if (dataRealDevolucao == null) {
            return 0;
        }
        if (dataRealDevolucao.isAfter(dataPrevistaDevolucao)) {
            return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
        }
        return 0;
    }

    public void exibir() {
        System.out.println("\n=== DETALHES DA LOCAÇÃO ===");
        System.out.println("ID: " + id);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")");
        System.out.println("Retirada: " + dataRetirada);
        System.out.println("Previsão devolução: " + dataPrevistaDevolucao);
        if (dataRealDevolucao != null) {
            System.out.println("Devolução real: " + dataRealDevolucao);
            System.out.println("Dias de atraso: " + calcularDiasAtraso());
        } else {
            System.out.println("Devolução real: Ainda não devolvido");
        }
        System.out.println("Valor base: R$ " + String.format("%.2f", valorBase));
        System.out.println("Multa por atraso: R$ " + String.format("%.2f", multa));
        System.out.println("TOTAL: R$ " + String.format("%.2f", calcularValorTotal()));
        System.out.println("==========================\n");
    }
}
