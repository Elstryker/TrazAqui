package TrazAqui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Menu m = new Menu();
        try {
            m.run();
        } catch (IOException | LojaInexistenteException e) {
            e.printStackTrace();
        }
    }
}