package org.example.ui;

import org.example.model.Medicao;
import org.example.model.FrequenciaCardiaca;
import org.example.model.Temperatura;
import org.example.model.SaturacaoOxigenio;

import java.util.List;

public class ExibirGraficos {
    public static void exibirGraficos(List<Medicao> medicoes) {
        System.out.println("\n--- Gráficos de Barras das Medições ---");
        for (Medicao medicao : medicoes) {
            String tipo = medicao.getClass().getSimpleName();
            double valor = 0.0;
            if (medicao instanceof FrequenciaCardiaca) {
                valor = ((FrequenciaCardiaca) medicao).getFrequencia();
            } else if (medicao instanceof SaturacaoOxigenio) {
                valor = ((SaturacaoOxigenio) medicao).getSaturacao();
            } else if (medicao instanceof Temperatura) {
                valor = ((Temperatura) medicao).getTemperatura();
            }
            String barras = gerarBarras(valor);
            System.out.printf("%s de %s em %s: %s (%.2f)\n",
                    tipo,
                    medicao.getPaciente().getNome(),
                    medicao.getDataRegisto().toAnoMesDiaString(),
                    valor);
        }
    }
    private static String gerarBarras(double valor) {
        int tamanho = (int) valor; // você pode ajustar escala se quiser barras maiores ou menores
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

        }
