package TrazAqui;

import java.io.*;

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
                        v.setLocalizacao(gps);
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
                        j.setCod(tokens[0]);
                        j.setNome(tokens[1]);
                        j.setLocalizacao(gps);
                        e.addLoja(j);
                        break;
                    case "Encomenda":
                        Encomenda s = new Encomenda();
                        s.setCodigo(tokens[0]);
                        s.setUtilizador(tokens[1]);
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
                        e.addEncomendaLoja(s,s.getLoja());
                        break;
                    case "Aceite":
                        e.addEncAceite(tokens[0]);
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

    public void saveToFile(Estado e) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(this.writePath));
        StringBuilder sb = new StringBuilder();
        for(Utilizador u: e.getUtilizadores().values())
            file.write("Utilizador:"+u.getCodigo()+","+u.getNome()+","+u.getPosicao().getLatitude()+","+u.getPosicao().getLongitude()+"\n");
        for(Voluntario u: e.getVoluntarios().values())
            file.write("Voluntario:"+u.getCodVoluntario()+","+u.getNomeVoluntario()+","+u.getLocalizacao().getLatitude()+","+u.getLocalizacao().getLongitude()+","+u.getRaio()+"\n");
        for(Transportadora u: e.getTransportadoras().values())
            file.write("Transportadora:"+u.getCodEmpresa()+","+u.getNomeEmpresa()+","+u.getLocalizacao().getLatitude()+","+u.getLocalizacao().getLongitude()+","+u.getNIF()+","+u.getRaio()+","+u.getPrecoKM()+"\n");
        for(Loja u: e.getLojas().values())
            file.write("Loja:"+u.getCod()+","+u.getNome()+","+u.getLocalizacao().getLatitude()+","+u.getLocalizacao().getLongitude()+"\n");
        for(Loja u: e.getLojas().values())
            for(Encomenda enc: u.getFilaEspera()) {
                file.write("Encomenda:"+enc.getCodigo()+","+enc.getUtilizador()+","+enc.getLoja()+","+enc.getPeso());
                for(LinhaEncomenda linha: enc.getProdutos())
                    file.write(","+linha.getCodigo()+","+linha.getDescricao()+","+linha.getQuantidade()+","+linha.getPreco());
                file.newLine();
            }
        for(String u: e.getEncAceites())
            file.write("Aceite:"+u+"\n");
        file.close();
    }

    public void adicionaUtilizador(String email, String password,String nome) throws IOException {
        File file = new File("Utilizadores.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(email + "," + password + "," + nome);
        writer.write("\n");
        writer.flush();
        writer.close();
    }

    public boolean validaDados(String email,String pass,String nome) throws IOException {
        boolean a = false;
        FileReader file = new FileReader("Utilizadores.txt");
        BufferedReader reader = new BufferedReader(file);
        String data = null;
        String[] tok;

        while ((data = reader.readLine())!=null && !a) {
           tok= data.split(",");
           if(tok[0].equals(email) && tok[1].equals(pass) && tok[2].equals(nome)) a=true;
        }
        file.close();
        reader.close();

        return a;
    }

}
