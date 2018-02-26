import java.util.regex.*;

/**
A interface Agenda especifica o comportamento padr�o de uma agenda telefonica.
Inst�ncias de classes  que implementam esta interface devem conter m�todos
para incluir e excluir.
Apesar de n�o especificar, explicitamente, os m�todos can�nicos, � �bvio que
esses m�todos devem ser implementados nas classes que implementam esta
interface.
@author Andr� Lu�s dos Reis Gomes de Carvalho.
@since 2000.
*/
public interface Agenda extends Cloneable
{
    class Contato
    {
        /**
        Express�o regular que define a forma de um nome v�lido.
        */
        public static final String  regExNom  =
        "^[A-Z][a-z]*(?: (?:[A-Z]|[a-z])[a-z]*)*$";

        /**
        Padr�o que define como � um nome v�lido.
        */
        public static final Pattern padraoNom = Pattern.compile (regExNom);

        /**
        Express�o regular que define a forma de um telefone nacional.
        */
        public static final String  regExTel  =
        "^(?:\\([0-9]{2}\\) )?9?[0-9]{4}-[0-9]{4}$";

        /**
        Padr�o que define como � um telefone nacional v�lido.
        */
        public static final Pattern padraoTel = Pattern.compile (regExTel);

        /**
        Valida o nome de um contato.
        Verifica se o nome fornecido como par�metro � um nome v�lido,
        lan�ando exce��es, caso incorretudes sejam detectadas.
        @param nom o nome a ser validado.
        @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                          n�o parecer ser um nome correto.
        */
        public static void valideNome (String nom) throws Exception
        {
            if (nom==null)
                throw new Exception ("Nome nao fornecido");

            if (!Agenda.Contato.padraoNom.matcher(nom).matches())
                throw new Exception ("Nome invalido");
        }

        /**
        Valida o telefone de um contato.
        Verifica se o n�mero de telefone fornecido � um n�mero
        nacional v�lido, lan�ando exce��es, caso incorretudes sejam
        detectadas.
        @param tel o telefone a ser validado.
        @throws Exception se n�o for fornecido um telefone, ou se o telefone
                          fornecido n�o parecer ser um telefone correto.
        */
        public static void valideTelefone (String tel) throws Exception
        {
            if (tel==null)
                throw new Exception ("Telefone nao fornecido");

            if (!Agenda.Contato.padraoTel.matcher(tel).matches())
                throw new Exception ("Telefone invalido");
        }

        /**
        Atributo que representa o nome de um contato.
        */
        protected String nome;

        /**
        Atributo que representa o telefone de um contato.
        */
        protected String telefone;

        /**
        Constroi uma nova inst�ncia da classe interna e protegida
        Contato.
        Para tanto, devem ser fornecidos dois Strings que ser�o
        utilizados, respectivamente, como nome e telefone da
        inst�ncia rec�m criada.
        @param nome o String a ser utilizado como nome.
        @param telefone o String a ser utilizado como telefone.
        @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                          n�o parecer ser um nome correto, ou se n�o for fornecido
                          um telefone, ou se o telefone fornecido n�o parecer ser
                          um telefone correto.
        */
        public Contato (String nom, String tel) throws Exception
        {
            this.setNome     (nom);
            this.setTelefone (tel);
        }

        /**
        Obtem o nome de um contato.
        Permite que seja recuperado o nome do contato chamante
        do m�todo.
        @return o nome do contato.
        */
        public String getNome ()
        {
            return this.nome;
        }

        /**
        Obtem o telefone de um contato.
        Permite que seja recuperado o telefone do contato chamante
        do m�todo.
        @return o telefone do contato.
        */
        public String getTelefone ()
        {
            return this.telefone;
        }

        /**
        Ajusta o nome de um contato.
        Permite a modifica��o do nome do contato chamante
        do m�todo.
        @param nom o novo nome do contato.
        @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                          n�o parecer ser um nome correto.
        */
        public void setNome (String nom) throws Exception
        {
            Agenda.Contato.valideNome (nom);
            this.nome = nom;
        }

        /**
        Ajusta o telefone de um contato.
        Permite a modifica��o do telefone do contato chamante
        do m�todo.
        @param tel o novo telefone do contato.
        @throws Exception se n�o for fornecido um telefone, ou se o telefone
                          fornecido n�o parecer ser um telefone correto.
        */
        public void setTelefone (String tel) throws Exception
        {
            Agenda.Contato.valideTelefone (tel);
            this.telefone = tel;
        }

        /**
        Converte uma agenda em um String.
        Produz e resulta uma inst�ncia da classe String que
        representa a inst�ncia � qual este m�todo for aplicado.
        @return o String que representa a agenda chamante do
                m�todo.
        */
        public String toString ()
        {
            return this.nome     + " (" +
                   this.telefone + ")";
        }

        /**
        Verifica a igualdade entre duas agendas.
        Verifica se o Object fornecido como par�metro representa
        uma agenda com conte�do id�ntico ao da inst�ncia � qual
        este m�todo for aplicado, resultando true em caso afirmativo,
        ou false, caso contr�rio.
        @return true, caso o Object fornecido ao m�todo e a inst�ncia
                chamante do m�todo representarem fra��es numericamente
                equivalentes, ou false, caso contr�rio.
        */
        public boolean equals (Object obj)
        {
            if (obj==null)
                return false;

            if (this.getClass()!=obj.getClass())
                return false;

            Contato contato = (Contato)obj;

            if (!this.nome.equals(contato.nome))
                return false;

            if (!this.telefone.equals(contato.telefone))
                return false;

            return true;
        }

        /**
        Calcula o c�digo de espalhamento (ou c�digo de hash) de uma
        agenda.
        Calcula e resulta o c�digo de espalhamento (ou c�digo de
        hash, ou ainda o hashcode) da agenda representada pela
        inst�ncia � qual o m�todo for aplicado.
        @return o c�digo de espalhamento da agenda chamante do m�todo.
        */
        public int hashcode ()
        {
            int ret = super.hashCode();

            ret = 13*ret + this.nome.hashCode();
            ret = 13*ret + this.telefone.hashCode();

            return ret;
        }

        /**
        Constroi uma nova inst�ncia da classe Contato.
        Para tanto, deve ser fornecida uma agenda que servir� como
        modelo.
        @param modelo a inst�ncia que servir� como modelo.
        @throws Exception se o modelo for null.
        */
        public Contato (Contato modelo) throws Exception
        {
            if (modelo==null)
                throw new Exception ("Contato modelo nao fornecido");

            this.nome     = modelo.nome;
            this.telefone = modelo.telefone;
        }


        /**
        Produz uma c�pia fiel de uma agenda.
        Produz e resulta uma c�pia exata da agenda � qual o m�todo for cado.
        @return a c�pia da agenda chamante do m�todo.
        */
        public Object clone ()
        {
            Contato ret = null;

            try
            {
                ret = new Contato (this);
            }
            catch (Exception e)
            {}

            return ret;
        }
    }

    /**
    Estabelece que agendas s�o capazes de incorporar um novo contato.
    Estabelece que, dados um nome e um telefone, agendas �s quais este
    m�todo for aplicado devem passar a armazenar um novo contato com
    os dados fornecidos.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                      n�o parecer ser um nome correto, ou se n�o for fornecido
                      um telefone, ou se o telefone fornecido n�o parecer ser
                      um telefone correto, ou se a agenda estiver cheia, ou
                      ainda se o nome fornecido j� estiver cadastrado.
    */
    void registreContato (String nom, String tel) throws Exception;

    /**
    Estabelece que agendas s�o capazes de descartar um contato.
    Estabelece que, dado um nome, agendas �s quais este m�todo for aplicado
    devem deixar de armazenar o contato com o nome fornecido.
    @param nom o nome do contato a ser descartado.
    @throws Exception se n�o for fornecido um nome, ou se o nome fornecido
                      n�o parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda n�o contiver um
                      contato com o nome fornecido.
    */
    void descarteContato (String nom) throws Exception;
}