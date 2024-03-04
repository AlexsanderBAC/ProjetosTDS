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
import java.util.ArrayList;

public class ProdutosDAO {

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    private Conexao conexao;
    private Connection conn;

    public ProdutosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void cadastrarProduto(ProdutosDTO produto) {

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
