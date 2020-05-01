package TrazAqui;

import java.util.List;

public class Utilizador implements Entrada {
    //Variaveis de instancia
    private String nome;
    private String cod;
    private GPS localizacao;
    private int numPedidos;


    public Utilizador() {
        this.nome = "";
        this.cod = "";
        this.localizacao = new GPS();
        this.numPedidos=0;
    }

    public Utilizador(String n, String c, GPS pos) {
        this.nome = n;
        this.localizacao = pos;
        this.cod = c;
        this.numPedidos=0;
    }

    public Utilizador(Utilizador u) {
        this.nome = u.getNome();
        this.cod = u.getCod();
        this.localizacao = u.getLocalizacao();
        this.numPedidos=u.getNumPedidos();

    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilizador{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", cod='").append(cod).append('\'');
        sb.append(", localizacao=").append(localizacao);
        sb.append(", numPedidos=").append(numPedidos);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass().equals(o.getClass())) return true;
        Utilizador u = (Utilizador) o;

        return this.localizacao.equals(u.getLocalizacao()) &&
                this.cod.equals(u.getCod()) &&
                this.nome.equals(u.getNome()) &&
                this.numPedidos==u.getNumPedidos();
    }

    public int getNumPedidos() {
        return numPedidos;
    }

    public void setNumPedidos(int numPedidos) {
        this.numPedidos = numPedidos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public GPS getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GPS localizacao) {
        this.localizacao = localizacao;
    }
}
