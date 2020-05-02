package TrazAqui;

public interface Entrada {
    String getCod();
    void setCod(String  n);
    String getNome();
    void setNome(String n);
    GPS getLocalizacao();
    void setLocalizacao(GPS a);
    Entrada clone();
    String toString();
    boolean equals(Object o);
    void menu();

}
