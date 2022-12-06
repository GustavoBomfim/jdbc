package application;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        String nome = "Mouse'";
        String descricao = "Mouse sem fio); delete from Produto;";

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao)" +
                " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        // Substituindo as interrogações do values
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        while(resultSet.next()){
            Integer id = resultSet.getInt(1);
            System.out.println("O id criado foi " + id);
        }

    }
}
