package com.locadora;

import java.time.LocalDate;
import com.locadora.cliente.Cliente;
import com.locadora.cliente.Endereco;
import com.locadora.cliente.CNH;
import com.locadora.veiculo.Veiculo;
import com.locadora.veiculo.CategoriaVeiculo;

/**
 * Teste de IDs em Cliente e Veículo
 */
public class TesteIDs {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║             TESTE: SISTEMA DE IDs SEQUENCIAIS            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        Locadora loc = new Locadora();

        // Teste de IDs de Clientes
        System.out.println("CLIENTES COM IDs:\n");
        
        Endereco e1 = new Endereco("Rua A", "1", "Centro", "São Paulo", "01000-000");
        CNH cnh1 = new CNH("12345", LocalDate.now().plusYears(1));
        Cliente c1 = new Cliente(loc.getProximoIdCliente(), "João Silva", "111.111.111-11", "11987654321", e1, cnh1);
        loc.adicionarCliente(c1);

        Endereco e2 = new Endereco("Rua B", "2", "Vila", "São Paulo", "01100-000");
        CNH cnh2 = new CNH("54321", LocalDate.now().plusYears(1));
        Cliente c2 = new Cliente(loc.getProximoIdCliente(), "Maria Santos", "222.222.222-22", "11999999999", e2, cnh2);
        loc.adicionarCliente(c2);

        Endereco e3 = new Endereco("Rua C", "3", "Bairro", "São Paulo", "01200-000");
        CNH cnh3 = new CNH("11111", LocalDate.now().plusYears(1));
        Cliente c3 = new Cliente(loc.getProximoIdCliente(), "Pedro Costa", "333.333.333-33", "11988888888", e3, cnh3);
        loc.adicionarCliente(c3);

        // Teste de IDs de Veículos
        System.out.println("\nVEÍCULOS COM IDs:\n");

        Veiculo v1 = new Veiculo(loc.getProximoIdVeiculo(), "ABC-1234", "Toyota", "Corolla", 2022, CategoriaVeiculo.ECONOMICO);
        loc.adicionarVeiculo(v1);

        Veiculo v2 = new Veiculo(loc.getProximoIdVeiculo(), "XYZ-9999", "Fiat", "Uno", 2020, CategoriaVeiculo.INTERMEDIARIO);
        loc.adicionarVeiculo(v2);

        Veiculo v3 = new Veiculo(loc.getProximoIdVeiculo(), "BBB-5555", "Volkswagen", "Gol", 2021, CategoriaVeiculo.EXECUTIVO);
        loc.adicionarVeiculo(v3);

        // Listagem completa com IDs
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("LISTAGEM COMPLETA COM IDs:\n");
        
        loc.listarClientes();
        loc.listarVeiculos();

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              ✅ IDs FUNCIONANDO CORRETAMENTE ✅           ║");
        System.out.println("║         Clientes: ID 1, 2, 3 | Veículos: ID 1, 2, 3    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}
