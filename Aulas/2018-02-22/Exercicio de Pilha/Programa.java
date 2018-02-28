public class Programa
{
    public static void main (String[] args)
    {
        Pilha p;
        p = new Pilha ();

        p.guarde("PUCC");
        p.guarde("FAS");
        p.guarde("SI");
        p.guarde("POO");

        System.out.println (p.getUmItem());
        p.jogueForaUmItem  ();
        System.out.println (p.getUmItem());
        p.jogueForaUmItem  ();
        System.out.println (p.getUmItem());
        p.jogueForaUmItem  ();
        System.out.println (p.getUmItem());
        p.jogueForaUmItem  ();
    }
}