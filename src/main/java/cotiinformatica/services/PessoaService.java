package cotiinformatica.services;

import cotiinformatica.entities.Pessoa;
import cotiinformatica.repositories.PessoaRepository;

public class PessoaService {


    //Atributo
    private PessoaRepository pessoaRepository;

    //Método contrutor
    public PessoaService() {
        pessoaRepository = new PessoaRepository();
    }

    public void consultarPessoa(){
        var pessoaRepository = new PessoaRepository();

        try{
            for(Pessoa pessoa : pessoaRepository.consultar()){
                System.out.println("Id.........." + pessoa.getId());
                System.out.println("Nome........" + pessoa.getNome());
                System.out.println("Cpf........." + pessoa.getCpf());
                System.out.println("Data Nasc...."+ pessoa.getDataNascimento());
                System.out.println("...");
            }
        }
        catch (Exception e){
            System.out.println("\nFalha ao consultar pessoa");
            System.out.println(e.getMessage());
        }
    }
}
