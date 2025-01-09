package domain;
import util.EscritorCSV;

import java.util.LinkedList;
import java.util.Random;

public class Criptomoneda extends Activo {

    public Criptomoneda(LinkedList<Double> valoresHistorico, String nombre) {

        super(valoresHistorico,nombre);
        Random random=new Random();
        int num_al1 = random.nextInt(2) * 2 - 1;
        int num_al2 = random.nextInt(2) * 2 - 1;
        super.setDistribucion(num_al1*valoresHistorico.getLast()/10,num_al2*valoresHistorico.getLast()/15);

    }

    @Override
    public void actualizar(){
        super.setValor(super.getValor()+super.getDistribucion().generarValor());
        this.valoresHistorico.add(super.getValor());
        EscritorCSV.escribirValor(super.getNombre(), super.getValor());
        //Recalculamos la distribucion a seguir ya que depende del valor del dia anterior
        Random random=new Random();
        int num_al1 = random.nextInt(2) * 2 - 1;
        int num_al2 = random.nextInt(2) * 2 - 1;
        super.setDistribucion(num_al1*super.getValoresHistorico().getLast()/10,num_al2*super.getValoresHistorico().getLast()/15);
    }

    @Override
    public String toString(){
        String cad = "\nCriptomoneda: "+super.toString();
        return cad;
    }

}
