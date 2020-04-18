package TrazAqui;

public class Main {

    public static void main(String[] args) {
        GPS a = new GPS(1,2);
        Loja l = new Loja(123,"Zara",a);
        System.out.println(l.hashCode());
    }
}
