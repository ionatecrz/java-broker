package domain;
import util.EscritorCSV;

import java.util.*;

public class FondoIndexado extends Activo{

    public FondoIndexado(LinkedList<Double> valoresHistorico,String nombre) {
        super(valoresHistorico,nombre);
        super.setDistribucion(porcentajeMedio(valoresHistorico),porcentajeMedio(valoresHistorico)/200);

    }

    public double porcentajeMedio(LinkedList<Double> valoresHistorico){
        Random random=new Random();
        int sem= random.nextInt(0,50);
        double diferencia=valoresHistorico.get(sem+1)-valoresHistorico.get(sem);
        return diferencia/valoresHistorico.get(sem);
    }

    @Override
    public void actualizar(){//algoritmo para predecir el siguiente valor en base a los valores historicos
        double valorEstimado=super.getDistribucion().generarValor();
        if (valorEstimado>0.1) { //por muy pequeña que sea la posibilidad podría haber un atípico extremo
            setValor(getValor() * 1.1);
        }else{
            setValor(getValor() * (1+valorEstimado));
        }
        this.valoresHistorico.add(super.getValor());
        EscritorCSV.escribirValor(super.getNombre(), super.getValor());
        //Recalculamos la distribucion a seguir ya que depende del valor del dia anterior
        super.setDistribucion(porcentajeMedio(super.getValoresHistorico()),porcentajeMedio(super.getValoresHistorico())/200);
    }

    @Override
    public double getValor() {
        return this.getValoresHistorico().getLast();
    }
    @Override
    public String toString(){
        String cad = "\nFondo Indexado: "+super.toString();
        return cad;
    }
}