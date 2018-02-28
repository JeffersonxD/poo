public class Fila
{
    String[] item   = new String [10];
    int      ultimo = -1;

    void guarde (String x)
    {
        ultimo++;
        item[ultimo] = x;
    }

    String getUmItem ()
    {
        return item[0];
    }

    void jogueForaUmItem ()
    {
        for (int i=0; i<ultimo; i++)
            item[i] = item[i+1];

        item[ultimo] = null;
        ultimo--;
    }
}






