package cotiinformatica;

public class Teste {
    static void main() {
        var a = new A();
        try {
            a.imprimir("JOÃO");
        } catch (Exception e) {
            System.out.println("ERRO!");
        }
    }
}

class A{
    public void imprimir(String nome) throws Exception{
        System.out.println("Olá, " + nome);
    }
}
