package TrazAqui;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    private String path;

    public FileIO() {
        this.path = "";
    }

    public FileIO(String p) {
        this.path = p;
    }

    public FileIO(FileIO f) {
        this.path = f.getPath();
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String p) {
        this.path = p;
    }

    public void processLine(Estado e) throws IOException {
        BufferedReader file;
        GPS gps;
        file = new BufferedReader(new FileReader(this.path));
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
                        break;
                    case "Voluntario":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Voluntario v = new Voluntario();
                        v.setRaio(Double.parseDouble(tokens[4]));
                        v.setNomeVoluntario(tokens[1]);
                        v.setCodVoluntario(tokens[0]);
                        e.addVoluntario(v);
                        break;
                    case "Transportadora":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Transportadora t = new Transportadora();
                        t.setCodEmpresa(tokens[0]);
                        t.setNomeEmpresa(tokens[1]);
                        t.setLocalizacao(gps);
                        t.setRaio(Double.parseDouble(tokens[5]));
                        t.setPrecoKM(Double.parseDouble(tokens[6]));
                        e.addTransportadora(t);
                        break;
                    case "Loja":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Loja j = new Loja();
                        j.setCodLoja(tokens[0]);
                        j.setNome(tokens[1]);
                        j.setLocalizacao(gps);
                        e.addLoja(j);
                        break;
                    case "Encomenda":
                        Encomenda s = new Encomenda();
                        s.setCodigo(tokens[0]);
                        s.setDescricao(tokens[1]);
                        s.setLoja(tokens[2]);
                        s.setPeso(Double.parseDouble(tokens[3]));
                        LinhaEncomenda linha = new LinhaEncomenda();
                        for(int ind = 4; ind < tokens.length; ind+=4) {
                            linha.setCodigo(tokens[ind]);
                            linha.setDescricao(tokens[1+ind]);
                            linha.setQuantidade(Double.parseDouble(tokens[2+ind]));
                            linha.setPreco(Double.parseDouble(tokens[3+ind]));
                            s.addProduto(linha);
                        }
                        break;
                    default:
                        break;
                }
            }
            else i++;
            line = file.readLine();
        }
        file.close();
    }
}
