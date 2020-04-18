package TrazAqui;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    private ArrayList<String> lines;

    public FileIO() {
        lines = new ArrayList<>();
    }

    public void processLine(Estado e) throws IOException {
        BufferedReader file;
        GPS gps;
        file = new BufferedReader(new FileReader("teste.txt"));
        String line = file.readLine(), temp;
        String[] tokens;
        int start = 42;
        int i = 0;
        while(line!=null) {
            if(i > start) {
                tokens = line.split(":");
                temp = tokens[0];
                tokens = tokens[1].split(",");
                for(String s : tokens) System.out.println(s);
                switch(temp) {
                    case "Utilizador":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Utilizador u = new Utilizador(tokens[1],tokens[0],gps);
                        e.addUtilizador(u);
                    case "Voluntario":
                        gps = new GPS(Double.parseDouble(tokens[3]),Double.parseDouble(tokens[4]));
                        Voluntario v = new Voluntario();
                        v.setRaio(Integer.parseInt(tokens[5]));
                        v.setNomeVoluntario(tokens[1]);
                        v.setCodVoluntario(tokens[0]);
                    case "Transportadora":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Transportadora t = new Transportadora();
                        t.setCodEmpresa(tokens[0]);
                        t.setNomeEmpresa(tokens[1]);
                        t.setLocalizacao(gps);
                        t.setRaio(Integer.parseInt(tokens[5]));
                        t.setPrecoKM(Double.parseDouble(tokens[6]));
                    default:
                        throw new IllegalStateException("Unexpected value: " + tokens[0]);
                }
            }
            else i++;
            line = file.readLine();
        }
        file.close();
    }
}
