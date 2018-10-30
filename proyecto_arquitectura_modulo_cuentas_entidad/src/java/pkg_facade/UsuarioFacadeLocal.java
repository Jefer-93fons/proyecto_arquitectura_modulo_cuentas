/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_facade;

import java.util.List;
import javax.ejb.Local;
import pkg_entidad.usuario;

/**
 *
 * @author Usuario
 */
@Local
public interface UsuarioFacadeLocal {
    usuario find(Object id);
    List<usuario> findAll();
    List<usuario> findRange(int[] range);
    int count();

    String iniciarSesion(usuario us);
}
