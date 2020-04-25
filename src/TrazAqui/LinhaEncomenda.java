package TrazAqui;

public class LinhaEncomenda {
    //Variaveis de instancia
    private String descricao;
    private double preco;
    private double quantidade;
    private boolean fragil;
    private String codigo;

    //Construtores
    public LinhaEncomenda() {
        this.descricao = "";
        this.preco = 0;
        this.quantidade = 0;
        this.fragil = false;
        this.codigo = "";
    }

    public LinhaEncomenda(String descricao, double preco,double quantidade,boolean fragil, String codigo) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fragil = fragil;
        this.codigo = codigo;
    }

    public LinhaEncomenda(LinhaEncomenda linha) {
        this.descricao = linha.getDescricao();
        this.preco = linha.getPreco();
        this.quantidade = linha.getQuantidade();
        this.fragil = linha.getFragil();
        this.codigo = linha.getCodigo();
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public boolean getFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
                le.getCodigo().equals(this.codigo) &&
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
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append('}');
        return sb.toString();
    }

    //Metodos
    public double calculaValorLinhaEnc() {
        return this.quantidade * this.preco;
    }

}
