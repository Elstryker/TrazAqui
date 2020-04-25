package TrazAqui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class  Estado {
    //Variaveis de instancia
    private HashSet<Utilizador> utilizadores;
    private HashSet<Transportadora> transportadoras;
    private HashSet<Voluntario> voluntarios;
    private HashMap<String,Loja> lojas;
    private HashSet<String> encAceites;

    public Estado() {
        this.utilizadores = new HashSet<>();
        this.lojas = new HashMap<>();
        this.voluntarios = new HashSet<>();
        this.transportadoras = new HashSet<>();
        this.encAceites = new HashSet<>();
    }

    public Estado(HashSet<Utilizador> u,HashSet<Transportadora> t,HashSet<Voluntario> v,HashMap<String,Loja> l,HashSet<String> hs) {
        this.setLojas(l);
        this.setTransportadoras(t);
        this.setUtilizadores(u);
        this.setVoluntarios(v);
        this.setEncAceites(hs);
    }

    public Estado(Estado e) {
        this.setLojas(e.getLojas());
        this.setTransportadoras(e.getTransportadoras());
        this.setVoluntarios(e.getVoluntarios());
        this.setUtilizadores(e.getUtilizadores());
        this.setEncAceites(e.getEncAceites());
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

    public HashSet<String> getEncAceites() {
        return new HashSet<>(this.encAceites);
    }

    public void setEncAceites(HashSet<String> encAceites) {
        this.encAceites = new HashSet<>(encAceites);
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
        this.lojas.putIfAbsent(l.getCodLoja(),l.clone());
    }

    public void addEncAceite(String s) {
        this.encAceites.add(s);
    }

    public HashSet<Utilizador> getUtilizadores() {
        HashSet<Utilizador> res = new HashSet<>();
        for (Utilizador u : this.utilizadores) {
            res.add(u.clone());
        }
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
        for (Transportadora u : this.transportadoras) {
            res.add(u.clone());
        }
        return res;
    }

    public void setTransportadoras(HashSet<Transportadora> transportadoras) {
        this.transportadoras = new HashSet<>();
        for (Transportadora t : transportadoras) {
            this.transportadoras.add(t.clone());
        }
    }

    public HashMap<String, Loja> getLojas() {
        HashMap<String,Loja> res = new HashMap<>();
        for (Map.Entry<String,Loja> l : this.lojas.entrySet()) {
            res.put(l.getKey(),l.getValue().clone());
        }
        return res;
    }

    public void setLojas(HashMap<String, Loja> lojas) {
        this.lojas = new HashMap<>();
        for (Map.Entry<String,Loja> l : lojas.entrySet()) {
            this.lojas.put(l.getKey(),l.getValue().clone());
        }
    }

    public double totalFaturado(Transportadora t, LocalDateTime min, LocalDateTime max) {
        double total=0;
        for (Encomenda e : t.getEncomendasEntregues()) {
            if (e.getData().isAfter(min) && e.getData().isBefore(max)) {
                if (this.lojas.containsKey(e.getLoja())) {
                    Loja l = this.lojas.get(e.getLoja()).clone();
                    total += t.precoEncomenda(e.getPeso(),l.getLocalizacao().distancia(e.getDest().getPosicao()));
                }
            }
        }
        return total;
    }

    public void addEncomendaLoja(Encomenda e, String loja) {
        this.lojas.get(loja).adicionaEncomenda(e);
    }


}
