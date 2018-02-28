public class Programa
{
    public static void main (String[] args)
    {
        Fila f;
        f = new Fila ();

        f.guarde ("PUCC");
        f.guarde ("FAS");
        f.guarde ("ESw");
        f.guarde ("LPOO");

        System.out.println (f.getUmItem());
        f.jogueForaUmItem  ();
        System.out.println (f.getUmItem());
        f.jogueForaUmItem  ();
        System.out.println (f.getUmItem());
        f.jogueForaUmItem  ();
        System.out.println (f.getUmItem());
        f.jogueForaUmItem  ();
    }
}