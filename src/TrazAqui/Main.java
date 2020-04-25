package TrazAqui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIO o = new FileIO("teste.txt");
        Estado e = new Estado();
        o.processLine(e);
    }
}
