package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ficheiro {
    // Método para escrever uma lista de objetos em um arquivo
    public static <T> void writeToFile(String filePath, List<T> dados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : dados) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Método para ler dados de um arquivo e retornar uma lista de Strings
    public static List<String> readFromFile(String filePath) {
        List<String> dados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dados.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return dados;
    }
}
