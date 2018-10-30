/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_controladores;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pkg_entidad.usuario;
import pkg_facadelocal.UsuarioFacade;
import javax.faces.bean.ManagedBean;
import pkg_facade.UsuarioFacadeLocal;

/**
 *
 * @author Jefferson
 */

@ManagedBean (name = "login")
@SessionScoped
public class UsuarioController implements Serializable {
    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private usuario usuario;
       
    @PostConstruct
    public void init(){
        usuario = new usuario();
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        String permisos;
        String us;
        
        String redireccion = null;
        try{
            us = EJBUsuario.iniciarSesion(usuario);

            if (us!=null){
                //Almacenar Sesión
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "index.xhtml?faces-redirect=true";
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario no Registrado",null));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario no Registrado",null));
        }
        
        return redireccion;
    } 
}
