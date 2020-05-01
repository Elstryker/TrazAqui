package TrazAqui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Transportadora extends Estafeta {
    private boolean certificada;
    private double numKms;
    private String NIF;
    private double precoKM;

    public Transportadora() {
        this.certificada = false;
        this.numKms = 0;
        this.NIF = "";
        this.precoKM = 0;
    }

    public Transportadora(String cod, String nome, GPS localizacao, double raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda, int[] classificacao, boolean disponivel, boolean certificada, double numKms, String NIF, double precoKM) {
        super(cod, nome, localizacao, raio, encomendasEntregues, pedidosEncomenda, classificacao, disponivel);
        this.certificada = certificada;
        this.numKms = numKms;
        this.NIF = NIF;
        this.precoKM = precoKM;
    }

    public Transportadora(Transportadora a) {
        super(a);
        this.certificada = a.isCertificada();
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

    public boolean isCertificada() {
        return certificada;
    }

    public void setCertificada(boolean certificada) {
        this.certificada = certificada;
    }


    public double getPrecoKM() {
        return precoKM;
    }

    public void setPrecoKM(double precoKM) {
        this.precoKM = precoKM;
    }

    // Métodos
    public double precoEncomenda(double peso, double dist) {
        double total = 0;

        if (peso > 10) total = this.precoKM*dist;
        else total = this.precoKM*dist+2.5;

        return total;
    }

    public void aceitaMedicamentos(boolean certificada){
        this.certificada=true;
    }

    public boolean aceitoTransportesMedicamentos(){
        return this.certificada;
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

}
