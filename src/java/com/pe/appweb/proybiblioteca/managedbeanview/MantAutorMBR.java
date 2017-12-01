/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * HOLI UWU*/
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.AutorDAO;
import com.pe.appweb.proybiblioteca.entidades.Autor;
import com.pe.appweb.proybiblioteca.util.MensajeSYSUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author RAUL
 */
@ManagedBean
@ViewScoped
public class MantAutorMBR extends MensajeSYSUtils implements Serializable {
    private Autor autor;
    private Autor autorcombos;
    private AutorDAO autorDAO;
    private List<Autor> listaautor;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.autor = new Autor();
        this.autorDAO = new AutorDAO();
        this.listaautor = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
        listadoAutor();
    }

    public String eliminarAutor(Autor autor){
        String respuesta;
        try {
            respuesta = autorDAO.EliminarAutorId(autor);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del Autor");
            } else {
                messageError("NO Se realizo la eliminación del Autor");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantAutor";
    }

    public void cargarCombosAutor(){
        try {
            //CARGAR COMBOS
            this.autor.setIdAutor(this.autorcombos.getIdAutor());
            this.autor.setNombre(this.autorcombos.getNombre());
            this.autor.setApellido(this.autorcombos.getApellido());
            this.autor.setNacionalidad(this.autorcombos.getNacionalidad());
            this.autor.setSexo(this.autorcombos.getSexo());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
    }

    public String registrarAutor(){
        try {
            String respuesta;
            int idCargo = 0;

            this.autor.setIdAutor(0);
            respuesta = autorDAO.RegistrarAutor(this.autor);

            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del Autor");
            } else {
                messageError("NO Se realizo la creación del Autor");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantAutor";
    }

    public String limpiarcajas() {
        this.autor=null;
        this.autorcombos = null;
        return "/MANTENIMIENTOS/FrmMantAutor";
    }

    public void listadoAutor(){
        try {
            this.listaautor = autorDAO.ListadoAutorTodos();
            //return this.listatipo;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }

    public String actualizarAutor(){
        try {
            autorDAO.ActualizarAutor(this.autor);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantAutor";
    }
  
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutorcombos() {
        return autorcombos;
    }

    public void setAutorcombos(Autor autorcombos) {
        this.autorcombos = autorcombos;
    }

    public AutorDAO getAutorDAO() {
        return autorDAO;
    }

    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    public List<Autor> getListaautor() {
        return listaautor;
    }

    public void setListaautor(List<Autor> listaautor) {
        this.listaautor = listaautor;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }


}
