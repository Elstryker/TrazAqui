package TrazAqui;

import java.util.*;

public class Voluntario extends Estafeta {

    public Voluntario() {
        super();
    }

    public Voluntario(String cod, String nome, GPS localizacao, double raio, List<Encomenda> encomendasEntregues, List<Encomenda> pedidosEncomenda, int[] classificacao, boolean disponivel) {
        super(cod, nome, localizacao, raio, encomendasEntregues, pedidosEncomenda, classificacao, disponivel);
    }

    public Voluntario(Estafeta a) {
        super(a);
    }

}