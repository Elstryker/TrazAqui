package TrazAqui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Encomenda {
    //Variaveis de instancia
    private double peso;
    private String descricao;
    private String codigo;
    private LocalDateTime data;
    private Utilizador dest;
    private String loja;
    private boolean medicamentos;
    private List<LinhaEncomenda> produtos;

    public Encomenda(double peso, boolean med, String descricao, String codigo, Utilizador dest, String loja,ArrayList<LinhaEncomenda> produtos,LocalDateTime l) {
        this.peso = peso;
        this.descricao = descricao;
        this.codigo = codigo;
        this.dest = dest;
        this.loja = loja;
        this.produtos = produtos;
        this.data = l;
        this.medicamentos = med;
    }

    public Encomenda() {
        this.peso = 0;
        this.descricao = "";
        this.codigo = "";
        this.dest = new Utilizador();
        this.loja = "";
        this.medicamentos = false;
        this.produtos = new ArrayList<>();
        this.data = LocalDateTime.now();
    }

    public Encomenda(Encomenda e) {
        this.peso = e.getPeso();
        this.data = e.getData();
        this.produtos = e.getProdutos();
        this.loja = e.getLoja();
        this.dest = e.getDest();
        this.codigo = e.getCodigo();
        this.medicamentos = e.getMedicamentos();
        this.descricao = e.getDescricao();
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Encomenda{");
        sb.append("peso=").append(peso);
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", codigo=").append(codigo);
        sb.append(", data=").append(data);
        sb.append(", dest=").append(dest);
        sb.append(", origem=").append(loja);
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
                this.codigo.equals(e.getCodigo()) &&
                this.descricao.equals(e.getCodigo()) &&
                this.dest.equals(e.getDest()) &&
                this.loja.equals(e.getLoja()) &&
                this.produtos.equals(e.getProdutos()) &&
                this.medicamentos==e.getMedicamentos() &&
                this.data.equals(e.getData());
    }

    //Metodos de acesso
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

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Utilizador getDest() {
        return this.dest.clone();
    }

    public void setDest(Utilizador dest) {
        this.dest = new Utilizador(dest);
    }

    public String getLoja() {
        return this.loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public ArrayList<LinhaEncomenda> getProdutos() {
        return new ArrayList<LinhaEncomenda>(this.produtos);
    }

    public void setProdutos(ArrayList<LinhaEncomenda> produtos) {
        this.produtos = new ArrayList<LinhaEncomenda>();
        this.produtos.addAll(produtos);
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