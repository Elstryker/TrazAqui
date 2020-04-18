package TrazAqui;

import java.util.HashSet;

public class Estado {
    //Variaveis de instancia
    private HashSet<Utilizador> utilizadores;
    private HashSet<Transportadora> transportadoras;
    private HashSet<Loja> lojas;

    public Estado() {
        this.utilizadores = new HashSet<>();
        this.lojas = new HashSet<>();
        this.transportadoras = new HashSet<>();
    }

    public Estado(HashSet<Utilizador> u,HashSet<Transportadora> t,HashSet<Loja> l) {
        this.utilizadores = u;
        this.transportadoras = t;
        this.lojas = l;
    }

    public Estado(Estado e) {
        this.lojas = e.getLojas();
        this.transportadoras = e.getTransportadoras();
        this.utilizadores = e.getUtilizadores();
    }

    public Estado clone() {
        return new Estado(this);
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || !this.getClass().equals(o.getClass())) return false;

        Estado e = (Estado) o;

        return this.lojas.equals(e.getLojas()) && this.transportadoras.equals(e.getTransportadoras()) && this.utilizadores.equals(e.getUtilizadores());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Estado{");
        sb.append("utilizadores=").append(utilizadores);
        sb.append(", transportadoras=").append(transportadoras);
        sb.append(", lojas=").append(lojas);
        sb.append('}');
        return sb.toString();
    }

    public void addUtilizador(Utilizador u) {
        this.utilizadores.add(u.clone());
    }

    public void addTransportadora(Transportadora t) {
        this.transportadoras.add(t.clone());
    }

    public void addLoja(Loja l) {
        this.lojas.add(l.clone());
    }

    public HashSet<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(HashSet<Utilizador> utilizadores) {
        this.utilizadores = new HashSet<>();
        this.utilizadores.addAll(utilizadores);
    }

    public HashSet<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(HashSet<Transportadora> transportadoras) {
        this.transportadoras = new HashSet<>();
        this.transportadoras.addAll(transportadoras);
    }

    public HashSet<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(HashSet<Loja> lojas) {
        this.lojas = new HashSet<>();
        this.lojas.addAll(lojas);
    }
}
