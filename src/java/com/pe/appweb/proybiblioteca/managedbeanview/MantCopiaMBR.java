/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.CopiaDAO;
import com.pe.appweb.proybiblioteca.dao.LibroDAO;
import com.pe.appweb.proybiblioteca.entidades.Copia;
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
public class MantCopiaMBR extends MensajeSYSUtils implements Serializable{
    
    private Copia copia;
    private Copia copiacombos;
    private CopiaDAO copiaDAO;
    private List<Copia> listacopia;
    private Boolean insert;
    
    private int idLibro;
    private Libro libro;
    
    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.copia = new Copia();
        this.copiacombos = new Copia();
        this.copiaDAO = new CopiaDAO();
        this.listacopia = new ArrayList();
        this.insert = true;
        this.libro = new Libro();
    }

    private void initlistDep() {
        //listadoTipo();
    }
    
    public String registrarCopia() {
        try {
            String respuesta;

            this.copia.setIdCopia(0);
            copia.setIdLibro(this.idLibro);
            respuesta = copiaDAO.RegistraCopia(this.copia);
            
            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación de la Copia");
            } else {
                messageError("NO Se realizo la creación de la Copia");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantCopia";
    }
    
    public String limpiarcajas() {
        this.libro=null;
        this.copia=null;
        this.copiacombos = null;
        return "/MANTENIMIENTOS/FrmMantCopia";
    }
    
    public void listadoCopia(){
        try {
            this.listacopia = copiaDAO.ListadoCopiassTodos();
            
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
    
    public String actualizarCopia(){
        try {
            //libro.setIdTipo(this.idTipo);
            //libroDAO.ActualizarLibro(this.libro);
            //this.idTipo = 0;
            this.copia.setIdLibro(this.idLibro);
            copiaDAO.ActualizarCopia(this.copia);
            insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantCopia";
    }
    
    public void cargarCombosCopia(){
        try {
            //CARGAR COMBOS
            /*
            this.libro.setIdLibro(this.librocombos.getIdLibro());
            this.libro.setTitulo(this.librocombos.getTitulo());
            idTipo = this.librocombos.getIdTipo();
            */
            this.copia.setIdCopia(this.copiacombos.getIdCopia());
            this.idLibro = this.copiacombos.getIdLibro();
            this.copia.setEstado(this.copiacombos.isEstado());
            this.copia.setEdicion(this.copiacombos.getEdicion());
            this.copia.setEditorial(this.copiacombos.getEditorial());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());  
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        } 
    }
    
    
    public String eliminarLibro(Copia copia){
        String respuesta;
        try {
            respuesta = copiaDAO.EliminarCopia(copia);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación de la Copia");
            } else {
                messageError("NO Se realizo la eliminación de la Copia");
            }
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantCopia";
    }
    
    
    public Copia getCopia() {
        return copia;
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }

    public Copia getCopiacombos() {
        return copiacombos;
    }

    public void setCopiacombos(Copia copiacombos) {
        this.copiacombos = copiacombos;
    }

    public CopiaDAO getCopiaDAO() {
        return copiaDAO;
    }

    public void setCopiaDAO(CopiaDAO copiaDAO) {
        this.copiaDAO = copiaDAO;
    }

    public List<Copia> getListacopia() {
        return listacopia;
    }

    public void setListacopia(List<Copia> listacopia) {
        this.listacopia = listacopia;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    
}
