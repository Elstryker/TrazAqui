package TrazAqui;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //  FileIO o = new FileIO();
        Scanner sc = new Scanner(System.in);
        Estado e = new Estado();
        // o.processLine(e);
        Validacao v = new Validacao();
        v.novoUtilizador("ola", "adeus");
        System.out.println("Insira email");
        String email = sc.next();
        if (v.validaEmail(email)) {
            System.out.println("Insira password");
            String pass = sc.next();
            if(v.validaPass(email,pass)) System.out.println("Bem-Vindo "+email);
            else System.out.println("Password incorreta");
        }
        else System.out.println("Utilizador nao existe");
    }
}
