package TrazAqui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private boolean exec;
    private FileIO f;
    private Estado e;

    public Menu(){
        this.exec = true;
        this.f = new FileIO("teste.txt","Estado.txt","Credentials.txt");
        this.e = new Estado();
    }

    public void run() throws IOException {
        Scanner inp = new Scanner(System.in);
        int opcao = -1;
        f.loadFromFile(e);
        while(e.getLogin() == null && this.exec) {
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
                        this.loginUtilizador();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        this.novoRegisto();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        this.e = f.readObjectStream();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + opcao);
            }
        }
        if(this.exec)
            switch(e.getLogin().getClass().getSimpleName()) {
                case "Utilizador":
                    while(this.exec) {
                        UI.printMenuUtilizador();
                        if(!MenuUtilizador()) stopExec();
                    }
                    break;
                case "Transportadora":
                    while(this.exec) {
                        UI.printMenuTransportadora();
                        if(!MenuTransportadora()) stopExec();
                    }
                    break;
                case "Voluntario":
                    while(this.exec) {
                        UI.printMenuVoluntario();
                        if(!MenuVoluntario()) stopExec();
                    }
                    break;
                case "Loja":
                    while(this.exec) {
                        UI.printMenuLoja();
                        if(!MenuLoja()) stopExec();
                    }
            }
        UI.goodbye();
    }

    public void stopExec(){
        this.exec = false;
    }

    public void loginUtilizador() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        this.e.login(email,password,this.f);
    }

    public void novoRegisto() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Regista-se como Utilizador, Loja, Transportadora ou Voluntario?: ");
        String tipo = sc.nextLine();
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
        this.e.registar(email,password,cod,nome,new GPS(lat,longi),this.f,tipo);
    }

    public Boolean menuUtilizador() throws IOException {
        int opcao =0;
        Scanner sc = new Scanner(System.in);
        UI.printMenuUtilizador();
        opcao = sc.nextInt();
        switch(opcao){
            case 1:
                Encomenda enc = new Encomenda();
                double preco,quantidade ;
                boolean fragil,conti = true;
                String cod,descricao;
                while(conti) {
                    UI.print("Faça uma descrição do produto: ");
                    descricao = sc.nextLine();
                    UI.print("Indique preço: ");
                    preco = sc.nextDouble();
                    UI.print("Indique a quantidade :");
                    quantidade = sc.nextDouble();
                    UI.print("Indique se é frágil ou não: ");
                    fragil = sc.nextBoolean();
                    UI.print("Indique o código do produto: ");
                    cod = sc.nextLine();
                    enc.addProduto(new LinhaEncomenda(descricao, preco, quantidade, fragil, cod));
                    UI.print("Deseja pedir mais produtos?");
                    conti = sc.nextBoolean();
                }
                break;
            case 2:
                    UI.print("Histórico de encomendas: \n");

                break;
            default:
                break;
        }
    }


}
//public LinhaEncomenda(String descricao, double preco,double quantidade,boolean fragil, String codigo)
//public Encomenda(double peso, boolean med, String descricao, String cod, String utilizador, String loja,ArrayList<LinhaEncomenda> produtos, LocalDateTime l)