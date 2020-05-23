package TrazAqui;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class  Estado implements Serializable {
    private HashMap<String,Utilizador> utilizadores;
    private HashMap<String,Estafeta> trabalhadores;
    private HashMap<String,Loja> lojas;
    private Entrada login;

    public Estado() {
        this.utilizadores = new HashMap<>();
        this.lojas = new HashMap<>();
        this.trabalhadores = new HashMap<>();
        this.login = null;
    }

    public Estado(HashMap<String,Utilizador> u,HashMap<String,Estafeta> t,HashMap<String,Loja> l, Entrada a) {
        this.setLojas(l);
        this.setTrabalhadores(t);
        this.setUtilizadores(u);
        this.login = a.clone();
    }

    public Estado(Estado e) {
        this.setLojas(e.getLojas());
        this.setUtilizadores(e.getUtilizadores());
        this.login = e.getLogin();
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

    public Entrada getLogin() {
        return this.login == null ? null : this.login.clone();
    }

    public void setLogin(Entrada login) {
        this.login = login.clone();
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

    public Encomenda removeEncomendaLoja(String codEnc) {
        Encomenda enc = new Encomenda();
        for (Loja l : this.lojas.values()) {
            for (Encomenda e : l.getPedidos()) {
                if (e.getCod().equals(codEnc)) {
                    l.removePedido(codEnc);
                    return new Encomenda(e);
                }
            }
        }
        return enc;
    }

    public void removeEncomendaTransportadora(String codEnc, String cod) {
        this.trabalhadores.get(cod).removerEncomenda(codEnc);
    }

    public void addPedidoDeTransporte(String cod,Encomenda e) {
        this.trabalhadores.get(cod).addPedidosEncomenda(e);
    }

    public void addEncomendaEntregue(String cod, Encomenda e) {
        this.trabalhadores.get(cod).addEncomendaEntregue(e);
    }

    public double totalFaturado(Transportadora t, LocalDateTime min, LocalDateTime max) {
        double total=0;
        for (Encomenda e : t.getEncomendasEntregues()) {
            if (e.getData().isAfter(min) && e.getData().isBefore(max)) {
                if (this.lojas.containsKey(e.getLoja()) && this.utilizadores.containsKey(e.getUtilizador())) {
                    Loja l = this.lojas.get(e.getLoja());
                    Utilizador u = this.utilizadores.get(e.getUtilizador());
                    double dist = l.getLocalizacao().distancia(u.getLocalizacao());
                    System.out.println(dist);
                    total += t.precoEncomenda(e.getPeso(),dist);
                }
            }
        }
        return total;
    }

    public List<Utilizador> getTop10Util() {
        Comparator<Integer> comp = Integer::compareTo;
        TreeMap<Integer,Set<Utilizador>> vezes = new TreeMap<>(comp);
        List<Utilizador> res = new ArrayList<>();
        int cont=0;
        for (Map.Entry<String,Utilizador> aux : this.utilizadores.entrySet()) {
            int numPedidos = aux.getValue().getEncomendasConcluidas().size();
            vezes.putIfAbsent(numPedidos,new HashSet<>());
            vezes.get(numPedidos).add(aux.getValue());
        }
        for (Map.Entry<Integer,Set<Utilizador>> aux : vezes.entrySet()) {
            if (cont==10) break;
            for (Utilizador u : aux.getValue()) {
                res.add(u.clone());
                cont++;
                if(cont == 10) break;
            }
        }
        return res;
    }

    public List<Transportadora> getTop10Trans() {
        Comparator<Double> comp = Double::compareTo;
        TreeMap<Double,Set<Estafeta>> vezes = new TreeMap<>(comp);
        List<Transportadora> res = new ArrayList<>();
        int cont=0;

        for (Map.Entry<String,Estafeta> aux : this.trabalhadores.entrySet()) {
            if (aux.getValue() instanceof Transportadora) {
                Transportadora t = (Transportadora) aux.getValue();
                double numKms = t.getNumKms();
                vezes.putIfAbsent(numKms,new HashSet<>());
                vezes.get(numKms).add(t);
            }
        }
        for (Map.Entry<Double,Set<Estafeta>> aux : vezes.entrySet()) {
            if (cont==10) break;
            for (Estafeta e : aux.getValue()) {
                res.add((Transportadora) e);
                cont++;
                if(cont==10) break;
            }
            cont++;
        }
        return res;
    }

    public void registar(String email, String pass, String cod, String nome, GPS loc, FileIO f, String tipo) throws IOException {
        Entrada a = new Utilizador();
        a = a.newEntrada(tipo);
        a.setCod(cod);
        a.setNome(nome);
        a.setLocalizacao(loc);
        f.registaConta(email,pass,a,this);

    }

    public void login(String email, String pass, FileIO f) throws IOException {
        f.validaLogin(email,pass, this);
    }

    public void logoff() {
        this.login = null;
    }

    public void add(Entrada a) {
        if(a instanceof Utilizador) addUtilizador((Utilizador) a);
        if(a instanceof Transportadora) addTrabalhador((Transportadora) a);
        if(a instanceof Voluntario) addTrabalhador((Voluntario) a);
        if(a instanceof Loja) addLoja((Loja) a);
        if(a instanceof LojaFilaEspera) addLoja((LojaFilaEspera) a);
    }

    public void addEncomendaUtilizador(String cod,Encomenda e) {
        this.utilizadores.get(cod).addEncomenda(e);
    }

    public void addEncomendaLoja(String cod,Encomenda e) {
        this.lojas.get(cod).addPedido(e);
    }

    public Utilizador getUtilizador(String cod) {
        return this.utilizadores.get(cod).clone();
    }

    public Loja getLoja(String cod) {
        return this.lojas.get(cod).clone();
    }

    public Estafeta getEstafeta(String cod) {
        return this.trabalhadores.get(cod).clone();
    }

    public void mudaDisponibilidade(String cod) {
        this.trabalhadores.get(cod).mudaDisponibilidade();
    }

    public boolean existeEncomenda(String cod) {
        return this.lojas.get(cod).getPedidos().size()!=0;
    }

    public List<Encomenda> encomendasDisponiveis(String cod) {
        Estafeta u = this.trabalhadores.get(cod);
        GPS v = this.trabalhadores.get(cod).getLocalizacao();
        List<Encomenda> res = new ArrayList<>();

        for (Loja l : this.lojas.values()) {
            if (l.getLocalizacao().distancia(v) < u.getRaio()) {
                for (Encomenda e : l.getPedidos()) {
                    res.add(e.clone());
                }
            }
        }
        return res;
    }

    public double precoDaEncomenda(String cod,String transp) {
        Loja lj = new Loja();
        Encomenda enc = new Encomenda();
        boolean stop = false;

        for (Loja l : this.lojas.values()) {
            for (Encomenda e : l.getPedidos()) {
                if (e.getCod().equals(cod)) {
                    lj = new Loja(l);
                    enc = new Encomenda(e);
                    stop = true;
                    break;
                }
            }
            if (stop) break;
        }
        if (!stop) {
            return 0;
        }
        Transportadora t = (Transportadora) this.trabalhadores.get(transp);
        double dist = lj.getLocalizacao().distancia(t.getLocalizacao());
        return t.precoEncomenda(enc.getPeso(),dist);
    }
}
