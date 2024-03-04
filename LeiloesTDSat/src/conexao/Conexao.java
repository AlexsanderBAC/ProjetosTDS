/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //VARIAVEIS PARA USAR NA CONEXAO DO BANCO DE DADOS
    public String url = "jdbc:mysql://localhost:3306/LeiloesTDsat"; //Nome da base de dados
    public String user = "root"; //nome do usuário do MySQL
    public String password = "26129414"; //senha do MySQL
    
    //METODO PARA CONECTAR COM O BANCO DE DADOS
    
    public Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectou com o banco de dados!!!!");
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possivel se conectar no banco de dados!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Driver JDBC nao encontrado!.");
        }

        return conn;
    }
}