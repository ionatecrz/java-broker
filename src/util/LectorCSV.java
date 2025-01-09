package util;

import java.io.*;
import java.util.*;

public class LectorCSV {

    private static final String RUTA_FICHEROS = "resources/ficheros/";

    public static LinkedList<Double> leerValor(String nombre) {
        String ruta = RUTA_FICHEROS + nombre + ".csv";
        LinkedList<Double> columnValues = new LinkedList<>();
        int indice = 1; // Precio está en la segunda columna
        int lineCount = contarLineas(ruta);
        int saltoLineas = Math.max(1, lineCount - 52 - 1);

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum <= saltoLineas) continue;
                String[] parts = line.split(";");
                if (parts.length > indice) {
                    try {
                        double value = Double.parseDouble(parts[indice]);
                        columnValues.add(value);
                    } catch (NumberFormatException e) {
                        System.err.println("Error en la línea: " + line);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return columnValues;
    }

    private static int contarLineas(String ruta) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

