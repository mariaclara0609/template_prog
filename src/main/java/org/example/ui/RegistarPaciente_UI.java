package org.example.ui;

import org.example.model.Hospital;
import org.example.model.Paciente;
import org.example.utils.Data;
import org.example.utils.Utils;

public class RegistarPaciente_UI {
    private Hospital hospital;

    public RegistarPaciente_UI(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() {
        System.out.println("Novo Paciente:");

        Paciente novoPaciente = introduzDados();
        apresentaDados(novoPaciente);

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            if (hospital.adicionarPaciente(novoPaciente)) {
                System.out.println("Dados do paciente guardados com sucesso.");
            } else {
                System.out.println("Não foi possível guardar os dados do paciente.");
            }
        }
    }

    private static Paciente introduzDados() {
        int id = Utils.readIntFromConsole("Introduza o id do paciente: ");
       String nome = Utils.readLineFromConsole("Introduza o nome do paciente: ");
        // Adaptar
        String sexo= "Feminino";
        Data dataNascimento = new Data();
        Data dataInternamento = new Data();
        return new Paciente(id,nome,sexo,dataNascimento, dataInternamento);
    }

    private void apresentaDados(Paciente paciente) {
        System.out.println("Paciente: " + paciente.toString());
    }

}
