package TrazAqui;

import java.util.List;
import java.util.HashMap;

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
        System.out.print("Indique se o produto é frágil ou não: ");
    }

    public static void printIndiqueCodProd() {
        System.out.print("Indique o código do produto: ");
    }


    public static void printDesejaMaisProd() {
        System.out.print("Deseja encomendar mais produtos? ");
    }

    public static void printHistoricoEncomendas() {
        System.out.println("Histórico de encomendas");
    }

    public static void printMenuVoluntario() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Escolher encomenda para ir buscar");
        System.out.println("3 - Transportar encomenda");
        System.out.println("Opcao: ");

    }

    public static void printTrabalhadores(HashMap<String, Estafeta> trab) {
        System.out.println("Trabalhadores");
        System.out.println(trab.toString());
    }
    public static void printMenuTransportadora() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Determinar preço da encomenda");
        System.out.println("3 - Transportar encomenda");
        System.out.println("Opcao: ");
    }

    public static void printMenuLoja() {
        System.out.println("0) Sair");
        System.out.println("1) Encomendas disponiveis para serem entregues");
        System.out.println("2) Indicar tamanho da fila");
    }

    public static void printEncomendas(List<Encomenda> enc) {
        StringBuilder sb;
        for (Encomenda e : enc) {
            sb = new StringBuilder();
            sb.append("Codigo: ").append(e.getCod()).append(" Conteudo: ").append(e.getDescricao());
            System.out.println(sb.toString());
        }
    }

    public static void printPreco(double p) {
        System.out.println(p);
    }

    public static void goodbye() {
        System.out.println("A sair..\nObrigado por usar a nossa aplicação! :)");
    }
}


