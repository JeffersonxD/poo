public class Programa
{
    public static void main (String[] args)
    {
        Fila f;
        f = new Fila ();

        for (int i=0; i<1000000; i++)
            f.guarde("PUCC"+i);

        System.out.println ("Tudo guardado");

        for (int i=0; i<1000000; i++)
            f.jogueForaUmItem  ();

        System.out.println ("Tudo jogado fora");
    }
}