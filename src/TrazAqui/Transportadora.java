package TrazAqui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transportadora {
    private boolean disponivel;
    private String nomeEmpresa;
    private String codEmpresa;
    private GPS localizacao;
    private int raio;
    private double precoKM;
    private List<Voluntarios> voluntarios;
    private List<Encomenda> encomendasEntregues;
    private List<Encomenda> pedidosEncomenda;

    public Transportadora(boolean disponivel, String nomeEmpresa, String codEmpresa, GPS localizacao, int raio, double precoKM, List<Voluntarios> voluntarios, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda) {
        this.disponivel = disponivel;
        this.nomeEmpresa = nomeEmpresa;
        this.codEmpresa = codEmpresa;
        this.localizacao = localizacao;
        this.raio = raio;
        this.precoKM = precoKM;
        this.setVoluntarios(voluntarios);
        this.setEncomendasEntregues(encomendasEntregues);
        this.setPedidosEncomenda(pedidosEncomenda);
    }

    public Transportadora(){
        this.disponivel=false;
        this.voluntarios= new ArrayList<>();
        this.pedidosEncomenda = new ArrayList<>();
        this.encomendasEntregues = new ArrayList<>();
        this.nomeEmpresa= "";
        this.codEmpresa= "";
        this.localizacao= new GPS();
        this.raio=0;
        this.precoKM=0;
    }

    public Transportadora(Transportadora a) {
        this.disponivel=a.disponivel;
        this.setVoluntarios(a.getVoluntarios());
        this.setEncomendasEntregues(a.getEncomendasEntregues());
        this.setPedidosEncomenda(a.getPedidosEncomenda());
        this.nomeEmpresa=a.nomeEmpresa;
        this.codEmpresa= a.codEmpresa;
        this.localizacao=a.localizacao;
        this.raio=a.raio;
        this.precoKM= a.precoKM;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
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

    public double getPrecoKM() {
        return precoKM;
    }

    public void setPrecoKM(double precoKM) {
        this.precoKM = precoKM;
    }

    public List<Voluntarios> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<Voluntarios> voluntarios) {
        this.voluntarios = new ArrayList<>();
        voluntarios.forEach(l -> this.voluntarios.add(l.clone()));
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
        Transportadora that = (Transportadora) o;
        return disponivel == that.disponivel &&
                raio == that.raio &&
                Double.compare(that.precoKM, precoKM) == 0 &&
                Objects.equals(nomeEmpresa, that.nomeEmpresa) &&
                Objects.equals(codEmpresa, that.codEmpresa) &&
                Objects.equals(localizacao, that.localizacao) &&
                Objects.equals(voluntarios, that.voluntarios) &&
                Objects.equals(encomendasEntregues, that.encomendasEntregues) &&
                Objects.equals(pedidosEncomenda, that.pedidosEncomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disponivel, nomeEmpresa, codEmpresa, localizacao, raio, precoKM, voluntarios, encomendasEntregues, pedidosEncomenda);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("disponivel=").append(disponivel);
        sb.append(", nomeEmpresa='").append(nomeEmpresa).append('\'');
        sb.append(", codEmpresa='").append(codEmpresa).append('\'');
        sb.append(", localizacao=").append(localizacao);
        sb.append(", raio=").append(raio);
        sb.append(", precoKM=").append(precoKM);
        sb.append(", voluntarios=").append(voluntarios);
        sb.append(", encomendasEntregues=").append(encomendasEntregues);
        sb.append(", pedidosEncomenda=").append(pedidosEncomenda);
        sb.append('}');
        return sb.toString();
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }

    // Métodos

    public double precoEncomenda(double precoKM, double peso, double dist) {
        double total = 0;

        if (peso > 10) total = precoKM*dist;
        else total = precoKM*dist+2.5;

        return total;
    }


}
