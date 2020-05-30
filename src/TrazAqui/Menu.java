package TrazAqui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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

    public void run() throws IOException,LojaInexistenteException {
        Scanner inp = new Scanner(System.in);
        int opcao = -1;
        try {
            e = f.readObjectStream();
        }
        catch (IOException | ClassNotFoundException ex) {
            f.loadFromFile(e);
        }
        while(this.exec) {
            while (e.getLogin() == null && this.exec) {
                boolean f = true;
                UI.printMenuInicial();
                while (f) {
                    try {
                        opcao = inp.nextInt();
                    } catch (InputMismatchException ex) {
                        System.out.println("Opcao inválida!");
                        inp.nextLine();
                    }
                    if (opcao >= 0 && opcao <= 2) f = false;
                }
                switch (opcao) {
                    case 0:
                        stopExec();
                        break;
                    case 1:
                        try {
                            this.loginUtilizador();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InvalidInputException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        try {
                            this.novoRegisto();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InvalidInputException | InputMismatchException | ExistingCodeException l) {
                            System.out.println(l.getMessage());
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + opcao);
                }
            }
            if (this.exec)
                switch (e.getLogin().getClass().getSimpleName()) {
                    case "Utilizador":
                        while (this.exec && e.getLogin() != null) {
                            menuUtilizador();
                        }
                        break;
                    case "Transportadora":
                        while (this.exec && e.getLogin() != null) {
                            menuTransportadora();
                        }
                        break;
                    case "Voluntario":
                        while (this.exec && e.getLogin() != null) {
                            menuVoluntario();
                        }
                        break;
                    case "Loja":
                    case "LojaFilaEspera":
                        while (this.exec && e.getLogin() != null) {
                            menuLoja();
                        }
                    default:
                        break;
                }
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

    public void loginUtilizador() throws IOException , InvalidInputException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (verificaEmail(email)) throw new InvalidInputException("Email invalido!");
        System.out.print("Password: ");
        String password = sc.nextLine();
        this.e.login(email, password, this.f);
    }

    private boolean verificaEmail(String mail) {
        String[] tokens;
        String temp;
        tokens = mail.split("@");
        if (tokens.length != 2 || tokens[0].equals("")) return true;
        temp = tokens[1];
        tokens = temp.split("\\.");
        return (tokens.length != 2 || tokens[0].equals(""));
    }

    public void novoRegisto() throws IOException, InvalidInputException, InputMismatchException, ExistingCodeException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Regista-se como Utilizador, Loja, LojaFilaEspera, Transportadora ou Voluntario?: ");
        String tipo = sc.nextLine();
        if(!tipo.equals("Utilizador") && !tipo.equals("Loja") && !tipo.equals("LojaFilaEspera") && !tipo.equals("Transportadora") && !tipo.equals("Voluntario"))
            throw new InvalidInputException("Tipo de registo inválido!");
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (verificaEmail(email)) {
            throw new InvalidInputException("Email invalido!");
        }
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Código: ");
        String cod = sc.nextLine();
        if(!((tipo.equals("Utilizador") && cod.charAt(0) == 'u')
        || (tipo.equals("Loja") || tipo.equals("LojaFilaEspera") && cod.charAt(0) == 'l')
        || (tipo.equals("Transportadora") && cod.charAt(0) == 't')
        || (tipo.equals("Voluntario") && cod.charAt(0) == 'v')))
            throw new InvalidInputException("Código inválido!");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Latitude: ");
        double lat = sc.nextDouble();
        System.out.print("Longitude: ");
        double longi = sc.nextDouble();
        this.e.registar(email, password, cod, nome, new GPS(lat, longi), this.f, tipo);
    }

    public void menuUtilizador() {
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
                    String codEnc,loja,cod, descricao;
                        enc.setData(LocalDateTime.now());
                        UI.print("Adicione um codigo a encomenda");
                        codEnc = sc.nextLine();
                        enc.setCod(codEnc);
                        UI.print("Transporta medicamentos(escreva true se sim, false se nao)");
                        try{
                            med = sc.nextBoolean();
                            enc.setMedicamentos(med);
                            UI.print("Indique peso");
                            peso = sc.nextDouble();
                            enc.setPeso(peso);
                            enc.setUtilizador(e.getLogin().getCod());
                            UI.printLojas(e.getLojas());
                            UI.print("Insira o codigo da loja");
                            sc.nextLine();
                            loja = sc.nextLine();
                            enc.setLoja(loja);
                            while (conti) {
                                try {
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
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    UI.printTipoIncorreto();
                                }
                            }
                            e.addEncomendaLoja(loja, enc);
                        }
                        catch (InputMismatchException | LojaInexistenteException e){
                            UI.printTipoIncorreto();
                        }
                    break;
                case 2:
                    LocalDateTime dataInicial = LocalDateTime.now();
                    LocalDateTime dataFinal = LocalDateTime.now();
                    String inicio = "",fim = "";
                    String codigoUtilizador = e.getLogin().getCod();
                    Map<String,Encomenda> lstEnc = e.getUtilizador(codigoUtilizador).getEncomendasConcluidas();
                    UI.printDesejaTransVolTempo();
                    int option = sc.nextInt();
                    sc.nextLine();
                    if(option==3) {
                        if (lstEnc.size() > 0) {
                            try {
                                UI.print("Insira a data inicial da procura ( formato yyyy-mm-dd HH:mm)");
                                inicio = sc.nextLine();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                dataInicial = LocalDateTime.parse(inicio, formatter);
                                UI.print("Insira a data final da procura ( formato yyyy-mm-dd HH:mm)");
                                fim = sc.nextLine();
                                dataFinal = LocalDateTime.parse(fim, formatter);
                            } catch (DateTimeParseException ex) {
                                System.out.println("Formato inválido!");
                            }
                        }
                    }
                    Utilizador u = e.getUtilizador(codigoUtilizador);
                    UI.printHistoricoEncomendas(lstEnc,option,u,dataInicial,dataFinal);
                    break;
                case 3:
                    int clas,i=0;
                    for(Map.Entry<String,Estafeta> a : e.getTrabalhadores().entrySet()){
                        if(a.getValue().getPedidosEncomenda().size()>0) {
                            UI.printPedidosEncomenda(a.getValue().getPedidosEncomenda());
                            i++;
                        }
                    }
                    if(i>0) {
                        UI.print("Indique o codigo da encomenda cuja encomenda deseja aceitar:");
                        String codEncomenda = sc.nextLine();
                        String codEsta = "";
                        Encomenda encomenda = null;
                        for(Map.Entry<String,Estafeta> a : e.getTrabalhadores().entrySet()) {
                            for(Encomenda x : a.getValue().getPedidosEncomenda()) {
                                if(x.getUtilizador().equals(e.getLogin().getNome()) && x.getCod().equals(codEncomenda)) {
                                    codEsta = x.getEstafeta();
                                    encomenda = x;
                                    break;
                                }
                            }
                        }
                        assert encomenda != null;
                        e.getEstafeta(codEsta).addEncomendaEntregue(encomenda);
                        e.addEncomendaUtilizador(e.getLogin().getCod(), encomenda);
                        UI.print("Indique a classificação que deseja dar: ");
                        e.getEstafeta(codEsta).classifica(sc.nextInt());
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
                    break;
                case 4:
                    UI.printUtilizadores(this.e.getTop10Util());
                    break;
                case 5:
                    e.logoff();
                    break;
                default:
                    UI.printIncorrectInput();
                    break;
            }
    }

       public void menuVoluntario() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuVoluntario();
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch (opcao) {
            case 0:
                stopExec();
                try {
                    f.saveObjectStream(e);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                exec = false;
                break;
            case 1:
                boolean b = this.e.mudaDisponibilidade(cod);
                if (b) UI.print(" -> Disponivel");
                else UI.print(" -> Indisponivel");
                break;
            case 2:
                if (!this.e.disponivel(cod)) {
                    UI.print("Altere a sua disponibilidade.");
                    break;
                }
                List<Encomenda> enc = this.e.encomendasDisponiveis(cod);
                UI.printEncomendas(enc);
                UI.print("Insira 0 caso nao exista encomendas.");
                UI.print("Codigo da encomenda: ");
                sc.nextLine();
                String codEncomenda = sc.nextLine();
                if (codEncomenda.equals("0")) break;
                if(enc.size()>0) {
                    try {
                        Encomenda e = this.e.removeEncomendaLoja(codEncomenda, cod);
                        if (e==null) {
                            UI.print("Nao pode transportar medicamentos!");
                            break;
                        }
                        this.e.addEncomendaEntregue(cod, e);
                        UI.print(" -> Encomenda em transporte.");
                    } catch (Exception e) {
                        UI.print(" -> Encomenda inexistente!");
                    }
                }
                break;
            case 3:
                this.e.logoff();
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
                stopExec();
                try {
                    f.saveObjectStream(e);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                exec = false;
                break;
            case 1:
                boolean b = this.e.mudaDisponibilidade(cod);
                if (b) UI.print(" -> Disponivel");
                else UI.print(" -> Indisponivel");
                break;
            case 2:
                this.e.getLojas().values().forEach(l -> UI.printEncomendas(l.getPedidos()));
                UI.print("Insira 0 caso nao existam encomendas.");
                UI.print("Codigo de encomenda: ");
                sc.nextLine();
                String codEncomenda = sc.nextLine();
                if (codEncomenda.equals("0")) break;
                try {
                    double p = this.e.precoDaEncomenda(codEncomenda, cod);
                    if (p!=-1) UI.printPreco(p);
                    else UI.print("Encomenda inexistente.");
                } catch (Exception e) {UI.print(" -> Encomenda inexistente.");}
                break;
            case 3:
                if (!this.e.disponivel(cod)) {
                    UI.print("Altere a sua disponibilidade.");
                    break;
                }
                this.e.getLojas().values().forEach(l -> UI.printEncomendas(l.getPedidos()));
                UI.print("Insira 0 caso nao existam encomendas.");
                UI.print("Codigo da encomenda: ");
                sc.nextLine();
                String codEnc = sc.nextLine();
                if (codEnc.equals("0")) break;
                try {
                    Encomenda encomenda = this.e.removeEncomendaLoja(codEnc,cod);
                    encomenda.setEstafeta(cod);
                    this.e.addPedidoDeTransporte(cod,encomenda);
                    UI.print(" -> Encomenda em transporte.");
                } catch (Exception e) {
                    UI.print("Encomenda inexistente.");
                }
                break;
            case 4:
                UI.printTop10(e.getTop10Util().stream().map(Utilizador::getNome).collect(Collectors.toList()));
                break;
            case 5:
                UI.printTop10(e.getTop10Trans().stream().map(Estafeta::getNome).collect(Collectors.toList()));
                break;
            case 6:
                LocalDateTime dataInicial, dataFinal;
                try {
                    UI.print("Insira a data inicial da procura ( formato yyyy-mm-dd HH:mm)");
                    String inicio = sc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    dataInicial = LocalDateTime.parse(inicio, formatter);
                    UI.print("Insira a data final da procura ( formato yyyy-mm-dd HH:mm)");
                    String fim = sc.nextLine();
                    dataFinal = LocalDateTime.parse(fim, formatter);
                }
                catch(DateTimeParseException ex) {
                    System.out.println("Formato inválido!");
                    break;
                }
                Transportadora t = (Transportadora) this.e.getTrabalhadores().get(cod);
                UI.printTotFat(this.e.totalFaturado(t,dataInicial,dataFinal));
                break;
            case 7:
                this.e.logoff();
                break;
            default:
                break;
        }
    }

    public void menuLoja() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        UI.printMenuLoja();
        opcao = sc.nextInt();
        String cod = this.e.getLogin().getCod();
        switch(opcao) {
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
            case 5:
                e.logoff();
                break;
            default:
                break;
        }
    }
}
