package TrazAqui;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIO o = new FileIO("teste.txt","output.txt");
        Estado e = new Estado();
        o.loadFromFile(e);
        //System.out.println(o.validaDados("as","d","f"));
    }
}