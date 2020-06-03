package TrazAqui;

import java.util.*;

public class Voluntario extends Estafeta {
    /**
     * Construtor vazio
     */
    public Voluntario() {
        super();
    }

    /**
     * Construtor parametrizado
     * @param cod String
     * @param nome String
     * @param localizacao GPS
     * @param raio double
     * @param encomendasEntregues List<Encomenda>
     * @param pedidosEncomenda List<Encomenda>
     * @param classificacao int[]
     * @param disponivel boolean
     * @param certificada boolean
     */
    public Voluntario(String cod, String nome, GPS localizacao, double raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda, int[] classificacao, boolean disponivel, boolean certificada) {
        super(cod, nome, localizacao, raio, encomendasEntregues, pedidosEncomenda, classificacao, disponivel, certificada);
    }

    /**
     * Construtor por copia 
     * @param a
     */
    public Voluntario(Estafeta a) {
        super(a);
    }

    /**
     * Retorna uma copia da class que a chama
     * @return Voluntario
     */
    public Voluntario clone() {
        return new Voluntario(this);
    }

    /**
     * Retorna o nome 
     * @return String
     */
    public String toStringNome() {
        return "Voluntario";
    }

}
