package TrazAqui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UI {

    public static void print(String s) {
        System.out.println(s);
    }

    public static void printMenuInicial(){
        System.out.println("----------------MENU-PRINCIPAL----------------");
        System.out.println("0 - Sair");
        System.out.println("1 - Login");
        System.out.println("2 - Criar uma nova conta");
        System.out.print("Opcao: ");
    }

    public static void printInsiraEmail(){
        System.out.print("Email: ");
    }

    public static void printIncorrectInput() {
        System.out.print("Opcão inválida, tente novamente: ");
    }

    public static void printInsiraPassword(){
        System.out.print("Password: ");
    }

    public static void printDetalhesConta(){
        System.out.print("Informações da conta: ");
    }

    public static void printMenuUtilizador() {
        System.out.println("----------------MENU-UTILIZADOR----------------");
        System.out.println("1 - Efetuar uma encomenda ");
        System.out.println("2 - Ver histórico de encomendas ");
        System.out.println("3 - Aceitar pedidos ");
        System.out.println("4 - Top 10 utilizadores");
        System.out.println("5 - Terminar sessão");
        System.out.println("0 - Sair");
    }

    public static void printFazerDescricao() {
        System.out.print("Faça a descrição do produto: ");
    }

    public static void printIndicarPreco() {
        System.out.print("Indique o preço: ");
    }

    public static void printIndicarQuant() {
        System.out.print("Indique a quantidade: ");
    }

    public static void printIndicarFragil() {
        System.out.print("Indique se o produto é frágil ou não (escreva true se sim, false se não): ");
    }

    public static void printIndiqueCodProd() {
        System.out.print("Indique o código do produto: ");
    }


    public static void printDesejaMaisProd() {
        System.out.print("Deseja encomendar mais produtos? (escreva true se sim, false se não)");
    }

    public static void printHistoricoEncomendas(Map<String,Encomenda> enc, int opcao,Utilizador u, LocalDateTime inicio,LocalDateTime fim) {
        if(opcao==1 || opcao ==2 || opcao == 3 || opcao ==4) {
            if(enc.size()>0) {
                int i=0;
                if (opcao == 1) {
                    for (Map.Entry<String, Encomenda> map : enc.entrySet()) {
                        if (map.getValue().getEstafeta().contains("t")) {
                            System.out.println(map.getKey() + ":" + map.getValue());
                            i++;
                        }
                    }
                    if(i==0) UI.print("Não existe nenhuma encomenda para apresentar");
                }
                if (opcao == 2) {
                    for (Map.Entry<String, Encomenda> map : enc.entrySet()) {
                        if (map.getValue().getEstafeta().contains("v")) {
                            System.out.println(map.getKey() + ":" + map.getValue());
                            i++;
                        }
                    }
                    if(i==0) UI.print("Não existe nenhuma encomenda para apresentar");
                }
                if (opcao == 3) {
                    UI.printEncomendas(u.procuraPor(inicio, fim));
                }
                if(opcao == 4){
                    for (Map.Entry<String, Encomenda> map : enc.entrySet()) {
                            System.out.println(map.getKey() + ":" + map.getValue());
                            i++;
                        }
                    if(i==0) UI.print("Não existe nenhuma encomenda para apresentar");
                }
            }
            else UI.print("Não existe nenhuma encomenda para apresentar");
        }
        else UI.print("Opcão inválida");
    }


    public static void printMenuVoluntario() {
        System.out.println("----------------MENU-VOLUNTARIO----------------");
        System.out.println("1 - Mudar disponibilidade");
        System.out.println("2 - Escolher encomenda para ir buscar");
        System.out.println("3 - Alterar o raio de ação");
        System.out.println("4 - Terminar sessao");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    public static void printTrabalhadores(HashMap<String, Estafeta> trab) {
        System.out.println("Trabalhadores");
        System.out.println(trab.toString());
    }
    
    public static void printMenuTransportadora() {
        System.out.println("----------------MENU-TRANSPORTADORA----------------");
        System.out.println("1 - Mudar disponibilidade");
        System.out.println("2 - Determinar preço da encomenda");
        System.out.println("3 - Transportar encomenda");
        System.out.println("4 - Top 10 utilizadores");
        System.out.println("5 - Top 10 Transportadoras");
        System.out.println("6 - Indicar o total faturado");
        System.out.println("7 - Alterar raio de ação");
        System.out.println("8 - Alterar preço por kilometro");
        System.out.println("9 - Terminar sessao");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }


    public static void printMenuLoja() {
        System.out.println("----------------MENU-LOJA----------------");
        System.out.println("0) Sair");
        System.out.println("1) Encomendas disponiveis para serem entregues");
        System.out.println("2) Indicar tamanho da fila");
        System.out.println("3) Top 10 utilizadores");
        System.out.println("4) Top 10 Transportadoras");
        System.out.println("5) Terminar sessão");
    }

    public static void printTop10(List<String> r) {
        System.out.println("Top 10:");
        for(String nome: r)
            System.out.println(nome);
    }

    public static void printEncomendas(List<Encomenda> enc) {
        StringBuilder sb;
        if(enc.size() != 0) {
            for (Encomenda e : enc) {
                sb = new StringBuilder();
                sb.append("Código: ").append(e.getCod()).append(" Conteúdo: ").append(e.getProdutos());
                System.out.println(sb.toString());
            }
        }
        else System.out.println("Sem encomendas a apresentar!");
    }

    public static void printUtilizadores(List<Utilizador> l) {
        l.forEach(t -> System.out.println(t.toString()));
    }

    public static void printTransportadoras(List<Transportadora> l) {
        l.forEach(t -> System.out.println(t.toString()));
    }

    public static void printTotFat(double f) {
        StringBuilder sc = new StringBuilder();
        sc.append("Total faturado: ").append(f);
        System.out.println(sc.toString());
    }

    public static void printPreco(double p) {
        System.out.println(p);
    }

    public static void goodbye() {
        System.out.println("A sair..\nObrigado por usar a nossa aplicação! :)");
    }


    public static void printLojas(Map<String,Loja> lojas){
        for(Map.Entry<String,Loja> map: lojas.entrySet()){
            System.out.println("Código da loja: " + map.getKey());
            System.out.println("Nome da loja: " + map.getValue().getNome() + "  Localização: " + map.getValue().getLocalizacao());
        }
    }

    public static void printPedidosEncomenda(List<Encomenda> e){
        for(Encomenda enc: e){
            System.out.println(enc);
        }
    }

    public static void printTipoIncorreto(){
        System.out.println("Input incorreto");
    }

    public static void printDesejaTransVolTempo(){
        System.out.println("1 - Ver encomendas transportadas por transportadoras");
        System.out.println("2 - Ver encomendas transportadas por voluntários");
        System.out.println("3 - Ver encomendas transportadas num intervalo de tempo");
        System.out.println("4 - Ver todas as encomendas transportadas");
    }
    public static void printInsiraCod(){
        System.out.println("Código: ");
    }

    public static void printTipoRegisto(){
        System.out.println("Regista-se como Utilizador, Loja, LojaFilaEspera, Transportadora ou Voluntário?: ");
    }

    public static void printInsiraNome(){
        System.out.println("Nome: ");
    }

    public static void printInsiraLatitude(){
        System.out.println("Latitude: ");
    }

    public static void printInsiraLongitude(){
        System.out.println("Longitude: ");
    }

    public static void printFormatoInvalido(){
        System.out.println("Formato inválido!");
    }

    public static void printDataInicial(){
        System.out.println("Insira a data inicial da procura ( formato yyyy-mm-dd HH:mm)");
    }

    public static void printDataFinal(){
        System.out.println("Insira a data final da procura ( formato yyyy-mm-dd HH:mm)");
    }

    public static void printTamanhoFilaEspera(int tam){
        System.out.println("Tamanho da lista de espera: " + tam);
    }

    public static void printNtemFilaEspera(){
        System.out.println("Esta loja não tem fila de espera.");
    }

    public static void printEncomendaInex(){
        System.out.println("Encomenda inexistente.");
    }

    public static void printSelectRaio() {
        System.out.print("Selecione o raio: ");
    }

    public static void printSelectPrecoKM() {
        System.out.print("Selecione o preço por km: ");
    }

    public static void printEncomendaEmTrans(){
        System.out.println(" Encomenda em transporte.");
    }

    public static void printInsiraCodEnc(){
        System.out.println("Código da encomenda: ");
    }

    public static void print0NEncomendas(){
        System.out.println("Pressione enter caso não existam encomendas.");
    }

    public static void printMudeDisp(){
        System.out.println("Altere a sua disponibilidade.");
    }

    public static void printDisponivel(){
        System.out.println(" Disponível");
    }

    public static void printIndisponivel(){
        System.out.println(" -> Indisponível");
    }
    public static void printNoMedica(){
        System.out.println("Não pode transportar medicamentos!");
    }

    public static void print0encParaAceitar(){
        System.out.println("Não existem encomendas para serem aceites.");
    }

    public static void printInsiraClass(){
        System.out.println("Indique a classificação que deseja dar: ");
    }

    public static void printCodEncAceitar(){
        System.out.println("Indique o código da encomenda cuja encomenda deseja aceitar:");
    }

    public static void printInsiraCodLoja(){
        System.out.println("Insira o código da loja: ");
    }
    public static void printInsiraPeso(){
        System.out.println("Indique o peso: ");
    }

    public static void printTransMedica(){
        System.out.println("Transporta medicamentos(escreva true se sim, false se não)");
    }
}


