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

    public ProdutosDTO getProduto(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            ProdutosDTO produto = new ProdutosDTO();

            rs.next();
            produto.setId(id);
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            return produto;

            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }

    public void venderProduto(ProdutosDTO produto) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        try {
            //esse trecho é igual ao método inserir
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //Setando os parâmetros
            stmt.setString(1, produto.getStatus());
            stmt.setInt(2, produto.getId());
            //Executando a query
            stmt.execute();
            //tratando o erro, caso ele ocorra
        } catch (SQLException e) {
            System.out.println("Erro ao editar o status: " + e.getMessage());
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

    public List<ProdutosDTO> getProdutoStatus(String status) { //parâmetro para buscar a empresa pelo nome
        String sql = "SELECT * FROM produtos WHERE status LIKE ?"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            //Como temos um parâmetro, devemos defini-lo
            stmt.setString(1, "%" + status + "%"); //Conforme for a palavra ou letra digitada para pesquisa, será buscada antes, no meio e no fim
            //Método para poder executar o SELECT.
            //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
            ResultSet rs = stmt.executeQuery();

            //Vamos criar um objeto do tipo List
            //Faça a importação do ArrayList
            List<ProdutosDTO> listaprodutostatus = new ArrayList<>();
            //percorrer o resultSet e salvar as informações dentro de uma variável "status"
            //Depois salva esse objeto dentro da lista

            //Estrutura de repetição While
            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO produtos = new ProdutosDTO();
                //Salvar dentro do objeto empresa as informações            
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                //Adicionando os elementos na lista criada
                listaprodutostatus.add(produtos);

            }
            //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
            return listaprodutostatus;

            //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
        } catch (Exception e) {
            return null;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}