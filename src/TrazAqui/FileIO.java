package TrazAqui;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
    private String readPath;
    private String writePath;

    public FileIO() {
        this.readPath = "";
        this.writePath = "";
    }

    public FileIO(String p, String p2) {
        this.readPath = p;
        this.writePath = p2;
    }

    public FileIO(FileIO f) {
        this.readPath = f.getReadPath();
        this.writePath = f.getWritePath();
    }

    public String getReadPath() {
        return readPath;
    }

    public void setReadPath(String readPath) {
        this.readPath = readPath;
    }

    public String getWritePath() {
        return writePath;
    }

    public void setWritePath(String writePath) {
        this.writePath = writePath;
    }

    public void loadFromFile(Estado e) throws IOException {
        BufferedReader file;
        GPS gps;
        file = new BufferedReader(new FileReader(this.readPath));
        String line = file.readLine(), temp;
        String[] tokens;
        Map<String,Encomenda> buffer = new HashMap<>();
        int start = 42;
        int i = 0;
        while(line!=null) {
            if(i > start) {
                tokens = line.split(":");
                temp = tokens[0];
                tokens = tokens[1].split(",");
                switch(temp) {
                    case "Utilizador":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Utilizador u = new Utilizador(tokens[1],tokens[0],gps,0,new HashMap<>());
                        e.addUtilizador(u);
                        break;
                    case "Voluntario":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Voluntario v = new Voluntario();
                        v.setRaio(Double.parseDouble(tokens[4]));
                        v.setNome(tokens[1]);
                        v.setCod(tokens[0]);
                        v.setLocalizacao(gps);
                        e.addTrabalhador(v);
                        break;
                    case "Transportadora":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Transportadora t = new Transportadora();
                        t.setCod(tokens[0]);
                        t.setNome(tokens[1]);
                        t.setLocalizacao(gps);
                        t.setRaio(Double.parseDouble(tokens[5]));
                        t.setPrecoKM(Double.parseDouble(tokens[6]));
                        e.addTrabalhador(t);
                        break;
                    case "Loja":
                        gps = new GPS(Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]));
                        Loja j = new Loja();
                        j.setCod(tokens[0]);
                        j.setNome(tokens[1]);
                        j.setLocalizacao(gps);
                        e.addLoja(j);
                        break;
                    case "Encomenda":
                        Encomenda s = new Encomenda();
                        s.setCod(tokens[0]);
                        s.setUtilizador(tokens[1]);
                        s.setLoja(tokens[2]);
                        s.setPeso(Double.parseDouble(tokens[3]));
                        LinhaEncomenda linha = new LinhaEncomenda();
                        for(int ind = 4; ind < tokens.length; ind+=4) {
                            linha.setCod(tokens[ind]);
                            linha.setDescricao(tokens[1+ind]);
                            linha.setQuantidade(Double.parseDouble(tokens[2+ind]));
                            linha.setPreco(Double.parseDouble(tokens[3+ind]));
                            s.addProduto(linha);
                        }
                        buffer.put(s.getCod(),s);
                        break;
                    case "Aceite":
                        Encomenda enc = buffer.get(tokens[0]).clone();
                        buffer.remove(tokens[0]);
                        e.addEncomendaUtilizador(enc.getUtilizador(),enc);
                        break;
                    default:
                        break;
                }
            } else i++;
            line = file.readLine();
        }

        if (!buffer.isEmpty()) {
            for (Map.Entry<String, Encomenda> aux : buffer.entrySet()) {
                    e.addEncomendaLoja(aux.getValue().getLoja(), aux.getValue());
            }
        }
        file.close();
    }

    public void saveToFile(Estado e) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(this.writePath));
        StringBuilder sb = new StringBuilder();

        for(Utilizador u: e.getUtilizadores().values())
            file.write("Utilizador:"+u.getCod()+","+u.getNome()+","+u.getLocalizacao().getLatitude()+","+u.getLocalizacao().getLongitude()+"\n");

        for(Estafeta u: e.getTrabalhadores().values()) {
            if (u instanceof Voluntario) {
                Voluntario v = (Voluntario) u;
                file.write("Voluntario:"+v.getCod()+","+v.getNome()+","+v.getLocalizacao().getLatitude()+","+v.getLocalizacao().getLongitude()+","+v.getRaio()+"\n");
            } else if (u instanceof Transportadora) {
                Transportadora t = (Transportadora) u;
                file.write("Transportadora:"+t.getCod()+","+t.getNome()+","+t.getLocalizacao().getLatitude()+","+t.getLocalizacao().getLongitude()+","+t.getNIF()+","+u.getRaio()+","+t.getPrecoKM()+"\n");
            }
        }
        for(Loja u: e.getLojas().values())
            file.write("Loja:"+u.getCod()+","+u.getNome()+","+u.getLocalizacao().getLatitude()+","+u.getLocalizacao().getLongitude()+"\n");

        for(Loja u: e.getLojas().values())
            for(Encomenda enc: u.getPedidos()) {
                file.write("Encomenda:"+enc.getCod()+","+enc.getUtilizador()+","+enc.getLoja()+","+enc.getPeso());
                for(LinhaEncomenda linha: enc.getProdutos())
                    file.write(","+linha.getCod()+","+linha.getDescricao()+","+linha.getQuantidade()+","+linha.getPreco());
                file.newLine();
            }

        for (Utilizador u : e.getUtilizadores().values()) {
            for (Encomenda enc : u.getEncomendasConcluidas().values()) {
                file.write("Encomenda:"+enc.getCod()+","+enc.getUtilizador()+","+enc.getLoja()+","+enc.getPeso());
                for(LinhaEncomenda linha: enc.getProdutos())
                    file.write(","+linha.getCod()+","+linha.getDescricao()+","+linha.getQuantidade()+","+linha.getPreco());
                file.newLine();
            }
        }

        for (Estafeta u : e.getTrabalhadores().values()) {
            for (Encomenda enc : u.getEncomendasEntregues()) {
                file.write("Encomenda:"+enc.getCod()+","+enc.getUtilizador()+","+enc.getLoja()+","+enc.getPeso());
                for(LinhaEncomenda linha: enc.getProdutos())
                    file.write(","+linha.getCod()+","+linha.getDescricao()+","+linha.getQuantidade()+","+linha.getPreco());
                file.newLine();
            }
            for (Encomenda enc : u.getPedidosEncomenda()) {
                file.write("Encomenda:"+enc.getCod()+","+enc.getUtilizador()+","+enc.getLoja()+","+enc.getPeso());
                for(LinhaEncomenda linha: enc.getProdutos())
                    file.write(","+linha.getCod()+","+linha.getDescricao()+","+linha.getQuantidade()+","+linha.getPreco());
                file.newLine();
            }
        }

        for (Utilizador u : e.getUtilizadores().values()) {
            for (Encomenda enc : u.getEncomendasConcluidas().values()) {
                file.write("Aceite:" + enc.getCod());
                file.newLine();
            }
        }

        file.close();
    }

    public void adicionaUtilizador(String email, String password,String nome) throws IOException {
        File file = new File("Utilizadores.txt");

        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(email + "," + password + "," + nome);
        writer.write("\n");
        writer.flush();
        writer.close();
    }

    public String validaDados(String email,String pass) throws IOException {
        boolean a = false;
        String cod = "";
        FileReader file = new FileReader("Utilizadores.txt");
        BufferedReader reader = new BufferedReader(file);
        String data = null;
        String[] tok;

        while ((data = reader.readLine())!=null && !a) {
           tok= data.split(",");
           if(tok[0].equals(email) && tok[1].equals(pass)) {
               a=true;
               cod=tok[2];
           }
        }
        file.close();
        reader.close();

        return cod;
    }



}
