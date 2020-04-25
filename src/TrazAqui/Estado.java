package TrazAqui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class  Estado {
    //Variaveis de instancia
    private HashMap<String,Utilizador> utilizadores;
    private HashMap<String,Transportadora> transportadoras;
    private HashMap<String,Voluntario> voluntarios;
    private HashMap<String,Loja> lojas;
    private HashSet<String> encAceites;

    public Estado() {
        this.utilizadores = new HashMap<>();
        this.lojas = new HashMap<>();
        this.voluntarios = new HashMap<>();
        this.transportadoras = new HashMap<>();
        this.encAceites = new HashSet<>();
    }

    public Estado(HashMap<String,Utilizador> u,HashMap<String,Transportadora> t,HashMap<String,Voluntario> v,HashMap<String,Loja> l, HashSet<String> hs) {
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
        this.voluntarios.put(v.getCodVoluntario(),v.clone());
    }

    public void addUtilizador(Utilizador u) {
        this.utilizadores.put(u.getCodigo(),u.clone());
    }

    public void addTransportadora(Transportadora t) {
        this.transportadoras.put(t.getCodEmpresa(),t.clone());
    }

    public void addLoja(Loja l) {
        this.lojas.put(l.getCodLoja(),l.clone());
    }

    public void addEncAceite(String s) {
        this.encAceites.add(s);
    }

    public HashMap<String, Utilizador> getUtilizadores() {
        HashMap<String,Utilizador> res = new HashMap<>();
        for (Map.Entry<String,Utilizador> u : this.utilizadores.entrySet()) {
            res.put(u.getKey(),u.getValue().clone());
        }
        return res;
    }

    public void setUtilizadores(HashMap<String, Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();
        for (Map.Entry<String,Utilizador> u : utilizadores.entrySet()) {
            this.utilizadores.put(u.getKey(),u.getValue().clone());
        }
    }

    public HashMap<String, Transportadora> getTransportadoras() {
        HashMap<String,Transportadora> res = new HashMap<>();
        for (Map.Entry<String,Transportadora> t : this.transportadoras.entrySet()) {
            res.put(t.getKey(),t.getValue().clone());
        }
        return res;
    }

    public void setTransportadoras(HashMap<String,Transportadora> transportadoras) {
        this.transportadoras = new HashMap<>();
        for (Map.Entry<String, Transportadora> t : transportadoras.entrySet()) {
            this.transportadoras.put(t.getKey(), t.getValue().clone());
        }
    }

    public HashMap<String, Voluntario> getVoluntarios() {
        HashMap<String,Voluntario> res = new HashMap<>();
        for (Map.Entry<String,Voluntario> v : this.voluntarios.entrySet()) {
            res.put(v.getKey(),v.getValue().clone());
        }
        return res;
    }

    public void setVoluntarios(HashMap<String, Voluntario> voluntarios) {
        this.voluntarios = new HashMap<>();
        for (Map.Entry<String, Voluntario> v : voluntarios.entrySet()) {
            this.voluntarios.put(v.getKey(), v.getValue().clone());
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
