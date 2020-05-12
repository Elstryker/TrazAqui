package TrazAqui;

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

    }

    public static void printMenuVoluntario() {

    }

    public static void printMenuTransportadora() {

    }

    public static void printMenuLoja() {

    }
}


