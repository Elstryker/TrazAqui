package TrazAqui;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        System.out.println("Efetuar uma encomenda: ");
        System.out.println("Ver historico de encomendas: ");
        System.out.println("Classificar voluntario/transportadora: ");
    }




    public static void printMenuVoluntario() {
        System.out.println("Indicar que esta disponivel");
        System.out.println("Escolher encomenda para ir buscar");
        System.out.println("Transportar encomenda");

    }

    public static void printMenuTransportadora() {
        System.out.println("Indicar que esta disponivel");
        System.out.println("Determinar preço da encomenda");
        System.out.println("Transportar encomenda");
    }

    public static void printMenuLoja() {
        System.out.println("Encomenda disponivel para ser entregue");
        System.out.println("Indicar tamanho da fila");

    }
}


