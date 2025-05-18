package org.example.model;

import org.example.utils.Data;

public class Temperatura extends Medida{
    private double temperatura;

    public Temperatura(Data dataRegisto, double temperatura, Paciente paciente, TecnicoSaude tecnicoSaude) {
        super(dataRegisto, paciente, tecnicoSaude);
        setTemperatura(temperatura);
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        if (temperatura < 35 || temperatura > 42) {
            throw new IllegalArgumentException("Temperatura deve estar entre 35 e 42 graus Celsius.");
        }
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTemperatura: " + temperatura;

    }
}
