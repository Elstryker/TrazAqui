package TrazAqui;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIO o = new FileIO("teste.txt");
        Scanner sc = new Scanner(System.in);
        Estado e = new Estado();
        o.processLine(e);
        o.adicionaUtilizador("ola","adeus","pedro");
        o.adicionaUtilizador("as","d","f");
    }
}
