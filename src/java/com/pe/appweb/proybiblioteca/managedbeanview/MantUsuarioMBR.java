/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.PersonalDAO;
import com.pe.appweb.proybiblioteca.dao.UsuarioDAO;
import com.pe.appweb.proybiblioteca.entidades.Usuario;
import com.pe.appweb.proybiblioteca.util.MensajeSYSUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author CHAR
 */
@ManagedBean
@ViewScoped
public class MantUsuarioMBR extends MensajeSYSUtils implements Serializable {
    
    
    private Usuario usuario;
    private Usuario usuariocombos;
    private UsuarioDAO usuarioDAO;
    public List<Usuario> listausuario;
    private boolean insert;
    
    private int idPersonal;
    private PersonalDAO personalDAO;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.usuario = new Usuario();
        this.usuarioDAO = new UsuarioDAO();
        this.listausuario = new ArrayList();
        this.usuariocombos = new Usuario();
        this.insert = true;
        this.personalDAO = new PersonalDAO();
    }

    private void initlistDep() {
        listadoUsuario();
    }

    public String registrarUsuario() {
        try {
            String respuesta;

            this.usuario.setIdUsuario(0);
            usuario.setIdPersonal(this.idPersonal);
            respuesta = usuarioDAO.RegistroUsuario(this.usuario);
            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del Usuario");
            } else {
                messageError("NO Se realizo la creación del Usuario");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantUsuario";
    }
    
    public String limpiarcajas() {
        this.usuario=null;
        this.usuariocombos = null;
        return "/MANTENIMIENTOS/FrmMantUsuario";
    }
    
    public void listadoUsuario(){
        try {
            
            this.listausuario = usuarioDAO.ListadoUsuarioTodos();
            //return this.listalibros;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
    
    public String actualizarUsuario(){
        try {
            usuario.setIdPersonal(this.idPersonal);
            usuarioDAO.ActualizarUsuario(this.usuario);
            this.idPersonal = 0;

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantUsuario";
    }
    
    public void cargarCombosUsuario(){
        try {
            //CARGAR COMBOS
            this.usuario.setIdUsuario(this.usuariocombos.getIdUsuario());
            this.usuario.setNombreusuario(this.usuariocombos.getNombreusuario());
            this.usuario.setClaveusuario(this.usuariocombos.getClaveusuario());
            idPersonal = this.usuariocombos.getIdPersonal();
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());  
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        } 
    }
    
    
    public String eliminarUsuario(Usuario usuario){
        String respuesta;
        try {
            respuesta = usuarioDAO.EliminarUsuario(usuario);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del usuario");
            } else {
                messageError("NO Se realizo la eliminación del usuario");
            }
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantUsuario";
    }
    
    public String buscaUsuarioxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Usuario buscarusuario = new Usuario();
            buscarusuario = this.usuarioDAO.ListadoUsuarioId(id);
            String nombreusuario = buscarusuario.getNombreusuario();
            String apellidousuario = buscarusuario.getClaveusuario();
            String cadena = nombreusuario + apellidousuario;
            return cadena; 
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuariocombos() {
        return usuariocombos;
    }

    public void setUsuariocombos(Usuario usuariocombos) {
        this.usuariocombos = usuariocombos;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getListausuario() {
        return listausuario;
    }

    public void setListausuario(List<Usuario> listausuario) {
        this.listausuario = listausuario;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public PersonalDAO getPersonalDAO() {
        return personalDAO;
    }

    public void setPersonalDAO(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    
    

}
