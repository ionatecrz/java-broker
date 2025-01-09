package util;
import java.util.Random;

public class Distribucion{

    private double media;
    private double desviacionTipica;
    private Random random;

    public Distribucion(double media, double desviacionTipica) {
        this.media = media;
        this.desviacionTipica = desviacionTipica;
        this.random = new Random();
    }

    public double generarValor(){
        return media + random.nextGaussian() * desviacionTipica;
    }

}
