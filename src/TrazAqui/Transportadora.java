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
    private List<Encomendas> encomendasEntregues;
    private List<Encomendas> pedidosEncomenda;

    public Transportadora(boolean disponivel, String nomeEmpresa, String codEmpresa, GPS localizacao, int raio, double precoKM, List<Voluntarios> voluntarios, List<Encomendas> encomendasEntregues, List<Encomendas> pedidosEncomenda) {
        this.disponivel = disponivel;
        this.nomeEmpresa = nomeEmpresa;
        this.codEmpresa = codEmpresa;
        this.localizacao = localizacao;
        this.raio = raio;
        this.precoKM = precoKM;
        this.voluntarios = voluntarios;
        this.encomendasEntregues = encomendasEntregues;
        this.pedidosEncomenda = pedidosEncomenda;
    }

    public Transportadora(){
        this.disponivel=false;
        this.voluntarios= new ArrayList<>();
        this.pedidosEncomenda = new ArrayList<>();
        this.encomendasEntregues = new ArrayList<>();
        this.nomeEmpresa= new String();
        this.codEmpresa= new String();
        this.localizacao= new GPS();
        this.raio=0;
        this.precoKM=0;
    }

    public Transportadora(Transportadora a) {
        this.disponivel=a.disponivel;
        this.voluntarios=a.voluntarios;
        this.encomendasEntregues=a.encomendasEntregues;
        this.pedidosEncomenda=a.pedidosEncomenda;
        this.nomeEmpresa= a.nomeEmpresa;
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
        this.voluntarios = voluntarios;
    }

    public List<Encomendas> getEncomendasEntregues() {
        return encomendasEntregues;
    }

    public void setEncomendasEntregues(List<Encomendas> encomendasEntregues) {
        this.encomendasEntregues = encomendasEntregues;
    }

    public List<Encomendas> getPedidosEncomenda() {
        return pedidosEncomenda;
    }

    public void setPedidosEncomenda(List<Encomendas> pedidosEncomenda) {
        this.pedidosEncomenda = pedidosEncomenda;
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


}
