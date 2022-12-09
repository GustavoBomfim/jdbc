package application;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        try(Connection connection = factory.recuperarConexao()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao)" +
                    " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ) {

                // Substituindo as interrogações do values
                adicionalVariavel("SmartTV", "45 Polegadas", preparedStatement);
                adicionalVariavel("Radio", "Radio de bateria", preparedStatement);

                connection.commit();

                //preparedStatement.close();
                //connection.close();

            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }

    }

    private static void adicionalVariavel(String nome, String descricao, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);


        if(nome.equals("Radio")){
            throw new RuntimeException("Não foi possível adicionar o produto");
        }


        preparedStatement.execute();

        try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                System.out.println("O id criado foi " + id);
            }
        }
        //resultSet.close();
    }
}
