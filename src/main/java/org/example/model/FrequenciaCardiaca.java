package org.example.model;
import org.example.utils.Data;

/**
 * Representa uma medição de frequência cardíaca associada a um paciente.
 */
public class FrequenciaCardiaca extends Medida{
    private double frequencia;

    public FrequenciaCardiaca(Data dataRegisto, double frequencia, Paciente paciente, TecnicoSaude tecnicoSaude) {
        super(dataRegisto, paciente, tecnicoSaude);
        this.frequencia = frequencia;
    }
    public double getFrequencia() {
        return frequencia;
    }

    /**
     * Define a frequência cardíaca registrada.
     * @param frequencia valor da frequência cardíaca em bpm (batimentos por minuto).
     */
    public void setFrequencia(double frequencia) {
        if (frequencia < 30 || frequencia > 300) {
            throw new IllegalArgumentException("Frequência cardíaca deve estar entre 30 e 300 bpm.");
        }
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFrequência Cardíaca: " + frequencia;
    }
}
