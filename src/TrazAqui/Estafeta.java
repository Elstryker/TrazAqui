package TrazAqui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Estafeta implements Entrada{

    private String cod;
    private String nome;
    private GPS localizacao;
    private double raio;
    private List<Encomenda> encomendasEntregues;
    private List<Encomenda> pedidosEncomenda;
    private int[] classificacao;
    private boolean disponivel;


    public Estafeta (){
        this.cod = "";
        this.nome = "";
        this.localizacao = new GPS();
        this.raio = 0;
        this.encomendasEntregues = new ArrayList<>();
        this.pedidosEncomenda = new ArrayList<>();
        this.classificacao = new int[5];
        this.disponivel= true;
    }

    public Estafeta(String cod, String nome, GPS localizacao, double raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda, int[] classificacao, boolean disponivel) {
        this.cod = cod;
        this.nome = nome;
        this.localizacao = localizacao;
        this.raio = raio;
        this.encomendasEntregues = encomendasEntregues;
        this.pedidosEncomenda = pedidosEncomenda;
        this.classificacao = classificacao;
        this.disponivel = disponivel;
    }

    public Estafeta(Estafeta a){
        this.cod = a.cod;
        this.nome = a.nome;
        this.setLocalizacao(a.localizacao);
        this.raio= a.raio;
        this.setEncomendasEntregues(a.encomendasEntregues);
        this.setPedidosEncomenda(a.pedidosEncomenda);
        this.setClassificacao(a.classificacao);
        this.disponivel=a.disponivel;
    }

    public String getCod(){
        return this.cod;
    }

    public void setCod(String a){
        this.cod = a;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String n){
        this.nome = n;
    }

    public GPS getLocalizacao(){
        return this.localizacao;
    }

    public void setLocalizacao(GPS a){
        this.localizacao= a;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<Encomenda> getEncomendasEntregues() {
        List<Encomenda> aux= new ArrayList<>();
        for(Encomenda a : this.encomendasEntregues)
            aux.add(a.clone());
        return aux;
    }

    public void setEncomendasEntregues(List<Encomenda> encomendasEntregues) {
        this.encomendasEntregues = new ArrayList<>();
        encomendasEntregues.forEach(l -> this.encomendasEntregues.add(l.clone()));
    }

    public List<Encomenda> getPedidosEncomenda() {
        List<Encomenda> aux= new ArrayList<>();
        for(Encomenda a: this.pedidosEncomenda)
            aux.add(a.clone());
        return aux;
    }

    public void setPedidosEncomenda(List<Encomenda> pedidosEncomenda) {
        this.pedidosEncomenda = new ArrayList<>();
        pedidosEncomenda.forEach(l-> this.pedidosEncomenda.add(l.clone()));
    }
    public int[] getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int[] classificacao) {
        this.classificacao = classificacao;
    }

    public void classifica(int x) {
        if (x >=1 && x<=5) {
            this.classificacao[x - 1]++;
        } else System.out.println("Classificacao invalida");
    }

    public double getClassMedia(){
        int totclas =0;
        int somaclas =0;
        double avg =0;
        for(int x : this.classificacao){
            somaclas += x;
            totclas++;
        }

        avg = (double) somaclas/totclas;
        return avg;
    }

    public List<Encomenda> aceitaEncomenda(List<Encomenda> pedidosEncomenda, HashMap<String,Loja> lojas, Estado estado){
        double dist;
        for(Encomenda e: pedidosEncomenda){
            Loja l = lojas.get(e.getLoja()).clone();
            dist = l.getLocalizacao().distancia(estado.getUserPos(e.getUtilizador()));
            if (dist>raio) pedidosEncomenda.remove(e);
        }
        return pedidosEncomenda;
    }

    public void mudaDisponibilidade(){
        this.disponivel= !this.disponivel;
    }

    public void addEncomendaEntregue(Encomenda a){
        this.encomendasEntregues.add(a.clone());
    }

    public void addPedidosEncomenda(Encomenda a){
        this.pedidosEncomenda.add(a.clone());
    }

    @Override
    public Estafeta clone(){
        return new Estafeta(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, nome, localizacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estafeta estafeta = (Estafeta) o;
        return Objects.equals(cod, estafeta.cod) &&
                Objects.equals(nome, estafeta.nome) &&
                Objects.equals(localizacao, estafeta.localizacao);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Estafeta{");
        sb.append("cod='").append(cod).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", localizacao=").append(localizacao);
        sb.append('}');
        return sb.toString();
    }
}
