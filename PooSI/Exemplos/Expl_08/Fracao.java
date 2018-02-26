/**
A classe Fracao representa fra��es, conforme as conhecemos da matem�tica.

Em outras palavras, a classe Fracao representa o conjunto matem�tico dos
n�meros racionais. Nela encontraremos diversos m�todos para operar com fra��es.
@author Andr� Lu�s dos Reis Gomes de Carvalho
@since 2000
*/
public class Fracao implements Comparable<Fracao>, Cloneable
{
    private long numerador, denominador;

    private double valorReal ()
    {
        return (double)numerador /
               (double)denominador;
    }

    private void simplifiqueSe ()
    {
        long menor = Math.min (Math.abs (this.numerador),
                               Math.abs (this.denominador));

        if (this.numerador%this.denominador == 0)
        {
            this.numerador   = this.numerador / this.denominador;
            this.denominador = 1;
        }
        else
            if (this.numerador  %menor == 0 &&
                this.denominador%menor == 0)
            {
                this.numerador   = this.numerador  /menor;
                this.denominador = this.denominador/menor;
            }
            else
                for (long metade=menor/2, i=2; i<=metade; i++)
                    while (this.numerador  %i == 0 &&
                           this.denominador%i == 0)
                    {
                        this.numerador   = this.numerador  /i;
                        this.denominador = this.denominador/i;
                    }
    }

    /**
    Constroi uma nova inst�ncia da classe Fracao.
    Para tanto, devem ser fornecidos dois inteiros que ser�o utilizados
    respectivamente, como numerador e como denominador da inst�ncia rec�m
    criada.
    @param numerador o n�mero inteiro a ser utilizado como numerador
    @param denominador o n�mero inteiro a ser utilizado como denominador
    @throws Exception se o denominador for igual a zero
    */
    public Fracao (long numerador,
                   long denominador)
                   throws Exception
    {
        if (denominador == 0)
            throw new Exception ("Denominador zero");

        if (denominador < 0)
        {
            this.numerador   = -numerador;
            this.denominador = -denominador;
        }
        else
        {
            this.numerador   = numerador;
            this.denominador = denominador;
        }

        this.simplifiqueSe ();
    }

    /**
    Obtem o numerador de uma fra��o.
    Resulta o numerador da inst�ncia � qual este m�todo for aplicado.
    @return o numerador da fra��o chamante do m�todo
    */
    public long getNumerador ()
    {
        return this.numerador;
    }

    /**
    Obtem o denominador de uma fra��o.
    Resulta o denominador da inst�ncia � qual este m�todo for aplicado.
    @return o denominador da fra��o chamante do m�todo
    */
    public long getDenominador ()
    {
        return this.denominador;
    }

    /**
    Ajusta o numerador de uma fra��o.
    Ajusta o numerador da inst�ncia � qual este m�todo for aplicado.
    @param numerador o numerador que a fra��o chamante do m�todo
           deve pasar a ter
    */
    public void setNumerador (long numerador)
    {
        this.numerador = numerador;
        this.simplifiqueSe ();
    }

    /**
    Ajusta o denominador de uma fra��o.
    Ajusta o denominador da inst�ncia � qual este m�todo for aplicado.
    @param denominador o denominador que a fra��o chamante do m�todo
           deve pasar a ter
    @throws Exception se o denominador for igual a zero
    */
    public void setDenominador (long denominador) throws Exception
    {
        if (denominador==0)
            throw new Exception ("Denominador zero");

        this.denominador = denominador;
        this.simplifiqueSe ();
    }

    /**
    Realiza a opera��o de soma para duas fra��es.
    Soma a inst�ncia � qual este m�todo for aplicado com aquela que lhe
    for fornecida como par�metro.
    @param fracao a fra��o que deve ser somada � chamante do m�todo
    @return a fra��o chamante do m�todo somada � fra��o fornecida
    @throws Exception se for fornecido null como par�metro
    */
    public Fracao mais (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em soma");

        long numerador   = this.numerador   * fracao.denominador +
                           this.denominador * fracao.numerador,

             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    /**
    Realiza a opera��o de subtra��o para duas fra��es.
    Subtrai da inst�ncia � qual este m�todo for aplicado aquela que lhe
    for fornecida como par�metro.
    @param fracao a fra��o que deve ser subtraida da chamante do m�todo
    @return a fra��o chamante do m�todo menos a fra��o fornecida
    @throws Exception se for fornecido null como par�metro
    */
    public Fracao menos (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em soma");

        long numerador   = this.numerador   * fracao.denominador -
                           this.denominador * fracao.numerador,

             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador, denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    /**
    Realiza a opera��o de multiplica��o para duas fra��es.
    Multiplica a inst�ncia � qual este m�todo for aplicado por aquela que lhe
    for fornecida como par�metro.
    @param fracao a fra��o que deve ser multiplicada pela chamante do m�todo
    @return a fra��o chamante do m�todo multiplicada pela fra��o fornecida
    @throws Exception se for fornecido null como par�metro
    */
    public Fracao vezes (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em multiplicacao");

        long numerador   = this.numerador   * fracao.numerador,
             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    /**
    Realiza a opera��o de divis�o para duas fra��es.
    Divide a inst�ncia � qual este m�todo for aplicado por aquela que lhe
    for fornecida como par�metro.
    @param fracao a fra��o pela qual deve ser dividida a chamante do m�todo
    @return a fra��o chamante do m�todo dividida pela fra��o fornecida
    @throws Exception se for fornecido null como par�metro
    */
    public Fracao divididoPor (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em divisao");

        if (fracao.numerador == 0)
            throw new Exception ("Divisao por zero");

        long numerador   = this.numerador   * fracao.denominador,
             denominador = this.denominador * fracao.numerador;

        if (denominador < 0)
        {
            numerador   = -numerador;
            denominador = -denominador;
        }

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    /**
    Converte uma fra��o em um String.
    Produz e resulta uma inst�ncia da classe String que representa a
    inst�ncia � qual este m�todo for aplicado.
    @return o String que representa a fra��o chamante do m�todo
    */
    public String toString ()
    {
        if (this.numerador == this.denominador)
            return "1";

        if (this.numerador + this.denominador == 0)
            return "-1";

        if (this.numerador == 0 || this.denominador == 1)
            return "" + this.numerador;

        return this.numerador + "/" + this.denominador;
    }

    /**
    Verifica a igualdade entre duas fra��es.
    Verifica se o Object fornecido como par�metro representa uma
    fra��o numericamente equivalente �quela representada pela inst�ncia
    � qual este m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem fra��es numericamente equivalentes, ou false,
            caso contr�rio
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

      //if (!obj instanceof Fracao)
        if (this.getClass() != obj.getClass())
            return false; // poderiamos fazer de outra forma
                          // caso quisessemos compatibilizar
                          // fracoes com outras classes, e.g.,
                          // com strings; naturalmente, isso
                          // causaria impacto no hashCode

        Fracao fracao = (Fracao)obj;

        if (this.valorReal() != fracao.valorReal())
            return false;

        return true;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de uma fra��o.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da fra��o representada pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento da fra��o chamante do m�todo
    */
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret +
              new Long(numerador).hashCode();

        ret = 13*ret +
              new Long(denominador).hashCode();

        return ret;
    }

    /**
    Compara duas fra��es.
    Compara as fra��es representadas respectivamente pela inst�ncia � qual
    o m�todo for aplicado e pela inst�ncia fornecida como par�metro, resultando
    um n�mero negativo, caso a primeira seja numericamente menor que a segunda,
    zero, caso as duas sejam numericamente iguais, ou
    um n�mero positivo, caso a primeira seja numericamente maior que a segunda.
    @return um n�mero negativo, caso a primeira seja numericamente menor que a
            segunda, zero, caso as duas sejam numericamente iguais, ou um
            n�mero positivo, caso a primeira seja numericamente maior que a
            segunda.

    */
    public int compareTo (Fracao fracao)
    {
        double vrThis   = this  .valorReal(),
               vrFracao = fracao.valorReal();

        if (vrThis < vrFracao)
            return -1;
        else
            if (vrThis == vrFracao)
                return 0;
            else
                return 1;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Fracao dada.
    Para tanto, deve ser fornecida uma instancia da classe Fracao para ser
    utilizada como modelo para a constru��o da nova inst�ncia criada.
    @param modelo a inst�ncia da classe Fracao a ser usada como modelo
    @throws Exception se o modelo for null
    */
    public Fracao (Fracao modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.numerador   = modelo.numerador;
        this.denominador = modelo.denominador;
    }

    /**
    Clona uma fra��o.
    Produz e resulta uma c�pia da fra��o representada pela inst�ncia
    � qual o m�todo for aplicado.
    @return a c�pia da fra��o representada pela inst�ncia � qual
            o m�todo for aplicado
    */
    public Object clone ()
    {
        Fracao copia=null;

        try
        {
            copia = new Fracao (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}