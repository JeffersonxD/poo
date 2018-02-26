/**
A classe Agenda1 representa uma simples agenda de telefone implemementada tendo
como base um vetor que armazena inst�ncias da classe Contato, que � uma classe
interna da interface Agenda, implementada por esta classe.
Inst�ncias desta classe permitem a reliza��o das opera��es b�sicas de uma agenda.
Nela encontramos, por exemplo, m�todos para incluir, exclir e listar
contatos.
@author Andr� Lu�s dos Reis Gomes de Carvalho.
@since 2000.
*/
public class Agenda1 implements Agenda
{
    /**
    Atributo que representa, em qualquer instante, a quantidade de contatos
    armazenados em uma agenda.
    */
    protected int qtdContatos=0;

    /**
    Atributo que representa o conjunto dos contatos armazenados em uma
    agenda.
    */
    protected Agenda.Contato[] contato;

    /**
    Localiza um nome dado na agenda.
    Procura um contato na agenda, pelo m�todo da busca bin�ria,
    resultando um n�mero inteiro negativo, quando o nome
    procurado n�o tiver sido encontrado, ou um numero inteiro
    positivo, quando o nome procurado tiver sido encontrado.
    @param nom o nome a ser procurado.
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
            fim        = this.qtdContatos - 1,
            meio       = 0,
            comparacao;

        while (inicio <= fim)
        {
            meio       = (inicio + fim) / 2;
            comparacao = nom.compareTo (this.contato[meio].getNome());

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
    Constroi uma nova inst�ncia da classe Agenda1.
    Para tanto, deve ser fornecido um inteiro que ser� utilizado
    como capacidade da inst�ncia rec�m criada.
    @param cap o n�mero inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public Agenda1 (int cap) throws Exception
    {
        if (cap <= 0)
            throw new Exception ("Capacidade invalida");

        this.contato = new Agenda.Contato [cap];
    }

    /**
    Inclui um novo contato em uma agenda.
    Acrescenta um contato com o nome e telefone fornecidos na inst�ncia
    � qual este m�todo for aplicado.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                      n�o parecer ser um nome correto, ou se n�o for fornecido
                      um telefone, ou se o telefone fornecido n�o parecer ser
                      um telefone correto, ou se a agenda estiver cheia, ou
                      ainda se o nome fornecido j� estiver cadastrado.
    */
    public void registreContato (String nom, String tel) throws Exception
    {
        if (this.qtdContatos == this.contato.length)
            throw new Exception ("Agenda cheia");

        Agenda.Contato.valideNome     (nom);
        Agenda.Contato.valideTelefone (tel);

        int posicao = this.ondeEsta (nom);

        if (posicao > 0)
            throw new Exception ("Nome ja registrado");

        posicao = (-posicao)-1;

        for (int pos=this.qtdContatos-1; pos>=posicao; pos--)
            this.contato [pos+1] = this.contato [pos];

        this.contato [posicao] = new Agenda.Contato (nom, tel);

        this.qtdContatos++;
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
        if (this.qtdContatos == 0)
            throw new Exception ("Agenda vazia");

        Agenda.Contato.valideNome (nom);

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        int pos;

        for (pos = posicao; pos < this.qtdContatos - 1; pos++)
            this.contato [pos] = this.contato [pos+1];

        this.contato [pos] = null;

        this.qtdContatos--;
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

        for (int pos=0; pos<this.qtdContatos; pos++)
            ret += this.contato [pos] + "\n";

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

        Agenda1 agenda = (Agenda1)obj;

        if (this.qtdContatos != agenda.qtdContatos)
            return false;

        for (int pos=0; pos<this.qtdContatos; pos++)
            if (!this.contato[pos].equals(agenda.contato[pos]))
                return false;

        return true;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de uma agenda.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da agenda representada pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento da agenda chamante do m�todo.
    */
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret + this.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
            ret = 13*ret + this.contato[pos].hashCode();

        return ret;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Agenda1 dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda1 para ser
    utilizada como modelo para a constru��o da nova inst�ncia criada.
    @param modelo a inst�ncia da classe Agenda1 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Agenda1 (Agenda1 modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.contato = new Agenda.Contato [modelo.contato.length];

        this.qtdContatos = modelo.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
            this.contato[pos] = (Agenda.Contato)modelo.contato[pos].clone();
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
        Agenda1 copia=null;

        try
        {
            copia = new Agenda1 (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}