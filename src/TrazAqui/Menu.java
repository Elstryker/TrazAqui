package TrazAqui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private boolean exec;
    private FileIO f;
    private Estado e;

    public Menu() {
        this.exec = true;
        this.f = new FileIO("teste.txt", "Estado", "Credentials.txt");
        this.e = new Estado();
    }

    public void run() throws IOException {
        Scanner inp = new Scanner(System.in);
        int opcao;
        try {
            e = f.readObjectStream();
        }
        catch (IOException | ClassNotFoundException ex) {
            f.loadFromFile(e);
        }
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
                        menuUtilizador();
                    }
                    break;
                case "Transportadora":
                    while (this.exec) {
                        menuTransportadora();
                    }
                    break;
                case "Voluntario":
                    while (this.exec) {
                        menuVoluntario();
                    }
                    break;
                case "Loja":
                    while (this.exec) {
                        UI.printMenuLoja();
                        menuLoja();
                    }
                default:
                    break;
            }
        try {
            f.saveObjectStream(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        UI.goodbye();
    }

    public void stopExec() {
        this.exec = false;
        e.logoff();
    }

    public void loginUtilizador() throws IOException , LoginException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (verifica(email)) throw new LoginException("Email invalido!");

        System.out.print("Password: ");
        String password = sc.nextLine();
        this.e.login(email, password, this.f);
    }

    private boolean verifica(String mail) {
        String[] tokens;
        String temp;
        tokens = mail.split("@");
        if (tokens[0].equals(mail)) return true;
        temp = tokens[1];
        tokens = tokens[1].split("\\.");
        return tokens[1].equals(temp);
    }

    public void novoRegisto() throws IOException, LoginException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Regista-se como Utilizador, Loja, Transportadora ou Voluntario?: ");
        String tipo = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (verifica(email)) {
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

    public boolean menuUtilizador() {
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
                    Map<String,Encomenda> lstEnc = e.getUtilizador(codigoUtilizador).getEncomendasConcluidas();
                    UI.print("Indique o periodo em que quer procurar");
                    UI.print("Indique os anos iniciais e finais");
                    int anoi = sc.nextInt();
                    int anof = sc.nextInt();
                    UI.print("Indique os meses inicial e final");
                    int mesi = sc.nextInt();
                    int mesf = sc.nextInt();
                    UI.print("Indique o dia do mes inicial e final");
                    int diamesi = sc.nextInt();
                    int diamesf = sc.nextInt();
                    UI.print("Indique a hora inicial e final");
                    int horai = sc.nextInt();
                    int horaf = sc.nextInt();
                    UI.print("Indique os minutos iniciais e finais");
                    int minutosi = sc.nextInt();
                    int minutosf = sc.nextInt();
                    sc.nextLine();
                    LocalDateTime inicio = LocalDateTime.of(anoi,mesi,diamesi,horai,minutosi);
                    LocalDateTime fim = LocalDateTime.of(anof,mesf,diamesf,horaf,minutosf);
                    UI.print("Indique o estafeta que deseja procurar: ");
                    String codEst = sc.nextLine();
                    UI.printEncomendas(e.getEstafeta(codEst).procuraPor(inicio,fim));
                    break;
                case 3:
                    int clas = 0;
                    for(Map.Entry<String,Estafeta> a : e.getTrabalhadores().entrySet()){
                        System.out.println(a.getValue().getPedidosEncomenda());
                    }
                    UI.print("Indique o codigo do estafeta cuja encomenda deseja aceitar:");
                    String codEsta = sc.nextLine();
                    UI.print("Indique o indice da encomenda que deseja aceitar: ");
                    int index = sc.nextInt();
                    Encomenda encomenda = e.getEstafeta(codEsta).getPedidosEncomenda().get(index);
                    e.getEstafeta(codEsta).addEncomendaEntregue(encomenda);
                    UI.print("Indique a classificação que deseja dar: ");
                    clas = sc.nextInt();
                    e.getEstafeta(codEsta).classifica(clas);
                    break;
                case 0:
                    bool = false ;
                    break;
                case 4:
                    UI.printUtilizadores(this.e.getTop10Util());
                default:
                    UI.printIncorrectInput();
                    break;
            }
        }
        return bool;
    }

    public void menuVoluntario() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch (opcao) {
            case 0:
                exec = false;
            case 1:
                this.e.mudaDisponibilidade(cod);
                break;
            case 2:
                List<Encomenda> enc = this.e.encomendasDisponiveis(cod);
                UI.printEncomendas(enc);
                UI.print("Codigo da encomenda: ");
                String codEncomenda = sc.nextLine();
                this.e.getLojas().get(codEncomenda).removePedido(codEncomenda);
                for (Encomenda e : enc) {
                    if (e.getCod().equals(codEncomenda)) {
                        this.e.getTrabalhadores().get(cod).addEncomendaEntregue(e);
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public void menuTransportadora() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch(opcao){
            case 0:
                try {
                    f.saveObjectStream(e);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                exec = false;
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
                UI.print("Codigo da encomenda: ");
                String codEnc = sc.nextLine();
                for (Loja lj : this.e.getLojas().values()){
                    for (Encomenda e : lj.getPedidos()) {
                        if (e.getCod().equals(codEnc)) {
                            lj.removePedido(codEnc);
                        }
                    }
                }
                this.e.getTrabalhadores().get(cod).removerEncomenda(codEnc);
                break;
            case 4:
                UI.printTransportadoras(this.e.getTop10Trans());
                break;
            case 5:
                int i=0;
                LocalDateTime[] data = new LocalDateTime[2];
                while (i<2) {
                    UI.print("Indique o ano: ");
                    int ano = sc.nextInt();
                    UI.print("Indique o mes: ");
                    int mes = sc.nextInt();
                    UI.print("Indique o dia: ");
                    int dia = sc.nextInt();
                    data[i++] = LocalDateTime.of(ano,mes,dia,0,0);
                }
                Transportadora t = (Transportadora) this.e.getTrabalhadores().get(cod);
                UI.printTotFat(this.e.totalFaturado(t,data[0],data[1]));
            default:
                break;
        }
    }

    public void menuLoja() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch(opcao){
            case 0:
                stopExec();
                try {
                    f.saveObjectStream(e);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                UI.printEncomendas(e.getLoja(e.getLogin().getCod()).getPedidos());

                break;
            case 2:
                if(e.getLogin() instanceof LojaFilaEspera) {
                    LojaFilaEspera l = (LojaFilaEspera) e.getLogin();
                    UI.print("Tamanho da lista de espera: "+l.getTamanhoListaEspera());
                }
                else {
                    UI.print("Esta loja não tem lista de espera");
                }
                break;
            case 3:
                UI.printTop10(e.getTop10Util().stream().map(Utilizador::getNome).collect(Collectors.toList()));
                break;
            case 4:
                UI.printTop10(e.getTop10Trans().stream().map(Estafeta::getNome).collect(Collectors.toList()));

                break;
            default:
                break;
        }
    }
}