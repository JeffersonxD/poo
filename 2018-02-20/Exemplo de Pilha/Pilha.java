public class Pilha
{
    String[] item = new String [10];
    int      topo = -1;

    void guarde (String x)
    {
        topo++;
        item[topo] = x;
    }

    String getItemDoTopo ()
    {
        return item[topo];
    }

    void jogueForaItemDoTopo ()
    {
      //item[topo]=null;
        topo--;
    }

}





