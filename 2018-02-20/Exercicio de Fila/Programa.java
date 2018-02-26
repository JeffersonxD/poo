public class Programa
{
    public static void main (String[] args)
    {
        Fila p;
        p = new Fila();

        p.guarde("PUCC");
        p.guarde("FAS");
        p.guarde("SI");
        p.guarde("POO");

        System.out.println (p.getPrimeiro());
        p.jogueForaPrimeiro ();
        System.out.println (p.getPrimeiro());
        p.jogueForaPrimeiro ();
        System.out.println (p.getPrimeiro());
        p.jogueForaPrimeiro ();
        System.out.println (p.getPrimeiro());
    }
}