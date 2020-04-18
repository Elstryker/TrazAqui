package TrazAqui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Loja {
    private int codLoja;
    private String nome;
    private GPS localizacao;
    private Queue<Encomenda> filaEspera;

    public Loja () {
        this.codLoja = 0;
        this.nome = "";
        this.localizacao = new GPS();
        this.filaEspera = new LinkedList<Encomenda>();
    }

    public Loja(int codLoja, String nome, GPS localizacao) {
        this.codLoja = codLoja;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public Loja(Loja a) {
        this.codLoja = a.getCodLoja();
        this.nome = a.getNome();
        this.localizacao = a.getLocalizacao();
        this.setFilaEspera(a.getFilaEspera());
    }

    public LinkedList<Encomenda> getFilaEspera() {
        LinkedList<Encomenda> s = new LinkedList<Encomenda>();
        Iterator<Encomenda> it = s.iterator();
        while(it.hasNext())
            s.add(it.next().clone());
        return s;
    }

    public void setFilaEspera(LinkedList<Encomenda> a) {
        LinkedList<Encomenda> s = new LinkedList<Encomenda>();
        Iterator<Encomenda> it = s.iterator();
        while(it.hasNext())
            s.add(it.next().clone());
        this.filaEspera = s;
    }

    public int getCodLoja() {
        return codLoja;
    }

    public void setCodLoja(int codLoja) {
        this.codLoja = codLoja;
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
        return this.codLoja == loja.getCodLoja() &&
                this.nome.equals(loja.getNome()) &&
                this.localizacao.equals(loja.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codLoja, nome, localizacao);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loja {");
        sb.append("codLoja = ").append(codLoja);
        sb.append(", nome = ").append(nome);
        sb.append(", localizacao = ").append(localizacao);
        sb.append('}');
        return sb.toString();
    }

    public Loja clone() {
        return new Loja(this);
    }
}
