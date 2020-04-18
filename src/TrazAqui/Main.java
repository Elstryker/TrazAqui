package TrazAqui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIO o = new FileIO();
        Estado e = new Estado();
        o.processLine(e);
    }
}
