/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    private Conexao conexao;
    private Connection conn;

    public ProdutosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES "
                + "(?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir o produto: " + e.getMessage());
        }    
    }
    
    public List<ProdutosDTO> getProdutosDTO() {
        String sql = "select id, nome, valor, status from produtos;"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            //Como temos um parâmetro, devemos defini-lo
            //Método para poder executar o SELECT.
            //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
            ResultSet rs = stmt.executeQuery();

            List<ProdutosDTO> listaProdutosDTO = new ArrayList<>();

            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutosDTO.add(produto);
            }

            return listaProdutosDTO;

            //Se o método entrar no "Catch" quer dizer que não encontrou nenhum filme, então damos um "return null"
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
