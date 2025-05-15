package org.example.model;

import org.example.utils.Data;

public abstract class Medida {
    private Data dataRegisto;
    private Paciente paciente;
    private TecnicoSaude tecnico;

    public Medida(Data dataRegisto, Paciente paciente, TecnicoSaude tecnico) {
        this.dataRegisto = dataRegisto;
        this.paciente = paciente;
        this.tecnico = tecnico;
    }
    public Data getDataRegisto() {
        return dataRegisto;
    }
    public void setDataRegisto(Data dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public TecnicoSaude getTecnico() {
        return tecnico;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Data do Registo=").append(dataRegisto);
        sb.append(", Paciente: ").append(paciente);
        sb.append(", Técnico: ").append(tecnico);
        return sb.toString();
    }
}
