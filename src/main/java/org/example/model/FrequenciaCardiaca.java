package org.example.model;

import org.example.utils.Data;

public class FrequenciaCardiaca extends Medida{
    private double frequencia;

    public FrequenciaCardiaca(Data dataRegisto, double frequencia, Paciente paciente, TecnicoSaude tecnicoSaude) {
        super(dataRegisto, paciente, tecnicoSaude);
        this.frequencia = frequencia;
    }
    public double getFrequencia() {
        return frequencia;
    }
    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FrequenciaCardiaca{");
        sb.append(super.toString());
        sb.append("frequencia=").append(frequencia);
        sb.append('}');
        return sb.toString();
    }
}
