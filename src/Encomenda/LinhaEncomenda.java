package Encomenda;

public class LinhaEncomenda {
    //Variaveis de instancia
    private String descricao;
    private double preco;
    private int quantidade;
    private boolean fragil;
    private String nome;

    //Construtores
    public LinhaEncomenda() {
        this.descricao = "";
        this.preco = 0;
        this.quantidade = 0;
        this.fragil = false;
        this.nome = "";
    }

    public LinhaEncomenda(String descricao, double preco,int quantidade,boolean fragil, String nome) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fragil = fragil;
        this.nome = nome;
    }

    public LinhaEncomenda(LinhaEncomenda linha) {
        this.descricao = linha.getDescricao();
        this.preco = linha.getPreco();
        this.quantidade = linha.getQuantidade();
        this.fragil = linha.getFragil();
        this.nome = linha.getNome();
    }

    //Metodos de acesso
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean getFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LinhaEncomenda clone() {
        return new LinhaEncomenda(this);
    }

    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj==null || obj.getClass() != this.getClass()) return false;
        LinhaEncomenda le = (LinhaEncomenda) obj;
        return  le.getDescricao().equals(this.descricao) &&
                le.getPreco() == this.preco &&
                le.getNome().equals(this.nome) &&
                le.getFragil()==this.fragil &&
                le.getQuantidade()==this.quantidade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LinhaEncomenda{");
        sb.append("descricao='").append(descricao).append('\'');
        sb.append(", preco=").append(preco);
        sb.append(", quantidade=").append(quantidade);
        sb.append(", fragil=").append(fragil);
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }

    //Metodos
    public double calculaValorLinhaEnc() {
        return this.quantidade * this.preco;
    }

}
