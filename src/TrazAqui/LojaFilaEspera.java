package TrazAqui;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LojaFilaEspera extends Loja implements Entrada {
    private int filaEspera;
    private double tempoEspera;

    public int getListaEspera() {
        return filaEspera;
    }

    public void setListaEspera(int filaEspera) {
        this.filaEspera = filaEspera;
    }

    public double getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(double tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public LojaFilaEspera() {
        super();
        this.filaEspera = ThreadLocalRandom.current().nextInt(11);
        this.tempoEspera = ThreadLocalRandom.current().nextDouble(5.0,30.0);
    }

    public LojaFilaEspera(String cod, String nome, GPS localizacao, List<Encomenda> l, int lis, double tempo) {
        super(cod,nome,localizacao,l);
        this.setListaEspera(lis);
        this.tempoEspera = tempo;
    }

    public LojaFilaEspera(LojaFilaEspera j) {
        super(j);
        this.filaEspera = j.getListaEspera();
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
        sb.append(", lista de espera = ").append(this.filaEspera);
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
                this.filaEspera == that.getListaEspera();
    }

    public int getTamanhoListaEspera() {
        return this.filaEspera;
    }

    public String toStringNome() {
        return "LojaFilaEspera";
    }
}