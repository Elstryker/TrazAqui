package TrazAqui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Validacao {
    private Map<String,String> dados;

    public  Validacao(){
        this.dados = new HashMap<>();
    }

    public Validacao(Validacao v){
        this.dados = v.dados;
    }

    public Validacao(Map<String, String> dados) {
        this.dados = dados;
    }

    public Map<String, String> getDados() {
        return dados;
    }

    public void setDados(Map<String, String> dados) {
        this.dados = dados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Validacao validacao = (Validacao) o;
        return Objects.equals(dados, validacao.dados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dados);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Validacao{");
        sb.append("dados=").append(dados);
        sb.append('}');
        return sb.toString();
    }

    public Validacao clone(){
        return new Validacao(this);
    }

    public void novoUtilizador(String email, String pass){
        this.dados.putIfAbsent(email,pass);
    }

    public boolean validaEmail(String email){
        boolean a=false;

        if(this.dados.containsKey(email)) a=true;

        return a;
    }

    public boolean validaPass(String email,String pass){
        boolean a=false;

        if(this.dados.get(email).equals(pass)) a=true;

        return a;
    }

}
