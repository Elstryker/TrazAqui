package TrazAqui;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UI {

    public static void print(String s) {
        System.out.println(s);
    }

    public static void printMenuInicial(){
        System.out.println("0 - Sair");
        System.out.println("1 - Login");
        System.out.println("2 - Criar uma nova conta");
        System.out.print("Opcao: ");
    }

    public static void printInsiraEmail(){
        System.out.print("Email: ");
    }

    public static void printIncorrectInput() {
        System.out.print("Opcao inválida, tente novamente: ");
    }

    public static void printInsiraPassword(){
        System.out.print("Password: ");
    }

    public static void printDetalhesConta(){
        System.out.print("Informaçoes da conta: ");
    }

    public static void printMenuUtilizador() {
        System.out.println("1 - Efetuar uma encomenda: ");
        System.out.println("2 - Ver historico de encomendas: ");
        System.out.println("3 - Aceitar pedidos: ");
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
        System.out.print("Indique se o produto é frágil? (true se sim, false se não): ");
    }

    public static void printIndiqueCodProd() {
        System.out.print("Indique o código do produto: ");
    }


    public static void printDesejaMaisProd() {
        System.out.print("Deseja encomendar mais produtos? (true se sim, false se não): ");
    }

    public static void printHistoricoEncomendas(Map<String,Encomenda> enc) {
        for(Map.Entry<String,Encomenda> map : enc.entrySet()){
            System.out.println(map.getKey() + ":" + map.getValue());
        }
    }

    public static void printMenuVoluntario() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Escolher encomenda para ir buscar");
        System.out.println("3 - Terminar sessao");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    public static void printTrabalhadores(HashMap<String, Estafeta> trab) {
        System.out.println("Trabalhadores");
        System.out.println(trab.toString());
    }
    
    public static void printMenuTransportadora() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Determinar preço da encomenda");
        System.out.println("3 - Transportar encomenda");
        System.out.println("4 - Top 10 utilizadores");
        System.out.println("5 - Top 10 Transportadoras");
        System.out.println("6 - Indicar o total faturado");
        System.out.println("7 - Terminar sessao");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }


    public static void printMenuLoja() {
        System.out.println("0) Sair");
        System.out.println("1) Encomendas disponiveis para serem entregues");
        System.out.println("2) Indicar tamanho da fila");
        System.out.println("3) Top 10 utilizadores");
        System.out.println("4) Top 10 Transportadoras");
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
                sb.append("Codigo: ").append(e.getCod()).append(" Conteudo: ").append(e.getProdutos());
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
            System.out.println("Codigo da loja: " + map.getKey());
            System.out.println("Nome da loja: " + map.getValue().getNome() + "  Localizaçao: " + map.getValue().getLocalizacao());
        }
    }

    public static void printPedidosEncomenda(List<Encomenda> e){
        for(Encomenda enc: e){
            System.out.println(enc);
        }

    }
}


