package org.example.model;

import org.example.utils.Data;

public class SaturacaoOxigenio extends Medida{
    private double saturacao;

    public SaturacaoOxigenio(Data dataRegisto, double saturacao, Paciente paciente, TecnicoSaude tecnicoSaude) {
        super(dataRegisto, paciente, tecnicoSaude);
        setSaturacao(saturacao);
    }

    public double getSaturacao() {
        return saturacao;
    }

    public void setSaturacao(double saturacao) {
        if (saturacao < 0 || saturacao > 100) {
            throw new IllegalArgumentException("Saturação deve estar entre 0 e 100%.");
        }
        this.saturacao = saturacao;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSaturação de Oxigênio: " + saturacao;
    }
}
