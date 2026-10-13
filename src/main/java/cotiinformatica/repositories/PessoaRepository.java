package cotiinformatica.repositories;

import cotiinformatica.entities.Pessoa;
import cotiinformatica.factories.ConnectionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    //Attributes
    private ConnectionFactory connectionFactory;

    //Método construtor
    public PessoaRepository() {
        //Instanciando / inicializar os atributos
        connectionFactory = new ConnectionFactory();
    }

    /*

        Método para inserir pessoa no banco de dados

    */

    public void inserir(Pessoa pessoa) throws Exception{
        try(var connection = connectionFactory.createConnection())
        //Executando um comando SQL para inserir pessoa no banco de dados
        {
            var statement = connection.prepareStatement
                    ("insert into pessoas(nome,cpf, dataNascimento) values(?, ?, ?)");
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());
            statement.setObject(3, pessoa.getDataNascimento());
            statement.execute();
        }
    }

    /*

    Método para atualizar pessoas no banco de dados

    */

    public boolean atualizar(Pessoa pessoa) throws Exception{
        try(var connection = connectionFactory.createConnection()){
            //Executando o comando SQL para atualizar uma pessoa no banco de dados
            var statement = connection.prepareStatement("update pessoas set nome=?, cpf=?, dataNascimento=? where id=?");
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());
            statement.setObject(3, pessoa.getDataNascimento());
            statement.setInt(4, pessoa.getId());
            return statement.executeUpdate() > 0;
        }
    }

    /*

        Método para excluir pessoa no banco de dados

     */

    public boolean excluir(Integer id)throws Exception{
        try(var connection = connectionFactory.createConnection()){
            //Executando um comado SQL para excluir uma pessoa no banco de dados
            var statement = connection.prepareStatement("delete from pessoas where id=?");
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        }
    }

    /*

        Método para consultar e imprimir os dados de pessoas

     */

    public List<Pessoa> consultar() throws Exception{

        try(var connection = connectionFactory.createConnection()){

            System.out.println("Banco conectado: " +
                    connection.getCatalog());

            System.out.println("Schema conectado: " +
                    connection.getSchema());

            var statement = connection.prepareStatement("select id, nome, cpf, datanascimento " +
                    "from pessoas order by id"
            );

            var result =  statement.executeQuery();

            //Estou criando uma variável lista de pessoas
            var lista = new ArrayList<Pessoa>();//Acabou de criar uma lista vazia

            //Percorrendo cada registro obtido do banco de dados
            while(result.next()){

                var pessoa = new Pessoa();

                pessoa.setId(result.getInt("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setCpf(result.getString("cpf"));
                pessoa.setDataNascimento(result.getObject("datanascimento", LocalDate.class));

                lista.add(pessoa);

            }
            return lista;
        }
    }




}
