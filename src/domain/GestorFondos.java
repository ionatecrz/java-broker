package domain;
import util.LectorCSV;
import util.LimpiadorCSV;

import java.util.LinkedList;

public class GestorFondos {

    public static LinkedList<GestorActivos> iniciarFondos(){
        //Fondos indexados europeos
        LinkedList<Activo> fondosEuropa=new LinkedList<>();

        //EURO STOXX 50
        fondosEuropa.add(new FondoIndexado(LectorCSV.leerValor("EUROSTOXX50"),"EUROSTOXX50"));
        //FTSE 100
        fondosEuropa.add(new FondoIndexado(LectorCSV.leerValor("FTSE100"),"FTSE100"));
        //IBEX35
        fondosEuropa.add(new FondoIndexado(LectorCSV.leerValor("IBEX35"),"IBEX35"));
        //CAC40
        fondosEuropa.add(new FondoIndexado(LectorCSV.leerValor("CAC40"),"CAC40"));
        //AEX
        fondosEuropa.add(new FondoIndexado(LectorCSV.leerValor("AEX"),"AEX"));

        //GESTOR EUROPA
        GestorActivos GestorEuropa=new GestorActivos(fondosEuropa);

        //Fondos indexados americanos
        LinkedList<Activo> fondosAmerica=new LinkedList<>();

        //NASDAQ100
        fondosAmerica.add(new FondoIndexado(LectorCSV.leerValor("NASDAQ100"),"NASDAQ100"));
        //SP500
        fondosAmerica.add(new FondoIndexado(LectorCSV.leerValor("SP500"),"SP500"));
        //SPTSE
        fondosAmerica.add(new FondoIndexado(LectorCSV.leerValor("SPTSE"),"SPTSE"));
        //DJI
        fondosAmerica.add(new FondoIndexado(LectorCSV.leerValor("DJI"),"DJI"));

        //GESTOR AMERICA
        GestorActivos GestorAmerica=new GestorActivos(fondosAmerica);

        //Fondos indexados resto del mundo
        LinkedList<Activo> fondosRestodelMundo=new LinkedList<>();

        //Nikkei225
        fondosRestodelMundo.add(new FondoIndexado(LectorCSV.leerValor("Nikkei225"),"Nikkei225"));
        //SSEC
        fondosRestodelMundo.add(new FondoIndexado(LectorCSV.leerValor("SSEC"),"SSEC"));
        //KOSPI
        fondosRestodelMundo.add(new FondoIndexado(LectorCSV.leerValor("KOSPI"),"KOSPI"));
        //NZSX50
        fondosRestodelMundo.add(new FondoIndexado(LectorCSV.leerValor("NZSX50"),"NZSX50"));

        //GESTOR RESTO
        GestorActivos GestorResto=new GestorActivos(fondosRestodelMundo);

        //Criptomonedas
        LinkedList<Activo> criptomonedas=new LinkedList<>();

        //Bitcoin
        criptomonedas.add(new Criptomoneda(LectorCSV.leerValor("Bitcoin"),"Bitcoin"));
        //Ethereum
        criptomonedas.add(new Criptomoneda(LectorCSV.leerValor("Ethereum"),"Ethereum"));

        //GESTOR CRIPTO
        GestorActivos GestorCripto=new GestorActivos(criptomonedas);

        LinkedList<GestorActivos> gestoresActivos=new LinkedList<>();
        gestoresActivos.add(GestorEuropa);
        gestoresActivos.add(GestorAmerica);
        gestoresActivos.add(GestorResto);
        gestoresActivos.add(GestorCripto);

        for (GestorActivos g:gestoresActivos){
            for (Activo a:g.getActivos()){
                LimpiadorCSV.limpiarCSV(a.getNombre());
            }
        }
        return gestoresActivos;
    }
}
