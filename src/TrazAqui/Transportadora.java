package TrazAqui;

import java.util.List;

public class Transportadora extends Estafeta {
    private double numKms;
    private String NIF;
    private double precoKM;

    public Transportadora() {
        this.numKms = 0;
        this.NIF = "";
        this.precoKM = 0;
    }

    public Transportadora(String cod, String nome, GPS localizacao, double raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda, int[] classificacao, boolean disponivel, boolean certificada, double numKms, String NIF, double precoKM) {
        super(cod, nome, localizacao, raio, encomendasEntregues, pedidosEncomenda, classificacao, disponivel, certificada);
        this.numKms = numKms;
        this.NIF = NIF;
        this.precoKM = precoKM;
    }

    public Transportadora(Transportadora a) {
        super(a);
        this.numKms = a.getNumKms();
        this.NIF = a.getNIF();
        this.precoKM = a.getPrecoKM();
    }

    public double getNumKms() {
        return numKms;
    }

    public void setNumKms(double numKms) {
        this.numKms = numKms;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public double getPrecoKM() {
        return precoKM;
    }

    public void setPrecoKM(double precoKM) {
        this.precoKM = precoKM;
    }

    public double precoEncomenda(double peso, double dist) {
        double total;
        if (peso > 10) total = this.precoKM*dist;
        else total = this.precoKM*dist+2.5;
        return total;
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    public String toStringNome() {
        return "Transportadora";
    }

    public void aumentaKms(GPS e) {
        this.numKms += e.distancia(this.getLocalizacao());
    }
}
