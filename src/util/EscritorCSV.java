package util;
import java.io.*;

public class EscritorCSV {

    private static final String RUTA_FICHEROS = "resources/ficheros/";

    public static void escribirValor(String nombre, double valor) {
        String ruta = RUTA_FICHEROS + nombre + ".csv";

        try (RandomAccessFile file = new RandomAccessFile(ruta, "rw")) {
            long posicion = 0;
            String linea;
            while ((linea = file.readLine()) != null) {
                posicion = file.getFilePointer();
            }
            file.seek(posicion);
            file.writeBytes(System.lineSeparator() + ";" + String.valueOf(valor));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





