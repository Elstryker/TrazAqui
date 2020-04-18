package TrazAqui;

public class Ultilizador {
    //Variaveis de instancia
    private String nome;
    private String codigo;
    private GPS posicao;

    public Ultilizador() {
        this.nome = "";
        this.codigo = "";
        this.posicao = new GPS();
    }

    public Ultilizador(String n,String c,GPS pos) {
        this.nome = n;
        this.posicao = pos;
        this.codigo = c;
    }

    public Ultilizador(Ultilizador u) {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.posicao = u.getPosicao();
    }

    public Ultilizador clone() {
        return new Ultilizador(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ultilizador{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", posicao=").append(posicao);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass().equals(o.getClass())) return true;
        Ultilizador u = (Ultilizador) o;

        return this.posicao.equals(u.getPosicao()) && this.codigo.equals(u.getCodigo()) && this.nome.equals(u.getNome());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public GPS getPosicao() {
        return posicao;
    }

    public void setPosicao(GPS posicao) {
        this.posicao = posicao;
    }
}
