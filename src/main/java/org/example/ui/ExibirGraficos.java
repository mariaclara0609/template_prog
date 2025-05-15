package org.example.ui;

public class ExibirGraficos {
    public static void exibir(double frequenciaCardiaca, double temperaturaCorporal, double saturacaoOxigenio) {
        System.out.println("Frequência cardíaca (bpm): " + gerarPontuacao((int) frequenciaCardiaca));
        System.out.println("Temperatura corporal (ºC): " + gerarPontuacao((int) temperaturaCorporal));
        System.out.println("Saturação de oxigênio (%): " + gerarPontuacao((int) saturacaoOxigenio));
    }

    private static String gerarPontuacao(int valor) {
        StringBuilder indicador = new StringBuilder();
        for (int i = 0; i < valor / 10; i++) { // Cada 10 unidades é representado por um "*"
            indicador.append("*");
        }
        return indicador.toString();
    }
}
