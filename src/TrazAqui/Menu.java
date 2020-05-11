package TrazAqui;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private boolean exec;
    private FileIO f;
    private Estado e;

    public Menu(){
        this.exec = true;
        this.f = new FileIO("teste.txt","output.txt","Credentials.txt");
        this.e = new Estado();
    }

    public void run() throws IOException {
        Scanner inp = new Scanner(System.in);
        int opcao = -1;
        f.loadFromFile(e);
        while(e.getLogin() == null || opcao == 0) {
            UI.printMenuInicial();
            while((opcao = inp.nextInt()) < 0 || opcao > 2) {
                UI.printIncorrectInput();
            }
            switch(opcao) {
                case 0:
                    stopExec();
                    break;
                case 1:
                    try {
                        this.novoRegisto();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        this.loginUtilizador();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcao);
            }
        }
        while(this.exec) {

        }
    }

    public boolean stopExec(){
        return !exec;
    }

    public void loginUtilizador() throws IOException {
        Estado estado = new Estado();
        FileIO io = new FileIO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        estado.login(email,password,io);
    }

    public void novoRegisto() throws IOException {
        Scanner sc = new Scanner(System.in);
        Estado est = new Estado();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Codigo: ");
        String cod = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Latitude: ");
        double lat = sc.nextDouble();
        System.out.print("Longitude: ");
        double longi = sc.nextDouble();
        System.out.print("Regista-se como Utilizador, Loja, Transportadora ou Voluntario?: ");
        String tipo = sc.nextLine();

        est.registar(email,password,cod,nome,new GPS(lat,longi),this.f,tipo);
    }
}
