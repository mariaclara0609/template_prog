package org.example.model;

import org.example.utils.Data;

public class TecnicoSaude extends Pessoa{
    private String especialidade;

    public TecnicoSaude(int id, String nome, char sexo, Data dataNascimento, String especialidade) {
        super(id, nome, sexo, dataNascimento);
        this.especialidade = especialidade;
    }
    public TecnicoSaude(TecnicoSaude t) {
        super(t);
        this.especialidade = t.especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TecnicoSaude{");
        sb.append(super.toString());
        sb.append(", especialidade='").append(especialidade).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
