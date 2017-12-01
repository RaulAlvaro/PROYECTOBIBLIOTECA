/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.LibroDAO;
import com.pe.appweb.proybiblioteca.dao.TipoDAO;
import com.pe.appweb.proybiblioteca.entidades.Libro;
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
public class MantLibroMBR extends MensajeSYSUtils implements Serializable {
    
    
    private Libro libro;
    private Libro librocombos;
    private LibroDAO libroDAO;
    private List<Libro> listalibros;
    private boolean insert;
    
    private int idTipo;
    private TipoDAO tipoDAO;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.libro = new Libro();
        this.libroDAO = new LibroDAO();
        this.listalibros = new ArrayList();
        this.librocombos = new Libro();
        this.insert = true;
        this.tipoDAO = new TipoDAO();
    }

    private void initlistDep() {
        listadoLibro();
    }

    public String registrarLibro() {
        try {
            String respuesta;

            this.libro.setIdLibro(0);
            libro.setIdTipo(this.idTipo);
            respuesta = libroDAO.RegistroLibro(this.libro);
            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del Autor");
            } else {
                messageError("NO Se realizo la creación del Autor");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public String limpiarcajas() {
        this.libro=null;
        this.librocombos = null;
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public void listadoLibro(){
        try {
            
            this.listalibros = libroDAO.ListadoLibrosTodos();
            //return this.listalibros;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
    
    public String actualizarLibro(){
        try {
            libro.setIdTipo(this.idTipo);
            libroDAO.ActualizarLibro(this.libro);
            this.idTipo = 0;

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public void cargarCombosLibro(){
        try {
            //CARGAR COMBOS
            this.libro.setIdLibro(this.librocombos.getIdLibro());
            this.libro.setTitulo(this.librocombos.getTitulo());
            idTipo = this.librocombos.getIdTipo();
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());  
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        } 
    }
    
    
    public String eliminarLibro(Libro libro){
        String respuesta;
        try {
            respuesta = libroDAO.EliminarLibro(libro);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del Autor");
            } else {
                messageError("NO Se realizo la eliminación del Autor");
            }
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public String buscaLibroxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Libro buscarlbro = new Libro();
            buscarlbro = this.libroDAO.ListadoLibroId(id);
            String nombretipo = buscarlbro.getTitulo();
            return nombretipo; 
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Libro getLibrocombos() {
        return librocombos;
    }

    public void setLibrocombos(Libro librocombos) {
        this.librocombos = librocombos;
    }

    public LibroDAO getLibroDAO() {
        return libroDAO;
    }

    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public List<Libro> getListalibros() {
        return listalibros;
    }

    public void setListalibros(List<Libro> listalibros) {
        this.listalibros = listalibros;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public TipoDAO getTipoDAO() {
        return tipoDAO;
    }

    public void setTipoDAO(TipoDAO tipoDAO) {
        this.tipoDAO = tipoDAO;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    

}
