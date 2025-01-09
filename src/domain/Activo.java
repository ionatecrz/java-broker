package domain;
import util.Distribucion;
import util.LectorCSV;

import java.util.LinkedList;

public abstract class Activo{

    private String nombre;
    protected LinkedList<Double> valoresHistorico;
    private double valor;
    private Distribucion distribucion;

    Activo(LinkedList<Double> valoresHistorico,String nombre) {
        this.valor = valoresHistorico.getLast();
        this.valoresHistorico=valoresHistorico;
        this.nombre=nombre;
        this.distribucion=null;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Distribucion getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(double media,double desv_tip) {
        this.distribucion=new Distribucion(media,desv_tip);
    }

    public LinkedList<Double> getValoresHistorico(){
        return valoresHistorico;
    }

    public void setValoresHistorico(LinkedList<Double> valoresHistorico) {
        this.valoresHistorico = valoresHistorico;
    }

    public abstract void actualizar();

    public double calcularSubida() {
        this.setValoresHistorico(LectorCSV.leerValor(this.getNombre()));
        double porcentaje = ((this.getValoresHistorico().get(51) - this.getValoresHistorico().get(50)) / this.getValoresHistorico().get(50)) * 100;
        return porcentaje;
    }

    public boolean calcularSignoSubida(){
        return (this.calcularSubida()>=0);
    }

    public String toString(){
        String cad = "\n" + this.getNombre()+"\nValor: "+valor;
        return cad;
    }
}

