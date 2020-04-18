package TrazAqui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dados d = new Dados();

        d.leitor("teste.txt");
        d.escritor("ola.txt");

        System.out.println(d.toString());

    }
}
