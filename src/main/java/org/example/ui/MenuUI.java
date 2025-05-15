/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.example.ui;

//import model.ViagensTop;
//import utils.Utils;

import org.example.model.Hospital;
import org.example.utils.Utils;

import java.io.IOException;

public class MenuUI {
    private Hospital hospital;
    private String opcao;


    public MenuUI(Hospital hospital) {
       this.hospital = hospital;
    }

    public void run()  {
        do {
            System.out.println("###### MENU #####");
            System.out.println("1. Leitura de dados a partir de um ficheiro de texto");
            System.out.println("2. Visualização de dados no ecrã");
            // Adaptar e completar
            System.out.println("0. Terminar programa");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                // Completar
                System.out.println("Selecionou a opção: Leitura de dados a partir de um ficheiro de texto");
            }
            else if (opcao.equals("2")) {
                System.out.println("Selecionou a opção: Visualização de dados no ecrã");
            }
        }
        while (!opcao.equals("0"));
    }
}
