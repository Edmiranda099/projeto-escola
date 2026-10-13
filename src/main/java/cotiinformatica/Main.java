package cotiinformatica;

import cotiinformatica.services.AlunoService;
import cotiinformatica.services.PessoaService;

import java.util.Scanner;

public class Main {
    static void main() {
        var alunoService = new AlunoService();
        var pessoaService = new PessoaService();

        System.out.println("\nPROJETO ESCOLA:\n");

        System.out.println("(1) Cadastrar Aluno");
        System.out.println("(2) Atualizar aluno");
        System.out.println("(3) Excluir aluno");
        System.out.println("(4) Consultar pessoa");

        var scanner = new Scanner(System.in);
        System.out.println("\nESCOLHA UM OPCAO:\n");
        var opcao = Integer.parseInt(scanner.nextLine());

        switch(opcao) {
            case 1:
                System.out.println("\nCadastro de alunos:\n");
                alunoService.cadastrarAlunos();
                break;
            case 2:
                System.out.println("\nAtualizar aluno:\n");
                alunoService.atualizarAluno();
                break;
            case 3:
                System.out.println("\nExcluir aluno:\n");
                alunoService.excluirAluno();
                break;
            case 4:
                System.out.println("\nConsultar pessoa:\n");
                pessoaService.consultarPessoa();
                break;
            default:
                System.out.println("\nOpção Invalida");
                break;
        }
    }
}
