package application;

import application.DAO.CategoriaDAO;
import application.modelo.Categoria;
import application.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperarConexao()){
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCategoria = categoriaDAO.listarComProdutos();
            listaDeCategoria.stream().forEach(ct -> {
                System.out.println(ct.getNome());

                for(Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }

            });
        }
    }
}
