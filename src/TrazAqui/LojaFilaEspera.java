package TrazAqui;

import java.util.LinkedList;
import java.util.List;
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

    public LojaFilaEspera() {
        super();
        this.listaEspera = new LinkedList<>();
        this.tempoEspera = 10;
    }

    public LojaFilaEspera(String cod, String nome, GPS localizacao, List<Encomenda> l, Queue<Entrada> lis, double tempo) {
        super(cod,nome,localizacao,l);
        this.setListaEspera(lis);
        this.tempoEspera = tempo;
    }

    public LojaFilaEspera(LojaFilaEspera j) {
        super(j);
        this.listaEspera = j.getListaEspera();
        this.tempoEspera = j.getTempoEspera();
    }

    public LojaFilaEspera clone() {
        return new LojaFilaEspera(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LojaFilaEspera {");
        sb.append("cod = ").append(this.getCod());
        sb.append(", nome = ").append(this.getNome());
        sb.append(", localizacao = ").append(this.getLocalizacao().toString());
        sb.append(", lista de espera = ").append(this.listaEspera.toString());
        sb.append(", tempo de espera = ").append(this.tempoEspera);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LojaFilaEspera)) return false;
        if (!super.equals(o)) return false;
        LojaFilaEspera that = (LojaFilaEspera) o;
        return Double.compare(that.getTempoEspera(), getTempoEspera()) == 0 &&
                this.listaEspera.equals(that.getListaEspera());
    }

    public int getTamanhoListaEspera() {
        return this.listaEspera.size();
    }
}