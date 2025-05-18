package org.example.ui;
//import model.ViagensTop;
//import utils.Utils;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.io.IOException;

public class MenuUI {
    private Hospital hospital;

    public MenuUI(Hospital hospital) {
        this.hospital = hospital;
    }

    public void iniciarMenu() {
        // Exemplo de interação
        carregarDados(); // Carrega dados ao iniciar
        // Outras opções do menu...
        salvarDados(); // Salva dados ao sair
    }

    public void salvarDados() {
        try {
            hospital.salvarEstado("hospital.dat");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try {
            this.hospital = Hospital.carregarEstado("hospital.dat");
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    public void run() {
        String opcao;
        do {
            System.out.println("\n###### MENU #####");
            System.out.println("1. Registar paciente");
            System.out.println("2. Registar técnico de saúde");
            System.out.println("3. Registar medições");
            System.out.println("4. Exibir Lista de Pacientes");
            System.out.println("5. Exibir Lista de Mediçōes");
            System.out.println("6. Alterar Sinais Vitais");
            System.out.println("7. Calcular Score de Gravidade");
            System.out.println("8. Visualizar gráficos de barras das medições");
            System.out.println("0. Sair");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case "1" -> registarPaciente();
                    case "2" -> registarTecnicoSaude();
                    case "3" -> adicionarMedicoes();;
                    case "4" -> exibirListaPacientes();
                    case "5" -> exibirListaMedicoes();
                    case "6" -> alterarSinaisVitais();
                    case "7" -> hospital.calcularScoreGravidade();
                    case "8" -> ExibirGraficos.exibirGraficos(hospital.getMedicoes());
                    case "0" -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    private void alterarSinaisVitais() {
        System.out.println("\n--- Alterar Sinais Vitais ---");

        double percentualFrequencia = lerPercentual("Percentual de alteração para Frequência Cardíaca (%): ");
        double percentualSaturacao = lerPercentual("Percentual de alteração para Saturação de Oxigênio (%): ");
        double percentualTemperatura = lerPercentual("Percentual de alteração para Temperatura Corporal (%): ");

        hospital.alterarSinaisVitais(percentualFrequencia, percentualSaturacao, percentualTemperatura);

        System.out.println("Alteração dos sinais vitais realizada com sucesso!");
    }

    private double lerPercentual(String mensagem) {
        double percentual;
        do {
            percentual = Utils.readDoubleFromConsole(mensagem);
            if (percentual < -100 || percentual > 100) {
                System.out.println("Erro: O percentual deve estar entre -100% e 100%. Tente novamente.");
            }
        } while (percentual < -100 || percentual > 100);
        return percentual;
    }

    private void registarPaciente() {
        System.out.println("\n--- Registar Paciente ---");
        int id = Utils.readIntFromConsole("ID do paciente: ");
        String nome = Utils.readLineFromConsole("Nome do paciente: ");
        String sexo = Utils.readLineFromConsole("Sexo (Masculino/Feminino): ");
        Data nascimento = Utils.readDateFromConsole("Data de nascimento (dd-MM-yyyy): ");
        Data internamento = Utils.readDateFromConsole("Data de internamento (dd-MM-yyyy): ");

        Paciente paciente = new Paciente(id, nome, sexo, nascimento, internamento);
        if (hospital.adicionarPaciente(paciente)) {
            System.out.println("Paciente registrado com sucesso!");
        } else {
            System.out.println("Erro: Paciente já existe ou dados inválidos!");
        }
    }

    private void registarTecnicoSaude() {
        System.out.println("\n--- Registar Técnico de Saúde ---");
        int id = Utils.readIntFromConsole("ID do técnico: ");
        String nome = Utils.readLineFromConsole("Nome do técnico: ");
        String sexo = Utils.readLineFromConsole("Sexo (Masculino/Feminino): ");
        Data nascimento = Utils.readDateFromConsole("Data de nascimento (dd-MM-yyyy): ");
        String especialidade = Utils.readLineFromConsole("Especialidade: ");

        TecnicoSaude tecnico = new TecnicoSaude(id, nome, sexo, nascimento, especialidade);
        if (hospital.adicionarTecnico(tecnico)) {
            System.out.println("Técnico registrado com sucesso!");
        } else {
            System.out.println("Erro: Técnico já existe ou dados inválidos!");
        }
    }
    private void adicionarMedicoes() {
        System.out.println("Escolha o tipo de medição:");
        System.out.println("1. Frequência Cardíaca");
        System.out.println("2. Temperatura");
        System.out.println("3. Saturação de Oxigênio");

        String tipo = Utils.readLineFromConsole("Digite o número da opção: ");
        Data data = new Data(); // Supondo que você tenha um método para obter a data atual
        Paciente paciente = escolherPaciente(); // Método para escolher um paciente
        TecnicoSaude tecnico = escolherTecnico(); // Método para escolher um técnico

        switch (tipo) {
            case "1":
                double frequencia = Double.parseDouble(Utils.readLineFromConsole("Digite a frequência cardíaca: "));
                FrequenciaCardiaca fc = new FrequenciaCardiaca(data, frequencia, paciente, tecnico);
                hospital.adicionarMedicao(fc);
                break;
            case "2":
                double temperatura = Double.parseDouble(Utils.readLineFromConsole("Digite a temperatura: "));
                Temperatura temp = new Temperatura(data, temperatura, paciente, tecnico);
                hospital.adicionarMedicao(temp);
                break;
            case "3":
                double saturacao = Double.parseDouble(Utils.readLineFromConsole("Digite a saturação de oxigênio: "));
                SaturacaoOxigenio so = new SaturacaoOxigenio(data, saturacao, paciente, tecnico);
                hospital.adicionarMedicao(so);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
    private Paciente escolherPaciente() {
        System.out.println("Escolha um paciente:");
        for (Paciente paciente : hospital.getPacientes()) {
            System.out.println(paciente.getId() + ". " + paciente.getNome());
        }
        int id = Integer.parseInt(Utils.readLineFromConsole("Digite o ID do paciente: "));
        return hospital.procurarPaciente(id); // Supondo que você tenha um método para procurar paciente pelo ID
    }
    private TecnicoSaude escolherTecnico() {
        System.out.println("Escolha um técnico de saúde:");
        for (TecnicoSaude tecnico : hospital.getTecnicos()) {
            System.out.println(tecnico.getId() + ". " + tecnico.getNome());
        }
        int id = Integer.parseInt(Utils.readLineFromConsole("Digite o ID do técnico: "));
        return hospital.procurarTecnico(id); // Supondo que você tenha um método para procurar técnico pelo ID
    }

    private void exibirListaPacientes() {
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : hospital.getPacientes()) {
            System.out.println(paciente.toString());
        }
    }

    private void exibirListaMedicoes() {
        System.out.println("\nLista de Medições:");
        for (Medicao medicao : hospital.getMedicoes()) {
            System.out.println(medicao.toString());
        }
    }
}
