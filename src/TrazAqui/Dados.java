package TrazAqui;

import java.io.*;
import java.util.ArrayList;

public class Dados {
    private ArrayList<String> dados;

    public Dados() {
        this.dados = new ArrayList<>();
    }
    public Dados(ArrayList<String> d) {
        this.dados = new ArrayList<>(d);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || !this.getClass().equals(o.getClass())) return false;

        Dados d = (Dados) o;

        return this.dados.equals(d.getDados());
    }

    public Dados(Dados d) {
        setDados(d.getDados());
    }

    public Dados clone() {
        return new Dados(this);
    }

    public void leitor(String path) throws IOException {
        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        String data = null;

        while ((data = reader.readLine())!=null) {
            this.dados.add(data);
        }
        file.close();
        reader.close();
    }

    public void escritor(String path) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String s : this.dados) {
            writer.write(s);
            writer.write("\n");
        }
        writer.close();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dados{");
        sb.append("dados=").append(dados);
        sb.append('}');
        return sb.toString();
    }

    public ArrayList<String> getDados() {
        return new ArrayList<>(this.dados);
    }

    public void setDados(ArrayList<String> dados) {
        this.dados = new ArrayList<>();
        this.dados.addAll(dados);
    }
}
