package TrazAqui;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                case "LojaFilaEspera":
                    while (this.exec) {
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

        System.out.print("Regista-se como Utilizador, Loja, LojaFilaEspera, Transportadora ou Voluntario?: ");
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
            UI.printMenuUtilizador();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    Encomenda enc = new Encomenda();
                    double peso,preco, quantidade;
                    boolean fragil, conti = true,med;
                    String descEnc,codEnc,loja,cod, descricao;
                    while (conti) {
                        enc.setData(LocalDateTime.now());
                        UI.print("Adicione um codigo a encomenda");
                        codEnc = sc.nextLine();
                        enc.setCod(codEnc);
                        UI.print("Transporta medicamentos");
                        med = sc.nextBoolean();
                        enc.setMedicamentos(med);
                        UI.print("Indique peso");
                        peso=sc.nextDouble();
                        enc.setPeso(peso);
                        enc.setUtilizador(e.getLogin().getCod());
                        sc.nextLine();
                        UI.print("Faca descricao da encomenda");
                        descEnc = sc.nextLine();
                        enc.setDescricao(descEnc);
                        System.out.println(e.getLojas());
                        UI.print("Insira o codigo da loja");
                        loja = sc.nextLine();
                        enc.setLoja(loja);
                        UI.printFazerDescricao();
                        descricao = sc.nextLine();
                        sc.nextLine();
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
                        e.addEncomendaLoja(loja,enc);
                        e.addEncomendaUtilizador(e.getLogin().getCod(),enc);
                    }
                    break;
                case 2:
                    //UI.printHistoricoEncomendas();
                    String codigoUtilizador = e.getLogin().getCod();
                    Map<String,Encomenda> lstEnc = e.getUtilizador(codigoUtilizador).getEncomendasConcluidas();
                    UI.printHistoricoEncomendas(lstEnc);
                    if(lstEnc.size() > 0) {
                        UI.print("Insira a data inicial da procura ( formato yyyy-mm-dd HH:mm)");
                        String inicio = sc.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dataInicial = LocalDateTime.parse(inicio, formatter);
                        UI.print("Insira a data final da procura ( formato yyyy-mm-dd HH:mm)");
                        String fim = sc.nextLine();
                        LocalDateTime dataFinal = LocalDateTime.parse(fim, formatter);
                        UI.printEncomendas(e.getUtilizador(codigoUtilizador).procuraPor(dataInicial, dataFinal));
                    }
                    break;
                case 3:
                    int clas = 0,i=0;
                    for(Map.Entry<String,Estafeta> a : e.getTrabalhadores().entrySet()){
                        if(a.getValue().getPedidosEncomenda().size()>0) {
                            System.out.println(a.getValue().getPedidosEncomenda());
                            i++;
                        }
                    }
                    if(i>0) {
                        UI.print("Indique o codigo do estafeta cuja encomenda deseja aceitar:");
                        String codEsta = sc.nextLine();
                        UI.print("Indique o indice da encomenda que deseja aceitar: ");
                        int index = sc.nextInt();
                        Encomenda encomenda = e.getEstafeta(codEsta).getPedidosEncomenda().get(index);
                        e.getEstafeta(codEsta).addEncomendaEntregue(encomenda);
                        UI.print("Indique a classificação que deseja dar: ");
                        clas = sc.nextInt();
                        e.getEstafeta(codEsta).classifica(clas);
                    }
                    else UI.print("Nao existem encomendas para serem aceites.");
                    break;
                case 0:
                    stopExec();
                    try {
                        f.saveObjectStream(e);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    bool = false ;
                    break;
                case 4:
                    UI.printUtilizadores(this.e.getTop10Util());
                default:
                    UI.printIncorrectInput();
                    break;
            }
        return bool;
    }

    public void menuVoluntario() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuVoluntario();
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
                sc.nextLine();
                String codEncomenda = sc.nextLine();
                try {
                    Encomenda e = this.e.removeEncomendaLoja(codEncomenda);
                    this.e.addEncomendaTransportar(cod,e);
                } catch (Exception e) {
                    UI.print("Encomenda inexistente!");
                }
                break;
            default:
                break;
        }
    }

    public void menuTransportadora() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuTransportadora();
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
                sc.nextLine();
                String codEncomenda = sc.nextLine();
                double p = this.e.precoDaEncomenda(codEncomenda,cod);
                UI.printPreco(p);
                break;
            case 3:
                UI.print("Codigo da encomenda: ");
                sc.nextLine();
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