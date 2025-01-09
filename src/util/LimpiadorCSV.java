package util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LimpiadorCSV {

    private static final String RUTA_DIRECTORIO = "resources/ficheros/";

    public static void limpiarCSV(String nombreArchivo) {
        String rutaArchivo = RUTA_DIRECTORIO + nombreArchivo + ".csv";
        File archivoOriginal = new File(rutaArchivo);
        List<String> lineasMantener = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            int numLinea = 0;
            while ((linea = reader.readLine()) != null && numLinea < 53) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String lineaModificada = partes[0] + "," + partes[1];
                    lineasMantener.add(lineaModificada);
                } else if (partes.length == 1) {
                    lineasMantener.add(partes[0]);
                }
                numLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoOriginal, false))) {
            for (String linea : lineasMantener) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
