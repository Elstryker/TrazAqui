package TrazAqui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIO o = new FileIO("teste.txt","output.txt","Credentials.txt");
        Estado e = new Estado();
        o.loadFromFile(e);

        List<Estafeta> l = e.getTop10Trans();

        for (Estafeta u : l) {
            System.out.println(u.toString());
        }


    }
}