package TrazAqui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu m = new Menu();
        try {
            m.run();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}