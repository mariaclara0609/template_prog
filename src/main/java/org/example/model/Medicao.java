package org.example.model;

import org.example.utils.Data;

public interface Medicao {
    Data getDataRegisto();
    Paciente getPaciente();
    TecnicoSaude getTecnico();
    String toString();
}
