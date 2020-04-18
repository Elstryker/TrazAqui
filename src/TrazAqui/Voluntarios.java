package TrazAqui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntarios {
    private String codVoluntario;
    private String nomeVoluntario;
    private boolean disponivel;
    private GPS localizacao;
    private int raio;
    private List<Encomenda> encomendasEntregues;
    private List<Encomenda> pedidosEncomenda;

    public Voluntarios(String codVoluntario, String nomeVoluntario, boolean disponivel, GPS localizacao, int raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda) {
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.disponivel = disponivel;
        this.localizacao = localizacao;
        this.raio = raio;
        this.setEncomendasEntregues(encomendasEntregues);
        this.setPedidosEncomenda(pedidosEncomenda);
    }

    public Voluntarios(){
        this.disponivel = false;
        this.localizacao= new GPS();
        this.nomeVoluntario= "";
        this.codVoluntario="";
        this.raio=0;
        this.encomendasEntregues= new ArrayList<>();
        this.pedidosEncomenda=new ArrayList<>();
    }

    public Voluntarios(Voluntarios a) {
        this.disponivel = a.disponivel;
        this.localizacao = a.localizacao;
        this.nomeVoluntario= a.nomeVoluntario;
        this.codVoluntario=a.codVoluntario;
        this.raio=a.raio;
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

    public List<Encomenda> getEncomendasEntregues() {
        return encomendasEntregues;
    }

    public void setEncomendasEntregues(List<Encomenda> encomendasEntregues) {
        this.encomendasEntregues = new ArrayList<>();
        encomendasEntregues.forEach(l -> this.encomendasEntregues.add(l.clone()));
    }

    public List<Encomenda> getPedidosEncomenda() {
        return pedidosEncomenda;
    }

    public void setPedidosEncomenda(List<Encomenda> pedidosEncomenda) {
        this.pedidosEncomenda = new ArrayList<>();
        pedidosEncomenda.forEach(l-> this.pedidosEncomenda.add(l.clone()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntarios that = (Voluntarios) o;
        return disponivel == that.disponivel &&
                raio == that.raio &&
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
        final StringBuilder sb = new StringBuilder("Voluntarios{");
        sb.append("codVoluntario='").append(codVoluntario).append('\'');
        sb.append(", nomeVoluntario='").append(nomeVoluntario).append('\'');
        sb.append(", disponivel=").append(disponivel);
        sb.append(", localizacao=").append(localizacao);
        sb.append(", raio=").append(raio);
        sb.append(", encomendasEntregues=").append(encomendasEntregues);
        sb.append(", pedidosEncomenda=").append(pedidosEncomenda);
        sb.append('}');
        return sb.toString();
    }

    public Voluntarios clone(){
        return new Voluntarios(this);
    }


    // Métodos

    public List<Encomenda> aceitaEncomenda(List<Encomenda> pedidosEncomenda){
        double dist;
        for(Encomenda aux: pedidosEncomenda){
            dist=aux.getOrigem().getLocalizacao().distancia(this.localizacao);
            if (dist>raio) pedidosEncomenda.remove(aux);
        }
        return pedidosEncomenda;
    }

    public void mudaDisponibilidade(){
        if(!this.disponivel) this.disponivel= true;
        else {
            this.disponivel=false;
        }
    }

    public void addEncomendaEntregue(Encomenda a){
        this.encomendasEntregues.add(a.clone());
    }

    public void addPedidosEncomenda(Encomenda a){
        this.pedidosEncomenda.add(a.clone());
    }

}