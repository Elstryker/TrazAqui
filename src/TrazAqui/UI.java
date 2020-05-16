package TrazAqui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("3 - Classificar voluntario/transportadora: ");
    }


    public static void printMenuVoluntario() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Escolher encomenda para ir buscar");
        System.out.println("3 - Transportar encomenda");
        System.out.println("Opcao: ");

    }

    public static void printMenuTransportadora() {
        System.out.println("1 - Indicar que esta disponivel");
        System.out.println("2 - Determinar preço da encomenda");
        System.out.println("3 - Transportar encomenda");
        System.out.println("Opcao: ");
    }

    public static void printMenuLoja() {
        System.out.println("1 - Encomenda disponivel para ser entregue");
        System.out.println("2 - Indicar tamanho da fila");
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


