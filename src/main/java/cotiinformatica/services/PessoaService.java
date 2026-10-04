package cotiinformatica.services;

import cotiinformatica.entities.Pessoa;
import cotiinformatica.repositories.PessoaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PessoaService {
    //Atributo
    private PessoaRepository pessoaRepository;

    //Método contrutor
    public PessoaService() {
        pessoaRepository = new PessoaRepository();
    }

    /*

        Método para realizar o fluxo de cadastro de pessoas

     */
    public void cadastrarPessoas(){

        var scanner = new Scanner(System.in);

        var pessoa = new Pessoa();

        System.out.println("Informe o seu nome:................:");
        pessoa.setNome(scanner.next());

        System.out.println("Informe o seu cpf:..................:");
        pessoa.setCpf(scanner.nextLine());

        System.out.println("Informe o seu nascimento:............:");
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        pessoa.setDataNascimento(
                LocalDate.parse(scanner.nextLine(), formato)
        );

        var pessoaRepository = new PessoaRepository();
        try{
            pessoaRepository.inserir(pessoa);

            System.out.println("\nPessoa cadastrada com sucesso!");
        }
        catch(Exception e){
            System.out.println("Falha ao inserir pessoa");
        }
    }

    public void atualizarPessoa(){
        var scanner = new Scanner(System.in);

        var pessoa = new Pessoa();

        System.out.println("Informe o id......................:");
        pessoa.setId(scanner.nextInt());

        System.out.println("Informe o seu nome:................:");
        pessoa.setNome(scanner.nextLine());

        System.out.println("Informe o seu cpf:..................:");
        pessoa.setCpf(scanner.nextLine());

        System.out.println("Informe o nascimento(dd-MM-yyyy):....:");
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        pessoa.setDataNascimento(
                LocalDate.parse(scanner.nextLine(), formato)
        );

        var pessoaRpository = new PessoaRepository();
        try{
            if(pessoaRepository.atualizar(pessoa)){
                System.out.println("\nPessoa atualizado com sucesso!");
            }
            else{
                System.out.println("\nNenhum registro foi encontrado para atualização!");
            }
        }
        catch(Exception e){
            System.out.println("\nFalha ao atualizar pessoa");
            System.out.println(e.getMessage());
        }


    }
    public void excluirPessoa(){
        //TODO
    }

    public void consultarPessoa(){
        var pessoaRepository = new PessoaRepository();
        //TODO
    }
}
