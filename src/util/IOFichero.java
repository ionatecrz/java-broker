package util;

import java.io.*;

public class IOFichero {

    public static String read(String path){
        String contenido="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido=contenido+linea+"\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }

    public static void write(String path,String contenido){
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))));
            pw.println(contenido);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
