package Encomenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Encomenda {
    //Variaveis de instancia
    private double peso;
    private String descricao;
    private int codigo;
    private LocalDateTime data;
    private String dest;
    private String origem;
    private String transporte;
    private boolean medicamentos;
    private List<LinhaEncomenda> produtos;

    public Encomenda(double peso,boolean med,String descricao,int codigo,String dest,String origem,String transporte,ArrayList<LinhaEncomenda> produtos,LocalDateTime l) {
        this.peso = peso;
        this.descricao = descricao;
        this.codigo = codigo;
        this.dest = dest;
        this.origem = origem;
        this.transporte = transporte;
        this.produtos = produtos;
        this.data = l;
        this.medicamentos = med;
    }

    public Encomenda() {
        this.peso = 0;
        this.descricao = "";
        this.codigo = 0;
        this.dest = "";
        this.origem = "";
        this.transporte = "";
        this.medicamentos = false;
        this.produtos = new ArrayList<LinhaEncomenda>();
        this.data = LocalDateTime.now();
    }

    public Encomenda(Encomenda e) {
        this.peso = e.getPeso();
        this.data = e.getData();
        this.produtos = e.getProdutos();
        this.transporte = e.getTransporte();
        this.origem = e.getOrigem();
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
        sb.append(", dest='").append(dest).append('\'');
        sb.append(", origem='").append(origem).append('\'');
        sb.append(", transporte='").append(transporte).append('\'');
        sb.append(", produtos=").append(produtos);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o==null || this.getClass().equals(o.getClass())) return true;
        Encomenda e = (Encomenda) o;

        return this.peso==e.getPeso() &&
                this.codigo==e.getCodigo() &&
                this.descricao.equals(e.getCodigo()) &&
                this.dest.equals(e.getDest()) &&
                this.origem.equals(e.getOrigem()) &&
                this.transporte.equals(e.getTransporte()) &&
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

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDest() {
        return this.dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getOrigem() {
        return this.origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getTransporte() {
        return this.transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public ArrayList<LinhaEncomenda> getProdutos() {
        return new ArrayList<LinhaEncomenda>(this.produtos);
    }

    public void setProdutos(ArrayList<LinhaEncomenda> produtos) {
        this.produtos = new ArrayList<LinhaEncomenda>();
        this.produtos.addAll(produtos);
    }
}