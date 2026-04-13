package com.locadora;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.locadora.cliente.Cliente;
import com.locadora.cliente.Endereco;
import com.locadora.cliente.CNH;
import com.locadora.veiculo.Veiculo;
import com.locadora.veiculo.CategoriaVeiculo;

public class Main {
    private static Locadora locadora = new Locadora();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        carregarExemplos();
        
        int opcao = 0;

        while (true) {
            try {
                exibirMenu();
                System.out.flush();
                
                String entrada = scanner.nextLine().trim();
                opcao = Integer.parseInt(entrada);
                
                switch (opcao) {
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        adicionarVeiculo();
                        break;
                    case 3:
                        fazerLocacao();
                        break;
                    case 4:
                        devolverVeiculo();
                        break;
                    case 5:
                        listarClientes();
                        break;
                    case 6:
                        listarVeiculos();
                        break;
                    case 7:
                        listarLocacoes();
                        break;
                    case 8:
                        atualizarCadastroCliente();
                        break;
                    case 0:
                        System.out.println("\nEncerrando...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("\nOpcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada invalida!");
            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
            }
            System.out.println();
        }
    }

    /**
     * Carrega exemplos de clientes e veículos no sistema
     * Facilita os testes e demonstração
     */
    private static void carregarExemplos() {
        System.out.println("Carregando dados de exemplo...\n");
        
        // Clientes de exemplo
        Endereco end1 = new Endereco("Rua A", "100", "Centro", "São Paulo", "01000-000");
        CNH cnh1 = new CNH("12345678", LocalDate.of(2027, 12, 31));
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00", "11987654321", end1, cnh1);
        locadora.adicionarCliente(cliente1);

        Endereco end2 = new Endereco("Rua B", "200", "Vila Nova", "São Paulo", "01100-000");
        CNH cnh2 = new CNH("87654321", LocalDate.of(2026, 6, 15));
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-00", "11999999999", end2, cnh2);
        locadora.adicionarCliente(cliente2);

        // Veículos de exemplo
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Toyota", "Corolla", 2022, CategoriaVeiculo.EXECUTIVO);
        locadora.adicionarVeiculo(veiculo1);

        Veiculo veiculo2 = new Veiculo("XYZ-9999", "Fiat", "Uno", 2020, CategoriaVeiculo.ECONOMICO);
        locadora.adicionarVeiculo(veiculo2);

        Veiculo veiculo3 = new Veiculo("BBB-5555", "Volkswagen", "Gol", 2021, CategoriaVeiculo.INTERMEDIARIO);
        locadora.adicionarVeiculo(veiculo3);

        System.out.println("Dados carregados! Clientes e veiculos ja existentes.\n");
    }

    private static void exibirMenu() {
        System.out.println("========== LOCADORA DE VEICULOS ==========");
        System.out.println("1. Adicionar Cliente");
        System.out.println("2. Adicionar Veiculo");
        System.out.println("3. Fazer Locacao (aluguel)");
        System.out.println("4. Devolver Veiculo (CALCULA TAXA/MULTA)");
        System.out.println("5. Listar Clientes");
        System.out.println("6. Listar Veiculos");
        System.out.println("7. Listar Locacoes");
        System.out.println("8. Atualizar Cadastro de Cliente");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        System.out.flush();
    }

    private static void adicionarCliente() {
        System.out.println("\n--- ADICIONAR CLIENTE ---");
        
        try {
            System.out.print("Nome: ");
            System.out.flush();
            String nome = scanner.nextLine().trim();
            
            System.out.print("CPF (xxx.xxx.xxx-xx): ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Telefone: ");
            System.out.flush();
            String telefone = scanner.nextLine().trim();
            
            System.out.print("Rua: ");
            System.out.flush();
            String rua = scanner.nextLine().trim();
            
            System.out.print("Numero: ");
            System.out.flush();
            String numero = scanner.nextLine().trim();
            
            System.out.print("Bairro: ");
            System.out.flush();
            String bairro = scanner.nextLine().trim();
            
            System.out.print("Cidade: ");
            System.out.flush();
            String cidade = scanner.nextLine().trim();
            
            System.out.print("CEP: ");
            System.out.flush();
            String cep = scanner.nextLine().trim();
            
            System.out.print("CNH (apenas numeros): ");
            System.out.flush();
            String numeroCnh = scanner.nextLine().trim();
            
            System.out.print("Vencimento CNH (dd/MM/yyyy) ex: 28/04/2028: ");
            System.out.flush();
            String dataStr = scanner.nextLine().trim();
            
            if (!dataStr.contains("/")) {
                System.out.println("Erro: Use o formato dd/MM/yyyy (com barras)");
                return;
            }
            
            LocalDate dataVencimento = LocalDate.parse(dataStr, dateFormatter);
            
            Endereco endereco = new Endereco(rua, numero, bairro, cidade, cep);
            CNH cnh = new CNH(numeroCnh, dataVencimento);
            
            // Validação: CNH não pode estar vencida
            if (!cnh.validarVencimento()) {
                System.out.println("Erro: CNH vencida não pode ser cadastrada!");
                System.out.println("Data de vencimento informada: " + dataStr);
                System.out.println("Data atual: " + LocalDate.now().format(dateFormatter));
                return;
            }
            
            Cliente cliente = new Cliente(nome, cpf, telefone, endereco, cnh);
            
            locadora.adicionarCliente(cliente);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void adicionarVeiculo() {
        System.out.println("\n--- ADICIONAR VEICULO ---");
        
        try {
            System.out.print("Placa: ");
            System.out.flush();
            String placa = scanner.nextLine().trim();
            
            System.out.print("Marca: ");
            System.out.flush();
            String marca = scanner.nextLine().trim();
            
            System.out.print("Modelo: ");
            System.out.flush();
            String modelo = scanner.nextLine().trim();
            
            System.out.print("Ano: ");
            System.out.flush();
            int ano = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("Categorias e valores:");
            System.out.println("1 - ECONOMICO (R$ 80,00/dia)");
            System.out.println("2 - INTERMEDIARIO (R$ 120,00/dia)");
            System.out.println("3 - EXECUTIVO (R$ 200,00/dia)");
            System.out.println("4 - MOTO (R$ 50,00/dia)");
            System.out.println("5 - VAN (R$ 180,00/dia)");
            System.out.print("Categoria (1-5): ");
            System.out.flush();
            int opcaoCategoria = Integer.parseInt(scanner.nextLine().trim());
            
            CategoriaVeiculo categoria = null;
            switch (opcaoCategoria) {
                case 1: categoria = CategoriaVeiculo.ECONOMICO; break;
                case 2: categoria = CategoriaVeiculo.INTERMEDIARIO; break;
                case 3: categoria = CategoriaVeiculo.EXECUTIVO; break;
                case 4: categoria = CategoriaVeiculo.MOTO; break;
                case 5: categoria = CategoriaVeiculo.VAN; break;
                default: System.out.println("Categoria invalida!"); return;
            }
            
            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano, categoria);
            locadora.adicionarVeiculo(veiculo);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void fazerLocacao() {
        System.out.println("\n--- FAZER LOCACAO (ALUGUEL) ---");
        
        try {
            System.out.print("CPF do cliente: ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Placa do veiculo: ");
            System.out.flush();
            String placa = scanner.nextLine().trim();
            
            System.out.print("Dias de aluguel: ");
            System.out.flush();
            int dias = Integer.parseInt(scanner.nextLine().trim());
            
            locadora.fazerLocacao(placa, cpf, dias);
            System.out.println("PROXIMA ETAPA: Use a opcao 4 (Devolver Veiculo) para devolucao e calculo de multa");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void devolverVeiculo() {
        System.out.println("\n--- DEVOLVER VEICULO (CALCULA TAXA/MULTA) ---");
        
        try {
            System.out.print("Placa do veiculo: ");
            System.out.flush();
            String placa = scanner.nextLine().trim();
            
            System.out.print("CPF do cliente: ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Houve atraso? (s/n): ");
            System.out.flush();
            String resposta = scanner.nextLine().trim().toLowerCase();
            
            boolean comAtraso = resposta.equals("s");
            int diasAtraso = 0;
            
            if (comAtraso) {
                System.out.print("Quantos dias de atraso? (multa de R$ 50,00 por dia): ");
                System.out.flush();
                diasAtraso = Integer.parseInt(scanner.nextLine().trim());
            }
            
            locadora.devolverVeiculo(placa, cpf, comAtraso, diasAtraso);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        System.out.println();
        locadora.listarClientes();
    }

    private static void listarVeiculos() {
        System.out.println();
        locadora.listarVeiculos();
    }

    private static void listarLocacoes() {
        System.out.println();
        locadora.listarLocacoes();
    }

    private static void atualizarCadastroCliente() {
        System.out.println("\n--- ATUALIZAR CADASTRO DE CLIENTE ---");
        System.out.println("1. Alterar Telefone");
        System.out.println("2. Alterar Email");
        System.out.println("3. Alterar Endereço");
        System.out.print("Escolha (1, 2 ou 3): ");
        System.out.flush();
        
        try {
            String entrada = scanner.nextLine().trim();
            
            if (!entrada.matches("^[1-3]$")) {
                System.out.println("Erro: Digite 1 para Telefone, 2 para Email ou 3 para Endereço!");
                return;
            }
            
            int opcao = Integer.parseInt(entrada);
            
            switch (opcao) {
                case 1:
                    alterarTelefone();
                    break;
                case 2:
                    alterarEmail();
                    break;
                case 3:
                    alterarEndereco();
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void alterarTelefone() {
        System.out.println("\n--- ALTERAR TELEFONE ---");
        
        try {
            System.out.print("CPF do cliente: ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Novo telefone: ");
            System.out.flush();
            String novoTelefone = scanner.nextLine().trim();
            
            locadora.alterarTelefoneCliente(cpf, novoTelefone);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void alterarEmail() {
        System.out.println("\n--- ALTERAR EMAIL ---");
        
        try {
            System.out.print("CPF do cliente: ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Novo email: ");
            System.out.flush();
            String novoEmail = scanner.nextLine().trim();
            
            locadora.alterarEmailCliente(cpf, novoEmail);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void alterarEndereco() {
        System.out.println("\n--- ALTERAR ENDEREÇO ---");
        
        try {
            System.out.print("CPF do cliente: ");
            System.out.flush();
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Rua: ");
            System.out.flush();
            String rua = scanner.nextLine().trim();
            
            System.out.print("Numero: ");
            System.out.flush();
            String numero = scanner.nextLine().trim();
            
            System.out.print("Bairro: ");
            System.out.flush();
            String bairro = scanner.nextLine().trim();
            
            System.out.print("Cidade: ");
            System.out.flush();
            String cidade = scanner.nextLine().trim();
            
            System.out.print("CEP: ");
            System.out.flush();
            String cep = scanner.nextLine().trim();
            
            Endereco novoEndereco = new Endereco(rua, numero, bairro, cidade, cep);
            locadora.alterarEnderecoCliente(cpf, novoEndereco);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
