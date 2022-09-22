/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author ecarl
 */
public class Conector {
    
    private static Connection conexao_PostgreSQL = null;
    
    //switch
    
    
    private static final String LINK2 = "jdbc:postgresql://localhost:5432/vitalty_test";
    
    
    private static final String id = "postgres";
    private static final String ps = "123456";
    
    
    // Método para fazer a conexão com um banco de dados PostgreSQL.
    public static Connection connectionPostgreSQL(){

        try {
            conexao_PostgreSQL = DriverManager.getConnection(LINK2, id, ps);
            System.out.println("conexão feita!");
        }
        catch (SQLException e){
            throw new RuntimeException("Ocorreu um problema no BD", e);
        }
        return conexao_PostgreSQL;
    }
     public static void closeConnectionPostgreSQL(){
        try {
            if (conexao_PostgreSQL == null) {
                conexao_PostgreSQL.close();
                System.out.println("Deu certo fechar");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um problema para encerrar a conexão com o banco de dados. ", e);
            
        }
        
    }
         public PreparedStatement criarPreparedStatement(String pSql, int RETURN_GENERATED_KEYS) {
            try{
                System.out.println("EXECUTANDO");
            return conexao_PostgreSQL.prepareStatement(pSql,RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }
        public PreparedStatement criarPreparedStatement(String sql){
            try {
                return this.conexao_PostgreSQL.prepareStatement(sql);
        }catch(SQLException e) {
            return null;
        }
                
            
          }

}
          
  
    
    

