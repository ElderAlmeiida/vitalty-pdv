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

import model.ModelProduto;
import util.Conector;
/**
*
* @author elder
*/
public class DAOProduto extends Conector {

    /**
    * grava Produto
    * @param pModelProduto
    * @return int
    */
    public int salvarProdutoDAO(ModelProduto pModelProduto){
        connectionPostgreSQL();
        String sql = 
            "INSERT INTO tbl_produto ("
                  
                    + "pro_descricao,"
                    + "pro_quantidade,"
                    + "pro_valor"
                + ") VALUES (?,?,?);"
            ;
        
        try (PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            preparedStatement.setString(1, pModelProduto.getProDescricao());
            preparedStatement.setInt(2, pModelProduto.getProQuantidade());
            preparedStatement.setDouble(3, pModelProduto.getProValor());
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally{
             closeConnectionPostgreSQL();
             
    }
    }
/**
    * recupera Produto
    * @param pProId
    * @return ModelProduto
    */
    

    /**
    * recupera uma lista de Produto
        * @return ArrayList
    */
    public ArrayList<ModelProduto> getListaProdutoDAO(){
        ArrayList<ModelProduto> listamodelProduto = new ArrayList();
        ModelProduto modelProduto = null;

        String sql = "SELECT "
                    + "pk_pro_id,"
                    + "pro_descricao,"
                    + "pro_quantidade,"
                    + "pro_valor"
                 + " FROM"
                     + " tbl_produto"
                + ";";

        connectionPostgreSQL();

        try (PreparedStatement preparedStatement = criarPreparedStatement(sql)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                modelProduto = new ModelProduto();
                modelProduto.setProId(resultSet.getInt(1));
                modelProduto.setProDescricao(resultSet.getString(2));
                modelProduto.setProQuantidade(resultSet.getInt(3));
                modelProduto.setProValor(resultSet.getDouble(4));
                listamodelProduto.add(modelProduto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnectionPostgreSQL();
        }
        return listamodelProduto;
    }

    /**
    * atualiza Produto
    * @param pModelProduto
    * @return boolean
    */
    public boolean atualizarProdutoDAO(ModelProduto pModelProduto){
        
        connectionPostgreSQL();
        String sql = 
            "UPDATE tbl_produto SET "
                    + "pk_pro_id = ?,"
                    + "pro_descricao = ?,"
                    + "pro_quantidade = ?,"
                    + "pro_valor = ?"
                + " WHERE pk_pro_id = ? "
                +";"
            ;
        
        try (PreparedStatement preparedStatement = criarPreparedStatement(sql)){
            preparedStatement.setInt(1, pModelProduto.getProId());
            preparedStatement.setString(2, pModelProduto.getProDescricao());
            preparedStatement.setInt(3, pModelProduto.getProQuantidade());
            preparedStatement.setDouble(4, pModelProduto.getProValor());
            preparedStatement.setInt(5, pModelProduto.getProId());
            return preparedStatement.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            closeConnectionPostgreSQL();
        }

    }
/**
    * exclui Produto
    * @param pProId
    * @return boolean
    */
    public boolean excluirProdutoDAO(int pProId){
        connectionPostgreSQL();
        String sql = 
            "DELETE FROM tbl_produto "
                + " WHERE pk_pro_id = ? "
                +";"
            ;
        
        try (PreparedStatement preparedStatement = criarPreparedStatement(sql)){
            preparedStatement.setInt(1, pProId);
            return preparedStatement.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
           closeConnectionPostgreSQL();
        }

    }

}