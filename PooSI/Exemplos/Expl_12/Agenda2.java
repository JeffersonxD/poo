import java.util.Vector;

/**
A classe Agenda2 representa uma simples agenda de telefone implemementada tendo
como base um Vector que armazena inst�ncias da classe Contato, que � uma classe
interna da interface Agenda, implementada por esta classe.
Inst�ncias desta classe permitem a reliza��o das opera��es b�sicas de uma agenda.
Nela encontramos, por exemplo, m�todos para incluir, excluir e listar
contatos.
@author Andr� Lu�s dos Reis Gomes de Carvalho.
@since 2000.
*/
class Agenda2 implements Agenda
{
    /**
    Atributo que armazena os contatos destinados � agenda.
    */
    protected Vector<Agenda.Contato> contatos;

    /**
    Localiza um nome dado na agenda.
    Procura um contato na agenda, pelo m�todo da busca bin�ria,
    resultando um n�mero inteiro negativo, quando o nome
    procurado n�o tiver sido encontrado, ou um numero inteiro
    positivo, quando o nome procurado tiver sido encontrado.
    @param nom o nome a ser procurado
    @return um inteiro ao qual se deve dar a seguinte interpreta��o:
            <ol>
             <li>
              Um inteiro i negativo � retornado quando o nome
              procurado n�o foi encontrado, mas, caso ele fosse ser
              inserido, para manter os nomes da agenda em ordem
              alfab�tica, o local apropriado para a inser��o seria
              a posi��o -i-1.
             </li>
             <li>
              Um inteiro i positivo � retornado quando o nome
              procurado foi encontrado na posi��o i-1.
             </li>
            </ol>
    */
    protected int ondeEsta (String nom)
    {
        int inicio     = 0,
            fim        = this.contatos.size() - 1,
            meio       = 0,
            comparacao = -1;

        while (inicio <= fim)
        {
            meio       = (inicio + fim) / 2;
            comparacao = nom.compareTo (this.contatos.elementAt(meio).getNome());

            if (comparacao == 0)
                return meio+1;
            else
                if (comparacao < 0)
                    fim = meio-1;
                else
                    inicio = meio+1;
        }

        return -(inicio+1);
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Agenda dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda para ser
    utilizada como modelo para a constru��o da nova inst�ncia criada.
    @param modelo a inst�ncia da classe Agenda2 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */

    public Agenda2 ()
    {
        contatos = new Vector<Agenda.Contato> ();
    }

    /**
    Inclui um novo contato em uma agenda.
    Acrescenta um contato com o nome e telefone fornecidos na inst�ncia
    � qual este m�todo for aplicado.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                      n�o parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda n�o contiver um
                      contato com o nome fornecido.
    */
    public void registreContato (String nom, String tel) throws Exception
    {
        Agenda.Contato.valideNome     (nom);
        Agenda.Contato.valideTelefone (tel);

        int posicao = this.ondeEsta (nom);

        if (posicao > 0)
            throw new Exception ("Nome ja registrado");

        posicao = (-posicao)-1;

        this.contatos.insertElementAt (new  Agenda.Contato(nom,tel), posicao);
    }

    /**
    Remove um contato, dado seu nome.
    Exclui da inst�ncia � qual este m�todo for aplicado o  contato
    cujo nome foi fornecido.
    @param nom o nome do contato a ser descartado.
    @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                      n�o parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda n�o contiver um
                      contato com o nome fornecido.
    */
    public void descarteContato (String nom) throws Exception
    {
        if (this.contatos.size() == 0)
            throw new Exception ("Agenda vazia");

        Agenda.Contato.valideNome (nom);

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        this.contatos.removeElementAt (posicao);
    }

    /**
    Gera uma representa��o textual de todo conte�do da agenda.
    Produz e resulta um String com todos os nomes e telefones contidos
    na agenda.
    @return um String contendo todo o conte�do da agenda.
    */
    public String toString ()
    {
        String ret = "";

        for (Agenda.Contato c : this.contatos)
            ret += c.getNome() + "(" + c.getTelefone() + ")\n";

        return ret;
    }

    /**
    Verifica a igualdade entre duas agendas.
    Verifica se o Object fornecido como par�metro representa uma
    agenda igual �quela representada pela inst�ncia � qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem agendas iguais, ou false, caso contr�rio.
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Agenda2 agenda = (Agenda2)obj;

        return this.contatos.equals(agenda.contatos);
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de uma agenda.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da agenda representada pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento da agenda chamante do m�todo.
    */
    public int hashCode ()
    {
        return this.contatos.hashCode();
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Agenda dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda para ser
    utilizada como modelo para a constru��o da nova inst�ncia criada.
    @param modelo a inst�ncia da classe Agenda2 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Agenda2 (Agenda2 modelo)
                    throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        /*
        N�o fosse o fato do m�todo clone da classe Vector fazer shall copy
        (em vez de deep copy), poderiamos substituir tudo que segue este
        coment�rio, simplesmente, por:

        this.contatos = (Vector<Agenda.Contato>)modelo.contatos.clone();
        */

        this.contatos = new Vector<Agenda.Contato> (modelo.contatos.capacity());

        for (Agenda.Contato c : modelo.contatos)
            this.contatos.add ((Agenda.Contato)c.clone());
    }

    /**
    Clona uma agenda.
    Produz e resulta uma c�pia da agenda representada pela inst�ncia
    � qual o m�todo for aplicado.
    @return a c�pia da agenda representada pela inst�ncia � qual
            o m�todo for aplicado.
    */
    public Object clone ()
    {
        Agenda2 copia=null;

        try
        {
            copia = new Agenda2 (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}