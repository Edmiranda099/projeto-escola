package cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    /*
    *   Método para abrir e retornar uma conexão com o banco de dados
    * */
    public Connection createConnection() throws Exception {

        //parâmetros para criação da conexão com o BD
        var host = "jdbc:postgresql://localhost:5434/bd-alunos";
        var user = "coti";
        var pass = "coti";

        return DriverManager.getConnection(host, user, pass);
    }

}
