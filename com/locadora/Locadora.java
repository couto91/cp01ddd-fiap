package com.locadora;

import java.util.ArrayList;
import java.time.LocalDate;
import com.locadora.cliente.Cliente;
import com.locadora.veiculo.Veiculo;
import com.locadora.locacao.Locacao;

/**
 * Classe que gerencia a locadora
 * Responsável por:
 * - Armazenar clientes, veículos e locações
 * - Criar locações
 * - Registrar devoluções e calcular multas
 */
public class Locadora {
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Locacao> locacoes;
    private int proximoIdLocacao;

    public Locadora() {
        this.veiculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.locacoes = new ArrayList<>();
        this.proximoIdLocacao = 1;
    }

    /**
     * Adiciona um veículo
     * Regra R4: Placa deve ser única
     */
    public void adicionarVeiculo(Veiculo v) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                System.out.println("Erro: Já existe um veículo com a placa " + v.getPlaca());
                return;
            }
        }
        veiculos.add(v);
        System.out.println("Veículo " + v.getPlaca() + " adicionado!");
    }

    /**
     * Adiciona um cliente
     * Regra R3: CPF deve ser único
     */
    public void adicionarCliente(Cliente c) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(c.getCpf())) {
                System.out.println("Erro: Já existe um cliente com o CPF " + c.getCpf());
                return;
            }
        }
        clientes.add(c);
        System.out.println("Cliente " + c.getNome() + " cadastrado!");
    }

    /**
     * Cria uma locação
     * Regras R1 e R2 são validadas no construtor de Locacao
     */
    public void fazerLocacao(String placa, String cpf, int diasAluguel) {
        Veiculo veiculo = null;
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                veiculo = v;
                break;
            }
        }

        if (veiculo == null) {
            System.out.println("Erro: Veículo com placa " + placa + " não encontrado!");
            return;
        }

        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Erro: Cliente com CPF " + cpf + " não encontrado!");
            return;
        }

        try {
            Locacao locacao = new Locacao(
                "LOC" + proximoIdLocacao,
                cliente,
                veiculo,
                LocalDate.now(),
                diasAluguel
            );
            locacoes.add(locacao);
            proximoIdLocacao++;
            System.out.println("Locação criada com sucesso! ID: " + locacao.getId());
            System.out.println("Valor base: R$ " + String.format("%.2f", locacao.getValorBase()));
            System.out.println("Devolução prevista para: " + locacao.getDataPrevistaDevolucao());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Registra a devolução de um veículo
     * Onde é aplicada a Regra R5 (cálculo de multa por atraso)
     */
    public void devolverVeiculo(String placaVeiculo, String cpfCliente, boolean comAtraso, int diasAtraso) {
        Locacao locacao = null;
        for (Locacao loc : locacoes) {
            if (loc.getVeiculo().getPlaca().equalsIgnoreCase(placaVeiculo) &&
                loc.getCliente().getCpf().equals(cpfCliente) &&
                loc.getDataRealDevolucao() == null) {
                locacao = loc;
                break;
            }
        }

        if (locacao == null) {
            System.out.println("Erro: Locação não encontrada!");
            return;
        }

        LocalDate dataReal = comAtraso ? 
            locacao.getDataPrevistaDevolucao().plusDays(diasAtraso) : 
            locacao.getDataPrevistaDevolucao();

        locacao.registrarDevolucao(dataReal);
        System.out.println("Veículo " + placaVeiculo + " devolvido!");
        locacao.exibir();
    }

    public void listarVeiculos() {
        System.out.println("\n=== VEÍCULOS DA LOCADORA ===");
        for (Veiculo v : veiculos) {
            v.exibir();
        }
        System.out.println();
    }

    public void listarClientes() {
        System.out.println("\n=== CLIENTES CADASTRADOS ===");
        for (Cliente c : clientes) {
            c.exibir();
            System.out.println();
        }
    }

    public void listarLocacoes() {
        System.out.println("\n=== LOCAÇÕES ===");
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação registrada.");
        } else {
            for (Locacao loc : locacoes) {
                loc.exibir();
            }
        }
    }

    public double calcularAluguel(String placa, int dias) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v.getValorDiaria() * dias;
            }
        }
        return 0;
    }
}
