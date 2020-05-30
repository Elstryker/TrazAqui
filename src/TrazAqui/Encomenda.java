package TrazAqui;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Encomenda implements Serializable {
    private double peso;
    private String cod;
    private LocalDateTime data;
    private String utilizador;
    private String loja;
    private boolean medicamentos;
    private String estafeta;
    private List<LinhaEncomenda> produtos;

    public Encomenda(double peso, boolean med, String cod, String utilizador, String loja,ArrayList<LinhaEncomenda> produtos,LocalDateTime l,String estafeta) {
        this.peso = peso;
        this.cod = cod;
        this.utilizador = utilizador;
        this.loja = loja;
        this.produtos = produtos;
        this.data = l;
        this.medicamentos = med;
        this.estafeta =estafeta;
    }

    public Encomenda() {
        this.peso = 0;
        this.cod = "";
        this.utilizador = "";
        this.loja = "";
        this.medicamentos = false;
        this.produtos = new ArrayList<>();
        this.data = LocalDateTime.now();
        this.estafeta="";
    }

    public Encomenda(Encomenda e) {
        this.peso = e.getPeso();
        this.data = e.getData();
        this.produtos = e.getProdutos();
        this.loja = e.getLoja();
        this.utilizador = e.getUtilizador();
        this.cod = e.getCod();
        this.medicamentos = e.getMedicamentos();
        this.estafeta= e.getEstafeta();
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Encomenda{");
        sb.append("Transportador='").append(estafeta).append('\'');
        sb.append(", peso='").append(peso).append('\'');
        sb.append(", cod='").append(cod).append('\'');
        sb.append(", data=").append(data);
        sb.append(", utilizador='").append(utilizador).append('\'');
        sb.append(", loja='").append(loja).append('\'');
        sb.append(", medicamentos=").append(medicamentos);
        sb.append(", produtos=").append(produtos);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o==null || this.getClass().equals(o.getClass())) return true;
        Encomenda e = (Encomenda) o;

        return this.peso==e.getPeso() &&
                this.cod.equals(e.getCod()) &&
                this.utilizador.equals(e.getUtilizador()) &&
                this.loja.equals(e.getLoja()) &&
                this.produtos.equals(e.getProdutos()) &&
                this.medicamentos==e.getMedicamentos() &&
                this.data.equals(e.getData())  &&
                this.estafeta.equals(e.getEstafeta());
    }

    public void setProdutos(List<LinhaEncomenda> produtos) {
        this.produtos = new ArrayList<>();
        for (LinhaEncomenda e : produtos) {
            this.produtos.add(e.clone());
        }
    }

    public boolean getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getUtilizador() {
        return this.utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public String getLoja() {
        return this.loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public ArrayList<LinhaEncomenda> getProdutos() {
        ArrayList<LinhaEncomenda> res = new ArrayList<>();
        for (LinhaEncomenda l : this.produtos) {
            res.add(l.clone());
        }
        return res;
    }

    public void setProdutos(ArrayList<LinhaEncomenda> produtos) {
        this.produtos = new ArrayList<>();
        for (LinhaEncomenda l : produtos) {
            this.produtos.add(l.clone());
        }
    }

    public String getEstafeta() {
        return estafeta;
    }

    public void setEstafeta(String estafeta) {
        this.estafeta = estafeta;
    }

    public void addProduto(LinhaEncomenda l) {
        this.produtos.add(l.clone());
    }

    public void removeProduto(LinhaEncomenda l) {
        int i,size = this.produtos.size();
        for (i=0; i<size; i++) {
            if (this.produtos.get(i).equals(l)) {
                this.produtos.remove(i);
            }
        }
    }

}