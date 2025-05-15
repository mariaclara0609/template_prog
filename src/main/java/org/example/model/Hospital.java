package org.example.model;

import org.example.utils.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private static final long serialVersionUID = 1L;
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

    // Método para adicionar paciente
    public boolean adicionarPaciente(Paciente paciente) {
        if (paciente != null && procurarPaciente(paciente.getId()) == null) {
            lstPacientes.add(paciente);
            return true;
        }
        return false;
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

    public Paciente procurarPaciente(int id) {
        for (Paciente paciente : lstPacientes) {
            if (paciente.getId() == id) {
                return paciente; // Retorna o paciente assim que o encontra
            }
        }
        return null; // Retorna null se nenhum paciente for encontrado
    }

    public TecnicoSaude procurarTecnico(int id) {
        for (TecnicoSaude tecnico : lstTecnicos) {
            if (tecnico.getId() == id) {
                return tecnico; // Retorna o técnico assim que o encontra
            }
        }
        return null; // Retorna null se nenhum técnico for encontrado
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

    public String classificarGravidade(double scoreDeGravidade) {
        if (scoreDeGravidade > 0.7) {
            return "Gravidade Alta";
        } else if (scoreDeGravidade > 0.3) {
            return "Gravidade Moderada";
        } else {
            return "Gravidade Baixa";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hospital: ").append(nome);
        sb.append("\nLista de pacientes:").append(lstPacientes);
        sb.append(", \nLista de Medições:").append(lstMedicoes);
        sb.append(", \nLista de Técnicos de Saúde: ").append(lstTecnicos);
        return sb.toString();
    }
}
    
    
