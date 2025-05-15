package org.example.model;

import org.example.utils.Data;

import java.util.Objects;

public class Paciente extends Pessoa  {
    private Data dataInternamento;

    public Paciente(int id, String nome, String sexo, Data dataNascimento, Data dataInternamento) {
        super(id, nome, sexo, dataNascimento);
        this.dataInternamento = dataInternamento;
    }
    public Paciente(Paciente p) {
        super(p);
        this.dataInternamento = p.dataInternamento;
    }

    public Data getDataInternamento() {
        return dataInternamento;
    }

    public void setDataInternamento(Data dataInternamento) {
        this.dataInternamento = dataInternamento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Data de Internamento: ").append(dataInternamento);
        return sb.toString();
    }
}
