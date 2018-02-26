import java.lang.reflect.*;

/**
A classe Deposito representa um reposit�rio gen�rico de �tens.
Inst�ncias desta classe permitem a reliza��o das opera��es b�sicas de uma reposit�rio.
Nela encontramos, por exemplo, m�todos para guardar �tens, jogar fora �tens guardados,
verificar a presen�a de �tens no reposit�rio e listar �tens.
@author Andr� Lu�s dos Reis Gomes de Carvalho.
@since 2016.
*/
public class Deposito <X> implements Cloneable // X � for�osamente uma classe
{
    /**
    Mant�m armazenados os �tens armazenados no deposito.
    */
    protected Object[] elem;

    /**
    Expressa, em cada instante, a posi��o na qual se encontra ammazenado o �ltimo �tem
    do deposito.
    */
    protected int ultimo;

    /**
    Constroi uma nova inst�ncia da classe gen�rica Deposito.
    Para tanto, deve ser fornecido um inteiro que ser� utilizado
    como capacidade da inst�ncia rec�m criada.
    @param tam n�mero inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public Deposito (int tam) throws Exception
    {
        if (tam<=0)
            throw new Exception ("Tamanho invalido");

        elem   = new Object [tam];
        ultimo = -1;
    }

    /**
    Armazena um novo �tem em um dep�sito.
    Acrescenta um novo �tem na inst�ncia � qual este m�todo for aplicado.
    @param i o �tem a ser guardado.
    @throws Exception se o deposito estiver cheio.
    */
    public void guarde (X i) throws Exception
    {
        if (ultimo==elem.length-1)
            throw new Exception ("Deposito cheio");

        ultimo++;

        if (i instanceof Cloneable)
        {
            Class classe            = i.getClass();
            Class<?>[] parmsFormais = null; // = null � desnecess�rio
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            elem[ultimo]            = (X)metodo.invoke(i,parmsReais);

          //elem[ultimo] = (X)i.clone();
        }
        else
            elem[ultimo] = i;
    }

    /**
    Verifica se um certo �tem se encontra armazenado.
    Resulta um valor l�gico, verdadeiro ou falso, conforme o �tem fornecido
    possa ou n�o ser encontrado no dep�sito.
    @return a possibilidade de encontrar o �tem dado.
    @throws Exception se n�o for fornecido o �tem a ser procurado.
    */
    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("�tem a pesquisar n�o fornecido");

        for (int pos=0; pos<=ultimo; pos++)
            if (elem[pos].equals(i))
                return true;

        return false;
    }

    /**
    Recupera um �tem armazenado em uma posi��o fornecida.
    Recupera um �tem, dado o n�mero de ordem dele no dep�sito.
    @return o �tem recuperado.
    @throws Exception o n�mero de ordem fornecido n�o se referir a uma posi��o v�lida
                      no dep�sito.
    */
    public X getElem (int pos) throws Exception
    {
        if (pos<0 || pos>this.ultimo)
            throw new Exception ("Posicao invalida");

        if (this.elem[pos] instanceof Cloneable)
        {
            Class classe            = this.elem[pos].getClass();
            Class<?>[] parmsFormais = null; // = null � desnecess�rio
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            return (X)metodo.invoke(this.elem[pos],parmsReais);

          //return (X)this.elem[pos].clone();
        }
        else
            return (X)this.elem[pos];
    }

    /**
    Remove um �tem do dep�sito.
    Exclui da inst�ncia � qual este m�todo for aplicado o �tem fornecido.
    @param nom o �tem a ser jogado fora.
    @throws Exception se n�o for fornecido um �tem a ser descartato.
    */
    public void jogueFora (X i) throws Exception
    {
        int pos;

        if (ultimo==-1)
            throw new Exception ("Deposito vazio");

        for (pos=0; pos<=ultimo; pos++)
            if (elem[pos].equals(i))
                break;

        if (pos==ultimo+1)
            throw new Exception ("Valor inexistente");

        for ( ; pos<ultimo; pos++)
            elem[pos] = elem[pos+1];

        ultimo--;
    }

    /**
    Verifica a igualdade entre dois dep�sitos.
    Verifica se o Object fornecido como par�metro representa um
    dep�sito igual �quele representada pela inst�ncia � qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem dep�sitos iguais, ou false, caso contr�rio.
    */
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

      //if (!(obj instanceof Deposito<X>))
        if (this.getClass() != obj.getClass())
            return false;

        Deposito dep = (Deposito)obj;

        if (this.ultimo!=dep.ultimo)
            return false;

        for (int i=0; i<=this.ultimo; i++)
            if (!this.elem[i].equals(dep.elem[i]))
                return false;

        return true;
    }

    /**
    Gera uma representa��o textual de todo conte�do do dep�sito.
    Produz e resulta um String com todos os �tens contidos no deposito.
    @return um String contendo todo o conte�do do dep�sito.
    */
    public String toString ()
    {
        String ret="{";

        for (int i=0; i<this.ultimo; i++)
            ret += this.elem[i]+",";

        if (this.ultimo>-1)
            ret += this.elem[this.ultimo];

        ret += "}";

        return ret;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de um dep�sito.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) do dep�sito representado pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento do dep�sito chamante do m�todo.
    */
    public int hashCode ()
    {
        final int O_PRIMO_ESCOLHIDO = 13;

        int ret = super.hashCode();

        ret = ret*O_PRIMO_ESCOLHIDO + new Integer(this.ultimo).hashCode(); // como ultimo nao � objeto...

        for (int i=0; i<=this.ultimo; i++)
            ret = ret*O_PRIMO_ESCOLHIDO + this.elem[i].hashCode();

        return ret;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Deposito dada.
    Para tanto, deve ser fornecida uma instancia da classe Dep�sito para ser
    utilizada como modelo para a constru��o da nova inst�ncia criada.
    @param modelo a inst�ncia da classe Dep�sito a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Deposito (Deposito<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.elem = new Object [modelo.elem.length];

        for (int i=0; i<=modelo.ultimo; i++)
            if (modelo.elem[i] instanceof Cloneable)
            {
                Class classe            = modelo.elem[i].getClass();
                Class<?>[] parmsFormais = null; // = null � desnecess�rio
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.elem[i]            = (X)metodo.invoke(modelo.elem[i],parmsReais);

              //this.elem[i] = (x)modelo.elem[i].clone();
            }
            else
                this.elem[i] = modelo.elem[i];

        this.ultimo = modelo.ultimo;
    }

    /**
    Clona um dep�sito.
    Produz e resulta uma c�pia do dep�sito representado pela inst�ncia
    � qual o m�todo for aplicado.
    @return a c�pia do dep�sito representado pela inst�ncia � qual
            o m�todo for aplicado.
    */
    public Object clone ()
    {
        Deposito<X> ret=null;

        try
        {
            ret = new Deposito<X> (this);
        }
        catch (Exception erro)
        {} // tenho certeza que nao vai acontecer

        return ret;
    }
}