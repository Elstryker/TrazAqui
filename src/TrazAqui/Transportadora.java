package TrazAqui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transportadora {
    private boolean certificada;
    private boolean disponivel;
    private String nomeEmpresa;
    private String codEmpresa;
    private GPS localizacao;
    private String NIF;
    private int raio;
    private double precoKM;
    private List<Encomenda> encomendasEntregues;
    private List<Encomenda> pedidosEncomenda;

    public Transportadora(boolean certificada, boolean disponivel, String nomeEmpresa, String codEmpresa, GPS localizacao,String NIF, int raio, double precoKM, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda) {
        this.certificada=certificada;
        this.disponivel = disponivel;
        this.nomeEmpresa = nomeEmpresa;
        this.codEmpresa = codEmpresa;
        this.localizacao = localizacao;
        this.NIF=NIF;
        this.raio = raio;
        this.precoKM = precoKM;
        this.setEncomendasEntregues(encomendasEntregues);
        this.setPedidosEncomenda(pedidosEncomenda);
    }

    public Transportadora(){
        this.certificada=true;
        this.disponivel=false;
        this.pedidosEncomenda = new ArrayList<>();
        this.encomendasEntregues = new ArrayList<>();
        this.nomeEmpresa= "";
        this.codEmpresa= "";
        this.localizacao= new GPS();
        this.raio=0;
        this.precoKM=0;
    }

    public Transportadora(Transportadora a) {
        this.certificada=a.certificada;
        this.disponivel=a.disponivel;
        this.setEncomendasEntregues(a.getEncomendasEntregues());
        this.setPedidosEncomenda(a.getPedidosEncomenda());
        this.nomeEmpresa=a.nomeEmpresa;
        this.codEmpresa= a.codEmpresa;
        this.localizacao=a.localizacao;
        this.raio=a.raio;
        this.precoKM= a.precoKM;
        this.NIF=a.NIF;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public boolean isCertificada() {
        return certificada;
    }

    public void setCertificada(boolean certificada) {
        this.certificada = certificada;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportadora that = (Transportadora) o;
        return certificada == that.certificada &&
                disponivel == that.disponivel &&
                raio == that.raio &&
                Double.compare(that.precoKM, precoKM) == 0 &&
                Objects.equals(nomeEmpresa, that.nomeEmpresa) &&
                Objects.equals(codEmpresa, that.codEmpresa) &&
                Objects.equals(localizacao, that.localizacao) &&
                Objects.equals(NIF, that.NIF) &&
                Objects.equals(encomendasEntregues, that.encomendasEntregues) &&
                Objects.equals(pedidosEncomenda, that.pedidosEncomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificada, disponivel, nomeEmpresa, codEmpresa, localizacao, NIF, raio, precoKM, encomendasEntregues, pedidosEncomenda);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("certificada=").append(certificada);
        sb.append(", disponivel=").append(disponivel);
        sb.append(", nomeEmpresa='").append(nomeEmpresa).append('\'');
        sb.append(", codEmpresa='").append(codEmpresa).append('\'');
        sb.append(", localizacao=").append(localizacao);
        sb.append(", NIF='").append(NIF).append('\'');
        sb.append(", raio=").append(raio);
        sb.append(", precoKM=").append(precoKM);
        sb.append(", encomendasEntregues=").append(encomendasEntregues);
        sb.append(", pedidosEncomenda=").append(pedidosEncomenda);
        sb.append('}');
        return sb.toString();
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }

    // Métodos
    public double precoEncomenda(double peso, double dist) {
        double total = 0;

        if (peso > 10) total = this.precoKM*dist;
        else total = this.precoKM*dist+2.5;

        return total;
    }

    public void aceitaMedicamentos(boolean certificada){
        this.certificada=true;
    }

    public boolean aceitoTransportesMedicamentos(){
        return this.certificada;
    }

    public void mudaDisponibilidade() {
        this.disponivel= !this.disponivel;
    }

    public void addEncomendaEntregue(Encomenda a){
        this.encomendasEntregues.add(a.clone());
    }

    public void addPedidosEncomenda(Encomenda a){
        this.pedidosEncomenda.add(a.clone());
    }
}
