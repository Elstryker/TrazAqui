package TrazAqui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class  Estado {
    //Variaveis de instancia
    private HashMap<String,Utilizador> utilizadores;
    private HashMap<String,Estafeta> trabalhadores;
    private HashMap<String,Loja> lojas;

    public Estado() {
        this.utilizadores = new HashMap<>();
        this.lojas = new HashMap<>();
        this.trabalhadores = new HashMap<>();
    }

    public Estado(HashMap<String,Utilizador> u,HashMap<String,Estafeta> t,HashMap<String,Loja> l) {
        this.setLojas(l);
        this.setTrabalhadores(t);
        this.setUtilizadores(u);
    }

    public Estado(Estado e) {
        this.setLojas(e.getLojas());
        this.setUtilizadores(e.getUtilizadores());
        this.setTrabalhadores(e.getTrabalhadores());
    }

    public Estado clone() {
        return new Estado(this);
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || !this.getClass().equals(o.getClass())) return false;

        Estado e = (Estado) o;

        return this.lojas.equals(e.getLojas()) &&
                this.trabalhadores.equals(e.getTrabalhadores()) &&
                this.utilizadores.equals(e.getUtilizadores());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Estado{");
        sb.append("utilizadores=").append(utilizadores);
        sb.append(", trabalhadores=").append(trabalhadores);
        sb.append(", lojas=").append(lojas);
        sb.append('}');
        return sb.toString();
    }

    public HashMap<String, Estafeta> getTrabalhadores() {
        HashMap<String,Estafeta> res = new HashMap<>();

        for(Map.Entry<String,Estafeta> aux : this.trabalhadores.entrySet()) {
            res.put(aux.getKey(),aux.getValue());
        }
        return res;
    }

    public void setTrabalhadores(HashMap<String, Estafeta> trabalhadores) {
        this.trabalhadores = new HashMap<>();

        for (Map.Entry<String,Estafeta> aux : trabalhadores.entrySet()) {
            this.trabalhadores.put(aux.getKey(),aux.getValue());
        }
    }

    public void addTrabalhador(Estafeta e) {
        this.trabalhadores.put(e.getCod(),e.clone());
    }

    public void addUtilizador(Utilizador u) {
        this.utilizadores.put(u.getCod(),u.clone());
    }

    public void addLoja(Loja l) {
        this.lojas.put(l.getCod(),l.clone());
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

    public GPS getUserPos(String user) {
        return this.utilizadores.get(user).getLocalizacao();
    }

    public double totalFaturado(Transportadora t, LocalDateTime min, LocalDateTime max) {
        double total=0;
        for (Encomenda e : t.getEncomendasEntregues()) {
            if (e.getData().isAfter(min) && e.getData().isBefore(max)) {
                if (this.lojas.containsKey(e.getLoja())) {
                    Loja l = this.lojas.get(e.getLoja()).clone();
                    Utilizador u = this.utilizadores.get(e.getUtilizador());
                    total += t.precoEncomenda(e.getPeso(),l.getLocalizacao().distancia(u.getLocalizacao()));
                }
            }
        }
        return total;
    }

    public List<Utilizador> getTop10Util() {
        Comparator<Integer> comp = Integer::compareTo;
        TreeMap<Integer,Utilizador> vezes = new TreeMap<>(comp);
        List<Utilizador> res = new ArrayList<>();
        int cont=0;

        for (Map.Entry<String,Utilizador> aux : this.utilizadores.entrySet()) {
            vezes.put(aux.getValue().getNumPedidos(),aux.getValue().clone());
        }

        for (Map.Entry<Integer,Utilizador> aux : vezes.entrySet()) {
            if (cont++==10) break;
            res.add(aux.getValue());
        }

        return res;
    }

    public List<Estafeta> getTop10Trans() {
        Comparator<Double> comp = Double::compareTo;
        TreeMap<Double,Estafeta> vezes = new TreeMap<>(comp);
        List<Estafeta> res = new ArrayList<>();
        int cont=0;

        for (Map.Entry<String,Estafeta> aux : this.trabalhadores.entrySet()) {
            if (aux.getValue() instanceof Transportadora) {
                Transportadora t = (Transportadora) aux.getValue();
                vezes.put(t.getNumKms(),aux.getValue().clone());
            }
        }

        for (Map.Entry<Double,Estafeta> aux : vezes.entrySet()) {
            if (cont++==10) break;
            res.add(aux.getValue());
        }
        return res;
    }

    public Utilizador getConta(String email, String pass) throws IOException {
        FileIO io = new FileIO();

        return this.utilizadores.get(io.validaDados(email,pass));
    }




}
