package TrazAqui;

import java.util.Objects;

public class Loja {
    private int codLoja;
    private String nome;
    private GPS localizacao;

    public Loja () {
        this.codLoja = 0;
        this.nome = "";
        this.localizacao = new GPS();
    }

    public Loja(int codLoja, String nome, GPS localizacao) {
        this.codLoja = codLoja;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public int getCodLoja() {
        return codLoja;
    }

    public void setCodLoja(int codLoja) {
        this.codLoja = codLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GPS getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GPS localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loja loja = (Loja) o;
        return this.codLoja == loja.getCodLoja() &&
                this.nome.equals(loja.getNome()) &&
                this.localizacao.equals(loja.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codLoja, nome, localizacao);
    }
}
