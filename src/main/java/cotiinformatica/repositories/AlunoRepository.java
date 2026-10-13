package cotiinformatica.repositories;

import cotiinformatica.entities.Aluno;
import cotiinformatica.entities.Pessoa;
import cotiinformatica.factories.ConnectionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    //Atributos
    private ConnectionFactory connectionFactory;

    //Metodo construtor
    public AlunoRepository(){
        //Instanciando /inicializar os atributos
        connectionFactory = new ConnectionFactory();
    }

    /*
        Método para inserir pessoa no banco de dados
     */

    public void inserir(Aluno aluno) throws Exception {
        try (var connection = connectionFactory.createConnection()) {

            connection.setAutoCommit(false);

            //Abrindo um comando SQL para inserir um aluno no banco de dados

            try {
                //1.Inserindo os dados da pessoa
                try (var statementPessoa = connection.prepareStatement("insert into pessoas(nome, cpf, datanascimento) values(?, ?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS)) {

                    statementPessoa.setString(1, aluno.getNome());
                    statementPessoa.setString(2, aluno.getCpf());
                    statementPessoa.setObject(3, aluno.getDataNascimento());

                    statementPessoa.executeUpdate();

                    // Recuperando o ID gerado pelo PostgreSQL

                    try (var result = statementPessoa.getGeneratedKeys()) {

                        if (!result.next()) {


                            throw new Exception("Não foi possível recuperar o ID  da pessoa.");

                        }

                        aluno.setId(result.getInt(1));
                    }
                }

                // 2. Inserindo os dados específicos do aluno

                try (var statementAluno = connection.prepareStatement(
                        "insert into alunos(id, matricula, curso, turma, datamatricula, ativo) " + "values(?, ?, ?, ?, ?, ?)"

                )) {

                    statementAluno.setInt(1, aluno.getId());
                    statementAluno.setString(2, aluno.getMatricula());
                    statementAluno.setString(3, aluno.getCurso());
                    statementAluno.setString(4, aluno.getTurma());
                    statementAluno.setObject(5, aluno.getDataMatricula());
                    statementAluno.setBoolean(6, aluno.getAtivo() != null ? aluno.getAtivo() : true);

                    statementAluno.executeUpdate();
                }

                // Confirmando as duas operações
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }
    }

    /*
        Método para atualizar alunos no banco de dados
     */


    public boolean atualizar(Aluno aluno) throws Exception{
        try(var connection = connectionFactory.createConnection()){
            var statement = connection.prepareStatement("update alunos set matricula=?, curso=?,turma=?, dataMatricula=?, ativo=? where id=?");
            statement.setString(1, aluno.getMatricula());
            statement.setString(2, aluno.getCurso());
            statement.setString(3, aluno.getTurma());
            statement.setObject(4, aluno.getDataMatricula());
            statement.setBoolean(5, aluno.getAtivo());
            statement.setInt(6, aluno.getId());
            return statement.executeUpdate() > 0;
        }
    }

    /*
        Método para excluir aluno no banco de dados
     */
    public boolean excluir(int id) throws Exception{
        try (var connection = connectionFactory.createConnection()){
            //Executando um comando SQL para excluir um aluno do banco de dados
            var statement = connection.prepareStatement("delete from alunos where id =?");
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    /*
        Método para consultar e imprimir dados de alunos
     */

    public List<Aluno> consultar() throws Exception{
        try(var connection = connectionFactory.createConnection()){
            var statement = connection.prepareStatement("select matricula, curso, turma, dataMatricula, ativo from alunos order by id");
            var result = statement.executeQuery();

            //Estou criando uma variável lista de pessoas
            var lista = new ArrayList<Aluno>();

            //Percorrendo cada registro obtido do banco de dados
            while(result.next()){

                var aluno = new Aluno();

                aluno.setMatricula(result.getString("matricula"));
                aluno.setCurso(result.getString("curso"));
                aluno.setTurma(result.getString("turma"));
                aluno.setDataMatricula(result.getObject("dataMatricula", LocalDate.class));

                lista.add(aluno);
            }
            return lista;
        }
    }
}
