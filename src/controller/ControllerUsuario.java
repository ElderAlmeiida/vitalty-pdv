/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DAOUsuario;
import java.util.List;
import model.ModelUsuario;

/**
 *
 * @author ecarl
 */
public class ControllerUsuario {
    DAOUsuario dAOUsuario = new DAOUsuario();


    /**
     * salvar um novo usuario no banco
     * @param modelUsuario
     * @return booblean
     */
    public boolean salvarUsuarioController(ModelUsuario modelUsuario) {
        return this.dAOUsuario.salvarUsuarioDAO(modelUsuario);
    }
    //retorna lista
    public List<ModelUsuario> getListaUsuariosController() {
      return this.dAOUsuario.getListaUsuarioDAO();
    }
}
