import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BoasVindas
{
    public static void main (String args []) throws IOException
    {
        BufferedReader leitor = new BufferedReader (
                                new InputStreamReader (
                                System.in));

        System.out.println ();

        System.out.print   ("Qual o seu nome? ");
        String nome        = leitor.readLine ();

        System.out.println ("Bem vindo(a) ao estudo de JAVA, " +
                            nome +
                            "!");

        System.out.println ();

        System.out.print   ("Com que nota voce pretende passar? ");

        float nota = Float.parseFloat (leitor.readLine ());
        /*
        ou
        float nota = Float.valueOf (leitor.readLine ()).intValue ();
        ou
        float nota = new Float (leitor.readLine ()).intValue ();
        */

        System.out.println ();

        System.out.println (nome +
                            ", desejo sucesso a voce;");

        System.out.println ("que voce passe com " +
                            nota +
                            " ou mais!");

        System.out.println ();
    }
}