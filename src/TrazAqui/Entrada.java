package TrazAqui;

public interface Entrada {
    public String getCod();
    public void setCod(String  n);
    public String getNome();
    public void setNome(String n);
    public GPS getLocalizacao();
    public void setLocalizacao(GPS a);
    public Entrada clone();
    public String toString();
    public boolean equals(Object o);
    public String toStringNome();
    public default Entrada newEntrada(String tipo) {
        Entrada a = null;
        switch (tipo) {
            case "Utilizador":
                a = new Utilizador();
                break;
            case "Transportadora":
                a = new Transportadora();
                break;
            case "Voluntario":
                a = new Voluntario();
                break;
            case "Loja":
                a = new Loja();
                break;
            case "LojaFilaEspera":
                a = new LojaFilaEspera();
                break;
            default:
                break;
        }
        return a;
    }
}
