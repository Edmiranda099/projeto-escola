package cotiinformatica.services;

import cotiinformatica.entities.Aluno;
import cotiinformatica.repositories.AlunoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AlunoService {

    //Atributo
    private AlunoRepository alunoRepository;

    //Método construtor
    public AlunoService(){
        alunoRepository = new AlunoRepository();
    }

    /*

        Método para realizar o fluxo de cadastro de alunos
     */


    public void cadastrarAlunos(){

        var scanner = new Scanner(System.in);

        var aluno = new Aluno();

        // Dados da Pessoa
        System.out.println("Informe o nome do aluno............:");
        aluno.setNome(scanner.nextLine());

        System.out.println("Informe o cpf do aluno..............:");
        aluno.setCpf(scanner.nextLine());

        System.out.println("Informe a data de nascimento (dd-MM-yyyy)..........:");
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        aluno.setDataNascimento(
                LocalDate.parse(scanner.nextLine(), formato)
        );

        // Dados do Aluno

        System.out.println("Informe a matrícula..................:");
        aluno.setMatricula(scanner.nextLine());

        System.out.println("Informe o curso.......................:");
        aluno.setCurso(scanner.nextLine());

        System.out.println("Informe a turma......................:");
        aluno.setTurma(scanner.nextLine());

        System.out.println("Informe a data da matrícula (dd-MM-yyyy) :");

        aluno.setDataMatricula(
                LocalDate.parse(scanner.nextLine(), formato)
        );

        try{
            alunoRepository.inserir(aluno);

            System.out.println("\nAluno matrículado com sucesso!");
        }
        catch(Exception e){
            System.out.println("Falha ao inserir aluno!");
            System.out.println(e.getMessage());
        }


    }

    /*
       Método para atualizar Aluno
    */

    public void atualizarAluno(){
        var scanner = new Scanner(System.in);

        var aluno = new Aluno();

        System.out.println("Informe o curso.....................:");
        aluno.setCurso(scanner.nextLine());

        System.out.println("Informe o nome da turma..............:");
        aluno.setTurma(scanner.nextLine());

        var alunoRepository = new AlunoRepository();

        try {
            if(alunoRepository.atualizar(aluno)){

                System.out.println("\nPessoa atualizada com sucesso");

            }
            else {

                System.out.println("\nNenhum registro foi encontrado para atualização!");

            }
        }
        catch(Exception e){
            System.out.println("\nFalha ao atualizar aluno!");
            System.out.println(e.getMessage());
        }
    }

    /*
        Método para excluir pessoa
     */

    public void excluirAluno(){
        var scanner = new Scanner(System.in);

        System.out.println("Informe o id.................:");
        var id = Integer.parseInt(scanner.nextLine());

        try{
            if (alunoRepository.excluir(id)){
                System.out.println("\nAluno excluida com sucesso!");
            }
            else{
                System.out.println("\nNenhum registro foi encontrado!");
            }
        }
        catch(Exception e){
            System.out.println("\nFalha ao excluir aluno!");
            System.out.println(e.getMessage());
        }
    }

    public void consultarAluno(){
        var alunoRepository = new AlunoRepository();

        try{

            for(Aluno aluno: alunoRepository.consultar()){
                System.out.println("Matricula................"+ aluno.getMatricula());
                System.out.println("Curso...................."+ aluno.getCurso());
                System.out.println("Turma...................."+aluno.getTurma());
                System.out.println("Data Matr................"+aluno.getDataMatricula());
                System.out.println("....");
            }
        }
        catch(Exception e){
            System.out.println("\nFalha ao consultar aluno");
            System.out.println(e.getMessage());

        }
    }

}
