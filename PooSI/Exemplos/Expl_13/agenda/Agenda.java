package agenda;

import java.util.regex.*;

/**
A classe Agenda representa uma simples agenda de telefone implemementada tendo
como base dois vetores que armazenam, respectivamente, os nomes e os telefones
dos contatos da agenda.
Instâncias desta classe permitem a relização das operações básicas de uma agenda.
Nela encontramos, por exemplo, métodos para incluir, excluir e listar
contatos.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public abstract class Agenda implements Cloneable
{
    /**
    Expressão regular que define a forma de um nome válido.
    */
    protected static final String  regExNom  =
    "^[A-Z][a-z]*(?: (?:[A-Z]|[a-z])[a-z]*)*$";

    /**
    Padrão que define como é um nome válido.
    */
    protected static final Pattern padraoNom = Pattern.compile (regExNom);

    /**
    Expressão regular que define a forma de um telefone nacional.
    */

    /**
    Padrão que define como é um telefone nacional válido.
    */
    protected static final String  regExTel =
    "^(?:\\([0-9]{2}\\) )?9?[0-9]{4}-[0-9]{4}$";

    protected static final Pattern padraoTel = Pattern.compile (regExTel);

    /**
    Valida o nome de um contato.
    Verifica se o nome fornecido como parâmetro é um nome válido,
    lançando exceções, caso incorretudes sejam detectadas.
    @param nom o nome a ser validado.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto.
    */
    public static void valideNome (String nom) throws Exception
    {
        if (nom==null)
            throw new Exception ("Nome nao fornecido");

        if (!Agenda.padraoNom.matcher(nom).matches())
            throw new Exception ("Nome invalido");
    }

    /**
    Valida o telefone de um contato.
    Verifica se o número de telefone fornecido é um número
    nacional válido, lançando exceções, caso incorretudes sejam
    detectadas.
    @param tel o telefone a ser validado.
    @throws Exception se não for fornecido um telefone, ou se o telefone
                      fornecido não parecer ser um telefone correto.
    */
    public static void valideTelefone (String tel) throws Exception
    {
        if (tel==null)
            throw new Exception ("Telefone nao fornecido");

        if (!Agenda.padraoTel.matcher(tel).matches())
            throw new Exception ("Telefone invalido");
    }

    /**
    Expressa, em cada instante, a quantidade de contatos
    armazenados na agenda.
    */
    protected int qtdContatos=0;

    /**
    Mantém armazenados os nomes dos contatos armazenados
    na agenda.
    */
    protected String[] nome;

    /**
    Mantém armazenados os telefones dos contatos armazenados
    na agenda.
    */
    protected String[] telefone;

    /**
    Localiza um nome dado na agenda.
    Procura um contato na agenda, resultando um número inteiro
    negativo, quando o nome procurado não tiver sido encontrado,
    ou um numero inteiro positivo, quando o nome procurado tiver
    sido encontrado.
    @param nom o nome a ser procurado.
    @return um inteiro ao qual se deve dar a seguinte interpretação:
            <ol>
             <li>
              Um inteiro i negativo é retornado quando o nome
              procurado não foi encontrado, mas, caso ele fosse ser
              inserido, para manter os nomes da agenda em ordem
              alfabética, o local apropriado para a inserção seria
              a posição -i-1.
             </li>
             <li>
              Um inteiro i positivo é retornado quando o nome
              procurado foi encontrado na posição i-1.
             </li>
            </ol>
    */
    protected abstract int ondeEsta (String nom);

    /**
    Constroi uma nova instância da classe Agenda.
    Para tanto, deve ser fornecido um inteiro que será utilizado
    como capacidade da instância recém criada.
    @param cap o número inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public Agenda (int cap)
                   throws Exception
    {
        if (cap <= 0)
            throw new Exception ("Capacidade invalida");

        this.nome     = new String [cap];
        this.telefone = new String [cap];
    }

    /**
    Inclui um novo contato em uma agenda.
    Acrescenta um contato com o nome e telefone fornecidos na instância
    à qual este método for aplicado.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se não for fornecido
                      um telefone, ou se o telefone fornecido não parecer ser
                      um telefone correto, ou se a agenda estiver cheia, ou
                      ainda se o nome fornecido já estiver cadastrado.
    */
    public abstract void registreContato (String nom,
                                          String tel)
                                          throws Exception;

    /**
    Remove um contato, dado seu nome.
    Exclui da instância à qual este método for aplicado o  contato
    cujo nome foi fornecido.
    @param nom o nome do contato a ser descartado.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda não contiver um
                      contato com o nome fornecido.
    */
    public void descarteContato (String nom)
                                 throws Exception
    {
        if (this.qtdContatos == 0)
            throw new Exception ("Agenda vazia");

        if (nom==null)
            throw new Exception ("Nome nao fornecido");

        if (!Agenda.padraoNom.matcher(nom).matches())
            throw new Exception ("Nome invalido");

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        int pos;

        for (pos = posicao; pos < this.qtdContatos - 1; pos++)
        {
            this.nome     [pos] = this.nome     [pos+1];
            this.telefone [pos] = this.telefone [pos+1];
        }

        this.nome     [pos] = null;
        this.telefone [pos] = null;

        this.qtdContatos--;
    }

    /**
    Gera uma representação textual de todo conteúdo da agenda.
    Produz e resulta um String com todos os nomes e telefones contidos
    na agenda.
    @return um String contendo todo o conteúdo da agenda.
    */
    public String toString ()
    {
        String ret = "";

        for (int pos=0; pos<this.qtdContatos; pos++)
            ret += this.nome[pos] + " (" + this.telefone[pos] + ")\n";

        return ret;
    }

    /**
    Verifica a igualdade entre duas agendas.
    Verifica se o Object fornecido como parâmetro representa uma
    agenda igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem agendas iguais, ou false, caso contrário.
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Agenda agenda = (Agenda)obj;

        if (this.qtdContatos != agenda.qtdContatos)
            return false;

        for (int pos=0; pos<this.qtdContatos; pos++)
            if (!this.nome    [pos].equals(agenda.nome    [pos]) ||
                !this.telefone[pos].equals(agenda.telefone[pos]))
                return false;

        return true;
    }

    /**
    Calcula o código de espalhamento (ou código de hash) de uma agenda.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da agenda representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da agenda chamante do método.
    */
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret + this.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
        {
            ret = 13*ret + this.nome    [pos].hashCode();
            ret = 13*ret + this.telefone[pos].hashCode();
        }

        return ret;
    }

    /**
    Constroi uma cópia da instância da classe Agenda dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Agenda a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Agenda (Agenda modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.nome     = new String [modelo.nome    .length];
        this.telefone = new String [modelo.telefone.length];

        this.qtdContatos = modelo.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
        {
            this.nome    [pos] = modelo.nome    [pos];
            this.telefone[pos] = modelo.telefone[pos];
        }
    }
}