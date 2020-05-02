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

    public Opcoes getOpcao(){
        return this.opcao;
    }

    public Utilizador loginUtilizador() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        return estado.getContaUtil(email,password);
    }

    public Loja loginLoja() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        return estado.getContaLoja(email,password);
    }

    public Transportadora loginTrans() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        return estado.getContaTrans(email,password);
    }

    public Voluntario loginVol() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        return estado.getContaVol(email,password);
    }

}
