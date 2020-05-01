package TrazAqui;

import java.util.LinkedList;
import java.util.Objects;

public class Loja implements Entrada {
    private String cod;
    private String nome;
    private GPS localizacao;

    public Loja () {
        this.cod = "";
        this.nome = "";
        this.localizacao = new GPS();
    }

    public Loja(String cod, String nome, double tempoProcessamento, GPS localizacao, LinkedList<Encomenda> l) {
        this.cod = cod;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public Loja(Loja a) {
        this.cod = a.getCod();
        this.nome = a.getNome();
        this.localizacao = a.getLocalizacao();
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
                this.localizacao.equals(loja.getLocalizacao());
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


}