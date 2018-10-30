/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_facadelocal;

import pkg_facade.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pkg_entidad.usuario;

/**
 *
 * @author Usuario
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<usuario> implements UsuarioFacadeLocal{

    @PersistenceContext(unitName = "proyecto_arquitectura_modulo_cuentas_entidadPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(usuario.class);
    }

    @Override
    public String iniciarSesion(usuario us) {
        String usuario="";
        String consulta;
        Object object = new Object();


        try{
            consulta = "SELECT u.nombreusuario FROM USUARIO u WHERE u.nombreusuario like ?1 and u.claveusuario like ?2";
            Query query = em.createNativeQuery(consulta);
            query.setParameter(1, us.getNombre());
            query.setParameter(2, us.getClave());

            object = query.getSingleResult();
            if(object!=null){
                usuario = String.valueOf(query.getSingleResult());
            }
        }catch(Exception e){
            throw e;
        }


        return usuario;
    }

    @Override
    public List<usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<usuario> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
