package application;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) throws SQLException {

       ConnectionFactory criaConexao = new ConnectionFactory();
       Connection connection = criaConexao.recuperarConexao();
       System.out.println("Conexao bem sucedida");

        connection.close();
    }
}
