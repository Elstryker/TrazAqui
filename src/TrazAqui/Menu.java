package TrazAqui;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private boolean exec;
    private FileIO f;
    private Estado e;


    public Menu() {
        this.exec = true;
        this.f = new FileIO("teste.txt", "Estado.txt", "Credentials.txt");
        this.e = new Estado();
    }

    public void run() throws IOException {
        Scanner inp = new Scanner(System.in);
        int opcao = -1;
        f.loadFromFile(e);
        while (e.getLogin() == null && this.exec) {
            UI.printMenuInicial();
            while ((opcao = inp.nextInt()) < 0 || opcao > 2) {
                UI.printIncorrectInput();
            }
            switch (opcao) {
                case 0:
                    stopExec();
                    break;
                case 1:
                    try {
                        this.loginUtilizador();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (LoginException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    try {
                        this.novoRegisto();
                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (LoginException l) {
                        System.out.println(l.getMessage());
                    }
                    break;
                case 3:
                    try {
                        this.e = f.readObjectStream();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + opcao);
            }
        }
        if (this.exec)
            switch (e.getLogin().getClass().getSimpleName()) {
                case "Utilizador":
                    while (this.exec) {
                        if (!menuUtilizador()) stopExec();
                    }
                    break;
                case "Transportadora":
                    while (this.exec) {
                        UI.printMenuTransportadora();
                        // if(!MenuTransportadora()) stopExec();
                    }
                    break;
                case "Voluntario":
                    while (this.exec) {
                        UI.printMenuVoluntario();
                        //    if(!MenuVoluntario()) stopExec();
                    }
                    break;
                case "Loja":
                    while (this.exec) {
                        UI.printMenuLoja();
                        // if(!MenuLoja()) stopExec();
                    }
                default:
                    break;
            }
        UI.goodbye();
    }

    public void stopExec() {
        this.exec = false;
    }

    public void loginUtilizador() throws IOException , LoginException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (!verifica(email)) throw new LoginException("Email invalido!");

        System.out.println("Password: ");
        String password = sc.nextLine();
        this.e.login(email, password, this.f);
    }

    private boolean verifica(String mail) {
        String[] tokens;
        String temp;
        tokens = mail.split("@");
        if (tokens[0].equals(mail)) return false;
        temp = tokens[1];
        tokens = tokens[1].split("\\.");
        if (tokens[1].equals(temp)) return false;
        return true;
    }

    public void novoRegisto() throws IOException, LoginException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Regista-se como Utilizador, Loja, Transportadora ou Voluntario?: ");
        String tipo = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (!verifica(email)) {
            throw new LoginException("Email invalido!");
        }
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Código: ");
        String cod = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Latitude: ");
        double lat = sc.nextDouble();
        System.out.print("Longitude: ");
        double longi = sc.nextDouble();
        this.e.registar(email, password, cod, nome, new GPS(lat, longi), this.f, tipo);
    }

    public boolean menuUtilizador() throws IOException {
        boolean bool = true;
        int opcao =-1;
        Scanner sc = new Scanner(System.in);
        while(opcao!=0) {
            UI.printMenuUtilizador();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    Encomenda enc = new Encomenda();
                    double preco, quantidade;
                    boolean fragil, conti = true;
                    String cod, descricao;
                    while (conti) {
                        UI.printFazerDescricao();
                        descricao = sc.nextLine();
                        UI.printIndicarPreco();
                        preco = sc.nextDouble();
                        UI.printIndicarQuant();
                        quantidade = sc.nextDouble();
                        UI.printIndicarFragil();
                        fragil = sc.nextBoolean();
                        sc.nextLine();
                        UI.printIndiqueCodProd();
                        cod = sc.nextLine();
                        enc.addProduto(new LinhaEncomenda(descricao, preco, quantidade, fragil, cod));
                        UI.printDesejaMaisProd();
                        conti = sc.nextBoolean();
                    }
                    break;
                case 2:
                    UI.printHistoricoEncomendas();
                    String codigoUtilizador = e.getLogin().getCod();
                    e.getUtilizador(codigoUtilizador).getEncomendasConcluidas();
                    break;
                case 3:
                    String worker = "";
                    int clas = 0;
                    UI.printTrabalhadores(e.getTrabalhadores()); //("Voluntário/Utilizador ");
                    UI.print("Indique o códido do estafeta que deseja avaliar: ");
                    worker = sc.nextLine();
                    UI.print("Indique a classificação que deseja dar: ");
                    clas = sc.nextInt();
                    e.getEstafeta(worker).classifica(clas);
                    break;
                case 0:
                    bool = false ;
                    break;
                default:
                    UI.printIncorrectInput();
                    break;
            }
        }
        return bool;
    }

    public boolean menuVoluntario() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuVoluntario();
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch (opcao) {
            case 1:
                this.e.mudaDisponibilidade(cod);
                break;
            case 2:
                List<Encomenda> enc = this.e.encomendasDisponiveis(cod);
                UI.printEncomendas(enc);
                String codEncomenda = sc.nextLine();
                this.e.getLojas().get(codEncomenda).removePedido(codEncomenda);
                for (Encomenda e : enc) {
                    if (e.getCod().equals(codEncomenda)) {
                        this.e.getUtilizadores().get(cod).addEncomenda(e);
                        break;
                    }
                }
                break;
            case 3:
                break;
            default:
                break;
        }
        return true;
    }

    public boolean menuTransportadora() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuTransportadora();
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch(opcao){
            case 1:
                this.e.mudaDisponibilidade(cod);
                break;
            case 2:
                UI.print("Codigo de encomenda: ");
                String codEncomenda = sc.nextLine();
                double p = this.e.precoDaEncomenda(codEncomenda,cod);
                UI.printPreco(p);
                break;
            case 3:
                break;
            default:
                break;
        }
        return true;
    }


    public boolean menuLojas() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuLoja();
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch(opcao){
            case 1:
                this.e.existeEncomenda(cod);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return true;
    }
}