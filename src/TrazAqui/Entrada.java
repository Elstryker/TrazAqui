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
}
