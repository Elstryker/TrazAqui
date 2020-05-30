package TrazAqui;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    public void addTrabalhador(Estafeta e) throws ExistingCodeException {
        if(this.trabalhadores.putIfAbsent(e.getCod(),e.clone()) != null)
            throw new ExistingCodeException("Código inválido!");
    }

    public void addUtilizador(Utilizador u) throws ExistingCodeException {
        if(this.utilizadores.putIfAbsent(u.getCod(),u.clone())!=null)
            throw new ExistingCodeException("Código inválido!");
    }

    public void addLoja(Loja l) throws ExistingCodeException {
        if(this.lojas.putIfAbsent(l.getCod(),l.clone()) != null)
            throw new ExistingCodeException("Código inválido!");
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

    public Encomenda removeEncomendaLoja(String codEnc,String cod) {
        boolean medicamentos = false;
        if (this.trabalhadores.get(cod) instanceof Transportadora) {
            medicamentos = this.trabalhadores.get(cod).aceitoTransportesMedicamentos();
        }
        for (Loja l : this.lojas.values()) {
            for (Encomenda e : l.getPedidos()) {
                if (e.getCod().equals(codEnc)) {
                    if (e.getMedicamentos() && medicamentos) {
                        this.lojas.get(l.getCod()).removePedido(codEnc);
                        return new Encomenda(e);
                    } else if (!e.getMedicamentos()) {
                        this.lojas.get(l.getCod()).removePedido(codEnc);
                        return new Encomenda(e);
                    }
                }
            }
        }
        return null;
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

    public double calculaPreco(Encomenda enc,String trans,String loja) {
        Loja l = this.lojas.get(loja);
        Transportadora t = (Transportadora) this.trabalhadores.get(trans);
        double taxa = 0;

        if (l instanceof LojaFilaEspera) {
            LojaFilaEspera ljfe = (LojaFilaEspera) l;
            taxa = ljfe.getTempoEspera()*ljfe.getListaEspera()*0.30;
        }

        double dist = l.getLocalizacao().distancia(t.getLocalizacao());
        double preco = t.precoEncomenda(enc.getPeso(),dist);
        return (preco+preco*taxa);
    }

    public double totalFaturado(Transportadora t, LocalDateTime min, LocalDateTime max) {
        double total=0;
        for (Encomenda e : t.getEncomendasEntregues()) {
            if (e.getData().isAfter(min) && e.getData().isBefore(max)) {
                    total += calculaPreco(e,t.getCod(),e.getLoja());
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
            vezes.putIfAbsent(numPedidos,new TreeSet<>(Comparator.comparing(Utilizador::getNome)));
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
        TreeMap<Double,List<Estafeta>> vezes = new TreeMap<>(comp);
        List<Transportadora> res = new ArrayList<>();
        int cont=0;
        for (Map.Entry<String,Estafeta> aux : this.trabalhadores.entrySet()) {
            if (aux.getValue() instanceof Transportadora) {
                Transportadora t = (Transportadora) aux.getValue();
                double numKms = t.getNumKms();
                vezes.putIfAbsent(numKms,new ArrayList<>());
                vezes.get(numKms).add(t);
            }
        }
        for (Map.Entry<Double,List<Estafeta>> aux : vezes.entrySet()) {
            aux.getValue().sort(Comparator.comparing(Estafeta::getNome));
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

    public void registar(String email, String pass, String cod, String nome, GPS loc, FileIO f, String tipo, String nif) throws IOException, ExistingCodeException {
        Entrada a = new Utilizador();
        a = a.newEntrada(tipo);
        a.setCod(cod);
        a.setNome(nome);
        a.setLocalizacao(loc);
        if(a instanceof Transportadora) {
            ((Transportadora) a).setRaio(ThreadLocalRandom.current().nextDouble(60, 151));
            ((Transportadora) a).setPrecoKM(ThreadLocalRandom.current().nextDouble(1,6));
            ((Transportadora) a).setNIF(nif);
        }
        f.registaConta(email,pass,a,this);
    }

    public void login(String email, String pass, FileIO f) throws IOException, InvalidInputException {
        f.validaLogin(email,pass, this);
    }

    public void logoff() {
        this.login = null;
    }

    public void add(Entrada a) throws ExistingCodeException {
        if(a instanceof Utilizador) addUtilizador((Utilizador) a);
        else if(a instanceof Transportadora) addTrabalhador((Transportadora) a);
        else if(a instanceof Voluntario) addTrabalhador((Voluntario) a);
        else if(a instanceof LojaFilaEspera) addLoja((LojaFilaEspera) a);
        else if(a instanceof Loja) addLoja((Loja) a);
    }

    public void addEncomendaUtilizador(String cod,Encomenda e) {
        this.utilizadores.get(cod).addEncomenda(e);
    }

    public void addEncomendaLoja(String cod,Encomenda e) throws LojaInexistenteException {
        if(this.getLojas().get(cod) != null) this.lojas.get(cod).addPedido(e);
        else throw new LojaInexistenteException("A loja " + cod + " nao existe");
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

    public void setRaio(String cod, double raio) {
        this.trabalhadores.get(cod).setRaio(raio);
    }

    public void setPrecokms(String cod, double preco) {
        Transportadora a = (Transportadora) this.trabalhadores.get(cod);
        a.setPrecoKM(preco);
    }

    public boolean mudaDisponibilidade(String cod) {
        return this.trabalhadores.get(cod).mudaDisponibilidade();
    }

    public boolean disponivel(String cod) {
        return this.trabalhadores.get(cod).isDisponivel();
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
        boolean temFila = false;
        double taxa = 0;

        for (Loja l : this.lojas.values()) {
            for (Encomenda e : l.getPedidos()) {
                if (e.getCod().equals(cod)) {
                    if (l instanceof LojaFilaEspera) {
                        temFila = true;
                        lj = new LojaFilaEspera((LojaFilaEspera) l);
                    } else lj = new Loja(l);
                    enc = new Encomenda(e);
                    stop = true;
                    break;
                }
            }
            if (stop) break;
        }
        if (!stop) {
            return -1;
        }
        if (temFila) {
            LojaFilaEspera ljfe = (LojaFilaEspera) lj;
            taxa = ljfe.getTempoEspera()*ljfe.getListaEspera()*0.30;
        }
        Transportadora t = (Transportadora) this.trabalhadores.get(transp);
        double dist = lj.getLocalizacao().distancia(t.getLocalizacao());
        double preco = t.precoEncomenda(enc.getPeso(),dist);
        return (preco+preco*taxa);
    }
}
