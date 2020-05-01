package TrazAqui;

import java.util.LinkedList;
import java.util.Queue;

public class LojaFilaEspera extends Loja implements Entrada {
    private Queue<Entrada> listaEspera;
    private double tempoEspera;

    public Queue<Entrada> getListaEspera() {
        Queue<Entrada> ret = new LinkedList<>();
        for(Entrada e: listaEspera)
            ret.add(e.clone());
        return ret;
    }

    public void setListaEspera(Queue<Entrada> listaEspera) {
        this.listaEspera = new LinkedList<>();
        for(Entrada e: listaEspera)
            this.listaEspera.add(e.clone());
    }

    public double getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(double tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
}