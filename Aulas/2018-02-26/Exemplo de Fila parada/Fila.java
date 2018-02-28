public class Fila
{
    String[] item   =  new String [1000000];
    int      inicio =  0;
    int      fim    = -1;
    int      qtd    =  0;

    void guarde (String x)
    {
        fim++;
        if (fim==item.length)
            fim = 0;
        item[fim] = x;
        qtd++;
    }

    String getUmItem ()
    {
        return item[inicio];
    }

    void jogueForaUmItem ()
    {
        item[inicio] = null;
        inicio++;
        if (inicio==item.length)
            inicio = 0;
        qtd--;
    }
}








