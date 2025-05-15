/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public void iniciarMenu() {
        // Exemplo de interação
        carregarDados(); // Carrega dados ao iniciar
        // Outras opções do menu...
        salvarDados(); // Salva dados ao sair
    }

    public void run()  {
        String opcao;
        do {
            System.out.println("\n###### MENU #####");
            System.out.println("1. Registar paciente");
            System.out.println("2. Registar técnico de saúde");
            System.out.println("3. Registar Frequência Cardíaca");
            System.out.println("4. Registrar Saturação de Oxigênio");
            System.out.println("5. Registrar Temperatura");
            System.out.println("6. Exibir Lista de Pacientes");
            System.out.println("7. Exibir Lista de Mediçōes");
            System.out.println("0. Sair");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1" -> registarPaciente();
                case "2" -> registarTecnicoSaude();
                case "3" -> registrarFrequenciaCardiaca();
                case "4" -> registrarSaturacaoOxigenio();
                case "5" -> registrarTemperatura();
                case "6" -> exibirListaPacientes();
                case "7" -> exibirListaMedicoes();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("0"));
    }

    private void registarPaciente() {
        System.out.println("\n--- Registrar Paciente ---");
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
        System.out.println("\n--- Registrar Técnico de Saúde ---");
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

    private void registrarFrequenciaCardiaca() {
        System.out.println("\n--- Registrar Frequência Cardíaca ---");
        int idPaciente = Utils.readIntFromConsole("ID do paciente: ");
        int idTecnico = Utils.readIntFromConsole("ID do técnico de saúde: ");
        double frequencia = Utils.readDoubleFromConsole("Frequência cardíaca: ");
        Data data = Utils.readDateFromConsole("Data de registro (dd-MM-yyyy): ");

        Paciente paciente = hospital.procurarPaciente(idPaciente);
        TecnicoSaude tecnico = hospital.procurarTecnico(idTecnico);

        if (paciente != null && tecnico != null) {
            FrequenciaCardiaca freq = new FrequenciaCardiaca(data, frequencia, paciente, tecnico);
            if (hospital.adicionarMedicao(freq)) {
                System.out.println("Frequência cardíaca registrada com sucesso!");
            } else {
                System.out.println("Erro ao registrar a frequência cardíaca.");
            }
        } else {
            System.out.println("Erro: Paciente ou técnico não encontrado!");
        }
    }

    private void registrarSaturacaoOxigenio() {
        System.out.println("\n--- Registrar Saturação de Oxigênio ---");
        int idPaciente = Utils.readIntFromConsole("ID do paciente: ");
        int idTecnico = Utils.readIntFromConsole("ID do técnico de saúde: ");
        double saturacao = Utils.readDoubleFromConsole("Saturação de oxigênio: ");
        Data data = Utils.readDateFromConsole("Data de registro (dd-MM-yyyy): ");

        Paciente paciente = hospital.procurarPaciente(idPaciente);
        TecnicoSaude tecnico = hospital.procurarTecnico(idTecnico);

        if (paciente != null && tecnico != null) {
            SaturacaoOxigenio saturacaoOxigenio = new SaturacaoOxigenio(data, saturacao, paciente, tecnico);
            if (hospital.adicionarMedicao(saturacaoOxigenio)) {
                System.out.println("Saturação de oxigênio registrada com sucesso!");
            } else {
                System.out.println("Erro ao registrar a saturação de oxigênio.");
            }
        } else {
            System.out.println("Erro: Paciente ou técnico não encontrado!");
        }
    }

    private void registrarTemperatura() {
        System.out.println("\n--- Registrar Temperatura ---");
        int idPaciente = Utils.readIntFromConsole("ID do paciente: ");
        int idTecnico = Utils.readIntFromConsole("ID do técnico de saúde: ");
        double temperatura = Utils.readDoubleFromConsole("Temperatura corporal: ");
        Data data = Utils.readDateFromConsole("Data de registro (dd-MM-yyyy): ");

        Paciente paciente = hospital.procurarPaciente(idPaciente);
        TecnicoSaude tecnico = hospital.procurarTecnico(idTecnico);

        if (paciente != null && tecnico != null) {
            Temperatura temperaturaMedicao = new Temperatura(data, temperatura, paciente, tecnico);
            if (hospital.adicionarMedicao(temperaturaMedicao)) {
                System.out.println("Temperatura registrada com sucesso!");
            } else {
                System.out.println("Erro ao registrar a temperatura.");
            }
        } else {
            System.out.println("Erro: Paciente ou técnico não encontrado!");
        }
    }

    private void exibirListaPacientes() {
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : hospital.getPacientes()) {
            System.out.println(paciente.toString());
        }
    }

    private void exibirListaMedicoes() {
        System.out.println("\nLista de Medições:");
        for (Medida medida : hospital.getMedicoes()) {
            System.out.println(medida.toString());
        }
    }
}
