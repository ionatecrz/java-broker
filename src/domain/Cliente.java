package domain;
import java.util.*;

public class Cliente{

    private String nombre;
    private int id;
    private double liquido;
    private HashMap<Activo,double[]> activos;
    //En la primera posicion de la lista double[] ira el precio de adquisision y en la otra el num de acciones

    public Cliente(String nombre, int id, double liquido, HashMap<Activo, double[]> activos) {
        this.nombre = nombre;
        this.id = id;
        this.liquido = liquido;
        this.activos = activos;
    }

    public void setActivos(HashMap<Activo, double[]> activos) {
        this.activos = activos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }


    public double getLiquido(){
        return liquido;
    }
    public void setLiquido(double liquido){
        this.liquido = liquido;
    }
    public HashMap<Activo, double[]> getActivos(){
        return activos;
    }

    public void venderTodo() {//copiamos la lista porque no podemos borrar eñementos de una misma lista que estamos iterando
        List<Activo> activosParaVender = new ArrayList<>(getActivos().keySet());
        for (Activo a : activosParaVender) {
            vender(a, (int) getActivos().get(a)[1]);
        }
        setActivos(new HashMap<>());
    }

    public void vender(Activo a, int numAcciones) {
        if (numAcciones>0 && this.verificar(a,numAcciones)){
            if (numAcciones<this.getActivos().get(a)[1]){
                double[] acciones = this.getActivos().get(a);
                acciones[1] -= numAcciones;
                this.setLiquido(this.getLiquido() + a.getValor() * numAcciones);
                this.getActivos().put(a, acciones);
            }
            else{
                this.setLiquido(this.getLiquido() + a.getValor() * numAcciones);
                HashMap <Activo,double[]> ac=activos;
                ac.remove(a);
                this.setActivos(ac);
            }
        }
    }

    public boolean verificar(Activo a, int numAcciones) {
        if (this.getActivos() != null && this.getActivos().containsKey(a) && numAcciones <= this.getActivos().get(a)[1]) {
            return true;
        } else {
            return false;
        }
    }


    public void invertir(Activo a, int numAcciones) {
        if (numAcciones>0 && this.getLiquido()>a.getValor()*numAcciones){
            if (this.getActivos().containsKey(a)) {
            double[] acciones=new double[2];
            acciones[0]=a.getValor();
            acciones[1]=this.getActivos().get(a)[1]+numAcciones;
            this.getActivos().put(a,acciones);
            }
            else {
                double[] acciones=new double[2];
                acciones[0]=a.getValor();
                acciones[1]=numAcciones;
                this.getActivos().put(a, acciones);
            }
            this.setLiquido(this.getLiquido() - a.getValor()*numAcciones);
        }
    }

    public double calcularInvertido(Activo a){
        if (this.getActivos().containsKey(a)){
            return this.getActivos().get(a)[0]*this.getActivos().get(a)[1];
        }else{
            return 0;
        }

    }
    public double calcularTotalInvertido() {
        double invertido = 0;
        for (Activo a : this.getActivos().keySet()) {
            invertido+=this.calcularInvertido(a);
        }
        return invertido;
    }

    public double calcularRentabilidad(Activo a){
        return (a.getValor()*this.getActivos().get(a)[1]-this.calcularInvertido(a))/this.calcularInvertido(a);
    }

    public double calcularTotalRentabilidad() {
        double rentabilidadTotal = 0;
        double inversionTotal = calcularTotalInvertido();
        for (Activo activo : this.getActivos().keySet()) {
            double[] infoActivo = this.getActivos().get(activo);
            double valorInvertidoEnActivo = infoActivo[0] * infoActivo[1];
            double rentabilidadActivo = calcularRentabilidad(activo);
            rentabilidadTotal += (valorInvertidoEnActivo / inversionTotal) * rentabilidadActivo; // Rentabilidad ponderada de cada activo
        }

        return rentabilidadTotal;
    }


    public String toString() {
        String cadena = "\nCliente: " + this.getNombre()+"\nID: " + this.getId()+ "\nLíquido: " + String.format("%.2f",this.getLiquido())+" €";
        LinkedList<String> nombreActivos=new LinkedList<>();
        cadena+="\nActivos en cartera: ";
        for (Activo a : this.getActivos().keySet()) {
            nombreActivos.add(a.getNombre());
        }
        for (String s:nombreActivos){
            if(s!=nombreActivos.getLast()){
                cadena+=s+", ";
            }else{
                cadena+=s+".";
            }
        }
        for (Activo a : this.getActivos().keySet()) {
            cadena +="\nRentabilidad de " + a.getNombre()+": "+ String.format("%.2f",100*this.calcularRentabilidad(a))+" %";
        }
        cadena+="\n\nRENTABILIDAD TOTAL CLIENTE: "+String.format("%.2f",100*this.calcularTotalRentabilidad())+" %";
        return cadena;
    }

}


