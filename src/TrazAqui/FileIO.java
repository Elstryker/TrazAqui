package TrazAqui;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    private ArrayList<String> lines;

    public FileIO() {
        lines = new ArrayList<>();
    }

    public void processLine() throws IOException {
        BufferedReader file;
        file = new BufferedReader(new FileReader("teste.txt"));
        String line = file.readLine();
        String[] tokens;
        int start = 42;
        int i = 0;
        while(line!=null) {
            if(i > start) {
                tokens = line.split(":");
                System.out.print(tokens[0]+"\n");
                tokens = tokens[1].split(",");
                for(String s : tokens) System.out.println(s);
                System.out.println("Proximo elemento");
            }
            else i++;
            line = file.readLine();
        }
        file.close();
    }
}
