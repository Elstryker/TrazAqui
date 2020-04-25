package TrazAqui;

import java.util.*;

public class Voluntario {
    private String codVoluntario;
    private String nomeVoluntario;
    private boolean disponivel;
    private GPS localizacao;
    private int raio;
    private int[] classificacao;
    private List<Encomenda> encomendasEntregues;
    private List<Encomenda> pedidosEncomenda;

    public Voluntario(String codVoluntario, String nomeVoluntario, boolean disponivel, GPS localizacao, int[] cla, int raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda) {
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.disponivel = disponivel;
        this.localizacao = localizacao;
        this.raio = raio;
        this.classificacao = cla;
        this.setEncomendasEntregues(encomendasEntregues);
        this.setPedidosEncomenda(pedidosEncomenda);
    }

    public Voluntario(){
        this.disponivel = false;
        this.localizacao= new GPS();
        this.nomeVoluntario= "";
        this.codVoluntario="";
        this.raio=0;
        this.classificacao = new int[5];
        this.encomendasEntregues= new ArrayList<>();
        this.pedidosEncomenda=new ArrayList<>();
    }

    public Voluntario(Voluntario a) {
        this.disponivel = a.disponivel;
        this.localizacao = a.localizacao;
        this.nomeVoluntario= a.nomeVoluntario;
        this.codVoluntario=a.codVoluntario;
        this.raio=a.raio;
        this.classificacao = a.getClassificacao();
        this.setEncomendasEntregues(a.getEncomendasEntregues());
        this.setPedidosEncomenda(a.getPedidosEncomenda());
    }

    public String getCodVoluntario() {
        return codVoluntario;
    }

    public void setCodVoluntario(String codVoluntario) {
        this.codVoluntario = codVoluntario;
    }

    public String getNomeVoluntario() {
        return nomeVoluntario;
    }

    public void setNomeVoluntario(String nomeVoluntario) {
        this.nomeVoluntario = nomeVoluntario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public GPS getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GPS localizacao) {
        this.localizacao = localizacao;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public int[] getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int[] classificacao) {
        this.classificacao = classificacao;
    }

    public List<Encomenda> getEncomendasEntregues() {
        List<Encomenda> aux = new ArrayList<>();
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
            for(Encomenda a : this.encomendasEntregues)
                aux.add(a.clone());
        return aux;
    }

    public void setPedidosEncomenda(List<Encomenda> pedidosEncomenda) {
        this.pedidosEncomenda = new ArrayList<>();
        pedidosEncomenda.forEach(l-> this.pedidosEncomenda.add(l.clone()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return disponivel == that.disponivel &&
                raio == that.raio &&
                Arrays.equals(classificacao, that.classificacao) &&
                Objects.equals(codVoluntario, that.codVoluntario) &&
                Objects.equals(nomeVoluntario, that.nomeVoluntario) &&
                Objects.equals(localizacao, that.localizacao) &&
                Objects.equals(encomendasEntregues, that.encomendasEntregues) &&
                Objects.equals(pedidosEncomenda, that.pedidosEncomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codVoluntario, nomeVoluntario, disponivel, localizacao, raio, encomendasEntregues, pedidosEncomenda);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Voluntario{");
        sb.append("codVoluntario='").append(codVoluntario).append('\'');
        sb.append(", nomeVoluntario='").append(nomeVoluntario).append('\'');
        sb.append(", disponivel=").append(disponivel);
        sb.append(", localizacao=").append(localizacao);
        sb.append(", raio=").append(raio);
        sb.append(", classificacao=").append(Arrays.toString(classificacao));
        sb.append(", encomendasEntregues=").append(encomendasEntregues);
        sb.append(", pedidosEncomenda=").append(pedidosEncomenda);
        sb.append('}');
        return sb.toString();
    }

    public Voluntario clone(){
        return new Voluntario(this);
    }


    // Métodos
    public void classifica(int x) {
        if (x >=1 && x<=5) {
            this.classificacao[x - 1]++;
        } else System.out.println("Classificacao invalida");
    }

    public List<Encomenda> aceitaEncomenda(List<Encomenda> pedidosEncomenda, HashMap<String,Loja> lojas){
        double dist;
        for(Encomenda e: pedidosEncomenda){
            Loja l = lojas.get(e.getLoja()).clone();
            dist = l.getLocalizacao().distancia(e.getDest().getPosicao());
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

}