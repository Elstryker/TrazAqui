package TrazAqui;

import java.util.HashSet;

public class  Estado {
    //Variaveis de instancia
    private HashSet<Utilizador> utilizadores;
    private HashSet<Transportadora> transportadoras;
    private HashSet<Voluntario> voluntarios;
    private HashSet<Loja> lojas;

    public Estado() {
        this.utilizadores = new HashSet<>();
        this.lojas = new HashSet<>();
        this.voluntarios = new HashSet<>();
        this.transportadoras = new HashSet<>();
    }

    public Estado(HashSet<Utilizador> u,HashSet<Transportadora> t,HashSet<Voluntario> v,HashSet<Loja> l) {
        this.setLojas(l);
        this.setTransportadoras(t);
        this.setUtilizadores(u);
        this.setVoluntarios(v);
    }

    public Estado(Estado e) {
        this.lojas = e.getLojas();
        this.transportadoras = e.getTransportadoras();
        this.voluntarios = e.getVoluntarios();
        this.utilizadores = e.getUtilizadores();
    }

    public Estado clone() {
        return new Estado(this);
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || !this.getClass().equals(o.getClass())) return false;

        Estado e = (Estado) o;

        return this.lojas.equals(e.getLojas()) &&
                this.transportadoras.equals(e.getTransportadoras()) &&
                this.utilizadores.equals(e.getUtilizadores()) &&
                this.voluntarios.equals(e.getVoluntarios());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Estado{");
        sb.append("utilizadores=").append(utilizadores);
        sb.append(", transportadoras=").append(transportadoras);
        sb.append(", voluntarios=").append(voluntarios);
        sb.append(", lojas=").append(lojas);
        sb.append('}');
        return sb.toString();
    }

    public void addVoluntario(Voluntario v) {
        this.voluntarios.add(v.clone());
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
        HashSet<Utilizador> res = new HashSet<>();
        res.addAll(this.utilizadores);
        return res;
    }

    public void setUtilizadores(HashSet<Utilizador> utilizadores) {
        this.utilizadores = new HashSet<>();
        for (Utilizador u : utilizadores) {
            this.utilizadores.add(u.clone());
        }
    }

    public HashSet<Voluntario> getVoluntarios() {
        HashSet<Voluntario> v = new HashSet<>();
        for(Voluntario vol: this.voluntarios)
            v.add(vol.clone());
        return v;
    }

    public void setVoluntarios(HashSet<Voluntario> a) {
        HashSet<Voluntario> v = new HashSet<>();
        for(Voluntario vol: a)
            v.add(vol.clone());
        this.voluntarios = v;
    }

    public HashSet<Transportadora> getTransportadoras() {
        HashSet<Transportadora> res = new HashSet<>();
        res.addAll(this.transportadoras);
        return res;
    }

    public void setTransportadoras(HashSet<Transportadora> transportadoras) {
        this.transportadoras = new HashSet<>();
        for (Transportadora t : transportadoras) {
            this.transportadoras.add(t.clone());
        }
    }

    public HashSet<Loja> getLojas() {
        HashSet<Loja> res = new HashSet<>();
        res.addAll(this.lojas);
        return res;
    }

    public void setLojas(HashSet<Loja> lojas) {
        this.lojas = new HashSet<>();
        for (Loja l : lojas) {
            this.lojas.add(l.clone());
        }
    }
}
