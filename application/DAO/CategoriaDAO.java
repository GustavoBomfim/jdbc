package application.DAO;

import application.modelo.Categoria;
import application.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection){
        this.connection = connection;
    }
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a query de listar categoria");

        String sql = "SELECT ID, NOME FROM CATEGORIA";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()){
                    Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));

                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException{
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a query de listar categoria");

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN" +
                " PRODUTO P ON C.ID = P.CATEGORIA_ID";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()){
                    if(ultima == null || !ultima.getNome().equals(resultSet.getString(2))){
                        Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));

                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getString(5));
                    ultima.adicionar(produto);
                }
            }
        }
        return categorias;
    }
}
