package TrazAqui;

public class UI {

    public void print(String s) {
        System.out.println(s);
    }

    public void menuInicial(){
        System.out.println("1 - Login");
        System.out.println("2 - Criar uma nova conta");
    }

    public void insiraEmail(){
        System.out.print("Email: ");
    }

    public void insiraPassword(){
        System.out.print("Password: ");
    }

    public void detalhesConta(){
        System.out.println("Informaçoes da conta: ");
    }

    public void tipoDeConta(){
        System.out.println("Registar-se como loja");
        System.out.println("Registar-se como transportadora");
        System.out.println("Registar-se como voluntario");
        System.out.println("Registar-se como utilizador");
    }




}


