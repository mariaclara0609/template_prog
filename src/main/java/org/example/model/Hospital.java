package org.example.model;

import org.example.utils.Data;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
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
        if (tecnico != null && procurarTecnico(tecnico.getId()) != null) {
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

    @Override
    public String toString() {
        return "Hospital{" +
                "nome='" + nome + '\'' +
                ", lstPacientes=" + lstPacientes +
                ", lstMedicoes=" + lstMedicoes +
                ", lstTecnicos=" + lstTecnicos +
                '}';
    }
}
    
    
