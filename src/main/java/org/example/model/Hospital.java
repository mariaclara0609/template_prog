package org.example.model;

import org.example.utils.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L; // Garantir compatibilidade de serialização
    private String nome;
    private final List<Paciente> lstPacientes;
    private final List<Medida> lstMedicoes;
    private final List<TecnicoSaude> lstTecnicos;

    public Hospital(String nome) {
        this.nome = nome;
        this.lstPacientes = new ArrayList<>();
        this.lstMedicoes = new ArrayList<>();
        this.lstTecnicos = new ArrayList<>();
    }

    // Método para salvar o estado do hospital
    public void salvarEstado(String caminhoArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(this);
        }
    }

    // Método para carregar o estado do hospital
    public static Hospital carregarEstado(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (Hospital) ois.readObject();
        }
    }

    // Método para adicionar um paciente
    public boolean adicionarPaciente(Paciente paciente) {
        if (paciente == null || lstPacientes.contains(paciente)) {
            return false;
        }
        return lstPacientes.add(paciente);
    }

    // Adicionar técnico de saúde
    public boolean adicionarTecnico(TecnicoSaude tecnico) {
        if (tecnico != null && procurarTecnico(tecnico.getId()) == null) {
            lstTecnicos.add(tecnico);
            return true;
        }
        return false;
    }

    // Método para adicionar medição
    public boolean adicionarMedicao(Medida medida) {
        if (medida != null && procurarPaciente(medida.getPaciente().getId()) != null && procurarTecnico(medida.getTecnico().getId()) != null) {
            lstMedicoes.add(medida);
            return true;
        }
        return false;
    }

    // Método para procurar paciente pelo ID
    public Paciente procurarPaciente(int id) {
        for (Paciente paciente : lstPacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    // Método para procurar técnico pelo ID
    public TecnicoSaude procurarTecnico(int id) {
        for (TecnicoSaude tecnico : lstTecnicos) {
            if (tecnico.getId() == id) {
                return tecnico;
            }
        }
        return null;
    }

    // Método para alterar os sinais vitais
    public void alterarSinaisVitais(double percentualFrequencia, double percentualSaturacao, double percentualTemperatura) {
        for (Medida medida : lstMedicoes) {
            if (medida instanceof FrequenciaCardiaca) {
                FrequenciaCardiaca freq = (FrequenciaCardiaca) medida;
                double novaFrequencia = freq.getFrequencia() * (1 + percentualFrequencia / 100);
                freq.setFrequencia(novaFrequencia);
            }
            if (medida instanceof SaturacaoOxigenio) {
                SaturacaoOxigenio saturacao = (SaturacaoOxigenio) medida;
                double novaSaturacao = saturacao.getSaturacao() * (1 + percentualSaturacao / 100);
                saturacao.setSaturacao(novaSaturacao);
            }
            if (medida instanceof Temperatura) {
                Temperatura temperatura = (Temperatura) medida;
                double novaTemperatura = temperatura.getTemperatura() * (1 + percentualTemperatura / 100);
                temperatura.setTemperatura(novaTemperatura);
            }
        }
    }

    // Verificar se a lista contém um paciente com o ID dado
    public boolean listaContemPaciente(int id) {
        return procurarPaciente(id) != null;
    }

    public List<Paciente> getPacientes() {
        return lstPacientes;
    }

    public List<Medida> getMedicoes() {
        return lstMedicoes;
    }

    // Método para calcular o Score de Gravidade
    public double calcularScoreDeGravidade(double frequenciaCardiaca, double temperatura, double saturacaoOxigenio) {
        // Atribuir os valores de FC_score
        double FC_score;
        if (frequenciaCardiaca < 60 || frequenciaCardiaca > 100) {
            FC_score = 1.0;
        } else if (frequenciaCardiaca >= 60 && frequenciaCardiaca <= 100) {
            FC_score = 0.0;
        } else {
            FC_score = 0.5; // Exemplo para outros casos
        }

        // Atribuir os valores de T_score
        double T_score;
        if (temperatura < 36 || temperatura > 37.5) {
            T_score = 1.0;
        } else {
            T_score = 0.0;
        }

        // Atribuir os valores de SpO2_score
        double SpO2_score;
        if (saturacaoOxigenio < 90) {
            SpO2_score = 1.0;
        } else if (saturacaoOxigenio >= 90 && saturacaoOxigenio <= 95) {
            SpO2_score = 0.5;
        } else {
            SpO2_score = 0.0;
        }

        // Calcular o Score de Gravidade
        double scoreDeGravidade = (FC_score * 0.3) + (T_score * 0.4) + (SpO2_score * 0.3);

        return scoreDeGravidade;
    }
    public void calcularScoreGravidade() {
        double maiorScore = 0;
        Paciente pacienteComMaiorRisco = null;

        for (Paciente paciente : lstPacientes) {
            double frequencia = 0;
            double temperatura = 0;
            double saturacao = 0;
            int contMedicoes = 0;

            // Percorre as medições para encontrar as associadas ao paciente
            for (Medida medida : lstMedicoes) {
                if (medida.getPaciente().equals(paciente)) {
                    if (medida instanceof FrequenciaCardiaca) {
                        frequencia = ((FrequenciaCardiaca) medida).getFrequencia();
                    } else if (medida instanceof Temperatura) {
                        temperatura = ((Temperatura) medida).getTemperatura();
                    } else if (medida instanceof SaturacaoOxigenio) {
                        saturacao = ((SaturacaoOxigenio) medida).getSaturacao();
                    }
                    contMedicoes++;
                }
            }

            // Calcular o score apenas se houver medições
            if (contMedicoes > 0) {
                double score = calcularScoreDeGravidade(frequencia, temperatura, saturacao);
                if (score > maiorScore) {
                    maiorScore = score;
                    pacienteComMaiorRisco = paciente;
                }
            }
        }

        if (pacienteComMaiorRisco != null) {
            System.out.println("Paciente com maior risco: " + pacienteComMaiorRisco + " com score de gravidade: " + maiorScore);
        } else {
            System.out.println("Nenhum paciente encontrado.");
        }
    }


    // Método para classificar sinais vitais
    public String classificarSinalVital(Medida medida) {
        if (medida instanceof FrequenciaCardiaca) {
            FrequenciaCardiaca freq = (FrequenciaCardiaca) medida;
            if (freq.getFrequencia() < 60 || freq.getFrequencia() > 120) {
                return "Crítico";
            } else if (freq.getFrequencia() >= 100 && freq.getFrequencia() <= 120) {
                return "Atenção";
            } else {
                return "Normal";
            }
        }

        if (medida instanceof Temperatura) {
            Temperatura temp = (Temperatura) medida;
            if (temp.getTemperatura() < 36 || temp.getTemperatura() > 38.5) {
                return "Crítico";
            } else if (temp.getTemperatura() > 37.5 && temp.getTemperatura() <= 38.5) {
                return "Atenção";
            } else {
                return "Normal";
            }
        }

        return "Desconhecido"; // Caso a medida não seja reconhecida
    }

    // Verifica se um paciente está em situação crítica
    private boolean isPacienteCritico(Paciente paciente) {
        for (Medida medida : lstMedicoes) {
            if (medida.getPaciente().equals(paciente)) {
                String classificacao = classificarSinalVital(medida);
                if ("Crítico".equals(classificacao)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método para calcular a percentagem de pacientes críticos
    public double calcularPercentagemCriticos() {
        int totalPacientes = lstPacientes.size();
        if (totalPacientes == 0) {
            return 0.0;
        }

        int pacientesCriticos = 0;
        for (Medida medida : lstMedicoes) {
            if ("Crítico".equals(classificarSinalVital(medida))) {
                pacientesCriticos++;
            }
        }
        return (pacientesCriticos / (double) totalPacientes) * 100;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hospital: ").append(nome);
        sb.append("\nLista de pacientes:").append(lstPacientes);
        sb.append("\nLista de Medições:").append(lstMedicoes);
        sb.append("\nLista de Técnicos de Saúde: ").append(lstTecnicos);
        return sb.toString();
    }
}