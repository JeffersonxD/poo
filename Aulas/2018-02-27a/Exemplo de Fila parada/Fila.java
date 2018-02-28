public class Fila
{
    String[] item   =  new String [1000000];
    int      inicio =  0;
    int      fim    = -1;
    int      qtd    =  0;

    void guarde (String x)
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        if (qtd==item.length)
            throw new Exception ("Nao cabe mais nada");

        fim++;
        if (fim==item.length)
            fim = 0;
        item[fim] = x;
        qtd++;
    }

    String getUmItem ()
    {
        if (qtd==0)
            throw new Exception ("Nada guardado");

        return item[inicio];
    }

    void jogueForaUmItem ()
    {
        if (qtd==0)
            throw new Exception ("Nada guardado");

        item[inicio] = null;
        inicio++;
        if (inicio==item.length)
            inicio = 0;
        qtd--;
    }
}








