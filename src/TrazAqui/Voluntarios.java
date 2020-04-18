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

    public Voluntarios(String codVoluntario, String nomeVoluntario, boolean disponivel, GPS localizacao, int raio, List<Encomenda> encomendasEntregues) {
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.disponivel = disponivel;
        this.localizacao = localizacao;
        this.raio = raio;
        this.encomendasEntregues = encomendasEntregues;
    }

    public Voluntarios(){
        this.disponivel = false;
        this.localizacao= new GPS();
        this.encomendasEntregues= new ArrayList<>();
        this.nomeVoluntario= new String();
        this.codVoluntario=new String();
        this.raio=0;
    }

    public Voluntarios(Voluntarios a) {
        this.disponivel = a.disponivel;
        this.localizacao = a.localizacao;
        this.encomendasEntregues= a.encomendasEntregues;
        this.nomeVoluntario= a.nomeVoluntario;
        this.codVoluntario=a.codVoluntario;
        this.raio=a.raio;
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
        this.encomendasEntregues = encomendasEntregues;
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
                Objects.equals(encomendasEntregues, that.encomendasEntregues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codVoluntario, nomeVoluntario, disponivel, localizacao, raio, encomendasEntregues);
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
        sb.append('}');
        return sb.toString();
    }

    public Voluntarios clone(){
        return new Voluntarios(this);
    }
}