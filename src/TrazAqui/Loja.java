package TrazAqui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Loja implements Entrada, Serializable {
    private String cod;
    private String nome;
    private GPS localizacao;
    private List<Encomenda> pedidos;

    public Loja () {
        this.cod = "";
        this.nome = "";
        this.localizacao = new GPS();
        this.pedidos = new ArrayList<>();
    }

    public Loja(String cod, String nome, GPS localizacao, List<Encomenda> p) {
        this.cod = cod;
        this.nome = nome;
        this.localizacao = localizacao;
        this.setPedidos(p);
    }

    public Loja(Loja a) {
        this.cod = a.getCod();
        this.nome = a.getNome();
        this.localizacao = a.getLocalizacao();
        this.pedidos = a.getPedidos();
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

    public List<Encomenda> getPedidos() {
        List<Encomenda> ret = new ArrayList<>();
        for(Encomenda e: this.pedidos)
            ret.add(e.clone());
        return ret;
    }

    public void setPedidos(List<Encomenda> pedidos) {
        this.pedidos = new ArrayList<>();
        for(Encomenda e: pedidos)
            this.pedidos.add(e.clone());
    }

    public void addPedido(Encomenda a) {
        this.pedidos.add(a.clone());
    }

    public void removePedido(String cod) {
        this.pedidos.removeIf(e -> e.getCod().equals(cod));
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

    public String toStringNome() {
        return "Loja";
    }

}