package TrazAqui;

import java.util.LinkedList;
import java.util.Objects;

public class Loja {
    private String cod;
    private String nome;
    private GPS localizacao;
    private double tempoProcessamento;
    private boolean temFila;
    private LinkedList<Encomenda> filaEspera;

    public Loja () {
        this.cod = "";
        this.nome = "";
        this.localizacao = new GPS();
        this.tempoProcessamento = 0;
        this.temFila = true;
        this.filaEspera = new LinkedList<>();
    }

    public Loja(String cod, String nome, double tempoProcessamento, GPS localizacao, boolean temFila, LinkedList<Encomenda> l) {
        this.cod = cod;
        this.nome = nome;
        this.localizacao = localizacao;
        this.tempoProcessamento = tempoProcessamento;
        this.temFila = temFila;
        if(temFila)
            this.setFilaEspera(l);
        else this.filaEspera = null;
    }

    public Loja(Loja a) {
        this.cod = a.getCod();
        this.nome = a.getNome();
        this.localizacao = a.getLocalizacao();
        this.tempoProcessamento = a.getTempoProcessamento();
        this.temFila = a.getTemFila();
        if(this.temFila)
            this.setFilaEspera(a.getFilaEspera());
        else this.filaEspera = null;
    }

    public boolean getTemFila() {
        return temFila;
    }

    public void setTemFila(boolean temFila) {
        this.temFila = temFila;
    }

    public LinkedList<Encomenda> getFilaEspera() {
        LinkedList<Encomenda> s = new LinkedList<Encomenda>();
        for (Encomenda encomenda : this.filaEspera) s.add(encomenda.clone());
        return s;
    }

    public void setFilaEspera(LinkedList<Encomenda> a) {
        LinkedList<Encomenda> s = new LinkedList<Encomenda>();
        for (Encomenda encomenda : a) s.add(encomenda.clone());
        this.filaEspera = s;
    }

    public double getTempoProcessamento() {
        return tempoProcessamento;
    }

    public void setTempoProcessamento(double tempoProcessamento) {
        this.tempoProcessamento = tempoProcessamento;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GPS getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GPS localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loja loja = (Loja) o;
        return this.cod.equals(loja.getCod()) &&
                this.nome.equals(loja.getNome()) &&
                this.tempoProcessamento == loja.getTempoProcessamento() &&
                this.localizacao.equals(loja.getLocalizacao()) &&
                this.filaEspera.equals(loja.getFilaEspera());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, nome, localizacao);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loja {");
        sb.append("cod = ").append(cod);
        sb.append(", nome = ").append(nome);
        sb.append(", localizacao = ").append(localizacao);
        sb.append('}');
        return sb.toString();
    }

    public Loja clone() {
        return new Loja(this);
    }

    public void adicionaEncomenda(Encomenda a) {
        this.filaEspera.add(a.clone());
    }

    public void removeEncomenda() {
        if(!this.filaEspera.isEmpty())
            this.filaEspera.remove();
    }

    public int sizeQueue() {
        return this.filaEspera.size();
    }

    public boolean haveEncomenda(String userID) {
        boolean ret = false;
        for(Encomenda e: this.filaEspera)
            if(e.getDest().getCodigo().equals(userID)) ret = true;
        return ret;
    }

    public double tempoDeEspera(String codEnc) {
        double ret = 0;
        for(Encomenda e: this.filaEspera)
            if(e.getCodigo().equals(codEnc)) {
                int x = this.filaEspera.indexOf(e);
                ret = (x+1) * this.tempoProcessamento;
            }
        return ret;
    }


}