package domain;

import java.util.*;

public class GestorClientes {

    private LinkedList<Cliente> clientes;

    public GestorClientes(LinkedList<Cliente> clientes){
        this.clientes=clientes;
    }

    public LinkedList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public double getDineroTotal(){
        double dinero=0;
        for (Cliente c:clientes){
            dinero+=c.getLiquido();
        }
        return dinero;
    }

    public void ajustarPerdidas(double p){
        for (Cliente c:clientes){
            c.setLiquido(c.getLiquido()*(1-p));
        }
    }

    public void ajustarGanancias(double p,double cantidad){
        for (Cliente c:clientes){
            c.setLiquido(c.getLiquido()+p*cantidad);
        }
    }

    public int getNumClientes(){
        return clientes.size();
    }

    public void venderActivo(Activo a, int numAcciones){
        for (Cliente c: clientes){
            c.vender(a,numAcciones);
        }
    }
    public String getClientesActivos() {
        StringBuilder clientes = new StringBuilder();
        for (Cliente c : getClientes()) {
            clientes.append("<html>").append(c.getNombre()).append(": ");
            for (Activo a : c.getActivos().keySet()) {
                clientes.append(a.getNombre()).append(" (acc: ").append((int) c.getActivos().get(a)[1]).append(") ");
            }
            clientes.append("<br><br><br>");
        }
        return clientes.toString();
    }

    public String getClientesLiquido() {
        StringBuilder clientes = new StringBuilder();
        for (Cliente c : getClientes()) {
            clientes.append("<html>").append(c.getNombre()).append(": ").append(String.format("%.2f", c.getLiquido())).append(" €");
            clientes.append("<br><br><br>");
        }
        return clientes.toString();
    }


    public String getPortfolio(){
        String cadena="PORTFOLIO: ";
        double rentabilidad=0;
        for(Cliente c: this.getClientes()) {
            cadena += "\n\n"+c.toString();
            rentabilidad+=c.calcularTotalRentabilidad();
        }
        cadena+="\n\n\n\nRENTABILIDAD TOTAL: "+String.format("%.2f",100*rentabilidad)+" %";
        return cadena;
    }

    public void venderTodo(){
        for (Cliente c:clientes){
            c.venderTodo();
        }
    }


}
