package TrazAqui;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Opcoes opcao;
    private boolean exec;


    public enum Opcoes{
        Menu,
        Login,
        Registo,
        RegistoUtil,
        RegistoLoja,
        RegistoTrans,
        RegistoVol,
        Utilizador;
    }

    public Menu(){
        this.opcao = Opcoes.Menu;
        this.exec = true;
    }

    public boolean stopExec(){
        return !exec;
    }

    public Opcoes getOpcao(){
        return this.opcao;
    }

    public void loginUtilizador() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        estado.getConta(email,password,io);
    }

    public void novoRegisto() throws IOException {
        Scanner sc = new Scanner(System.in);
        FileIO io = new FileIO();
        Estado est = new Estado();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Codigo: ");
        String cod = sc.nextLine();
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Latitude: ");
        double lat = sc.nextDouble();
        System.out.println("Longitude: ");
        double longi = sc.nextDouble();
        System.out.println("Tipo de conta");
        String tipo = sc.nextLine();

        est.registaConta(email,password,cod,nome,new GPS(lat,longi),io,tipo);
    }
}
