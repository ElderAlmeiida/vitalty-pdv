/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.ModelUsuario;
import util.Conector;

/**
 *
 * @author ecarl
 */
public class DAOUsuario extends Conector {
    private Connection connection; 
    
    public DAOUsuario (){
         
     }
    public boolean salvarUsuarioDAO(ModelUsuario pModelUsuario){
         connectionPostgreSQL();
        //executa SQL
        String sql = "INSERT INTO tbl_usuario ("
                + "usu_nome,"
                + "usu_login,"
                + "usu_senha)"
                + " VALUES (?,?,?)";
        
        	try {
			PreparedStatement stm = criarPreparedStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stm.setString(1,pModelUsuario.getUsuNome());
                        stm.setString(2,pModelUsuario.getUsuLogin());
                        stm.setString(3,pModelUsuario.getUsuSenha());
                        stm.executeUpdate();
			
		} catch(SQLException ex) {
			Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
                closeConnectionPostgreSQL();
                return true;
                
	}

        public List<ModelUsuario> getListaUsuarioDAO (){
            List<ModelUsuario> listaUsuario = new ArrayList();
            ModelUsuario modelUsuario = new ModelUsuario();
            connectionPostgreSQL();
            ResultSet resultSet = null;
            PreparedStatement stm = null;
            
            String sql= "SELECT pk_usu_id,"
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha "
                    + "FROM tbl_usuario ";
            
                    try{
                        stm  = criarPreparedStatement(sql);
                        resultSet= stm.executeQuery();
                       
                        while(resultSet.next()){
                            modelUsuario = new ModelUsuario();
                            modelUsuario.setUsuId(resultSet.getInt(1));
                            modelUsuario.setUsuNome(resultSet.getString(2));
                            modelUsuario.setUsuLogin(resultSet.getString(3));
                            modelUsuario.setUsuSenha(resultSet.getString(4));
                            listaUsuario.add(modelUsuario);
                        }
                       
                        
                    }catch(SQLException e) {
                        System.err.println(e);
                    }
            
             closeConnectionPostgreSQL();
            return listaUsuario;
        }
        
    }
   

