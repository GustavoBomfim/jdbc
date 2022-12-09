package application;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao)" +
                " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        // Substituindo as interrogações do values
        adicionalVariavel("SmartTV", "45 Polegadas", preparedStatement);
        adicionalVariavel("Radio", "Radio de bateria", preparedStatement);

    }

    private static void adicionalVariavel(String nome, String descricao, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);

        /*
        if(nome.equals("Radio")){
            throw new RuntimeException("Não foi possível adicionar o produto");
        }
        */

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        while(resultSet.next()){
            Integer id = resultSet.getInt(1);
            System.out.println("O id criado foi " + id);
        }
        resultSet.close();
    }
}
