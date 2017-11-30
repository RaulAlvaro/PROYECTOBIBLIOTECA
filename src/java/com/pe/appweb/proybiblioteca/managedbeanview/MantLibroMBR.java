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

/*
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
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = libroDAO.ContadorRegistroLibro(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.libro.setIdLibro(countReg + 1);
            } else {
                this.libro.setIdLibro(1);
            }
            libro.setTipo(tipoDAO.ListadoTipoId(this.session, this.idTipo));
            respuesta = libroDAO.RegistroLibro(this.libro);
            this.transaction.commit();

            if (respuesta) {
                messageInfo("Se realizo la creación del Autor");
            } else {
                messageError("NO Se realizo la creación del Autor");
            }
        } catch (Exception ex) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public String limpiarcajas() {
        this.libro=null;
        this.librocombos = null;
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public void listadoLibro(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listalibros = libroDAO.ListadoLibrosTodos(this.session);
            this.transaction.commit();
            //return this.listalibros;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
    public String actualizarLibro(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            libro.setTipo(tipoDAO.ListadoTipoId(this.session, this.idTipo));
            libroDAO.ActualizarLibro(this.session, this.libro);
            this.transaction.commit();
            this.idTipo = 0;
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return "/MANTENIMIENTOS/FrmMantLibro";
    }
    
    public void cargarCombosLibro(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.libro.setIdLibro(this.librocombos.getIdLibro());
            this.libro.setTitulo(this.librocombos.getTitulo());
            this.libro.setEdicion(this.librocombos.getEdicion());;
            idTipo = this.librocombos.getTipo().getIdTipo();
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
    
    public String eliminarLibro(Libro libro){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = libroDAO.EliminarLibro(session, libro);
            this.transaction.commit();
            if (respuesta) {
                messageInfo("Se realizo la elminación del Autor");
            } else {
                messageError("NO Se realizo la eliminación del Autor");
            }
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return "/MANTENIMIENTOS/FrmMantLibro";
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
    
    */
    

}
