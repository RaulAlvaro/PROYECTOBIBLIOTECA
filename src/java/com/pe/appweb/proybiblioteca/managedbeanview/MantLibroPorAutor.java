/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.AutorDAO;
import com.pe.appweb.proybiblioteca.dao.LibroDAO;
import com.pe.appweb.proybiblioteca.dao.LibroporautorDAO;
import com.pe.appweb.proybiblioteca.entidades.Autor;
import com.pe.appweb.proybiblioteca.entidades.Libro;
import com.pe.appweb.proybiblioteca.entidades.Libroporautor;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import com.pe.appweb.proybiblioteca.util.MensajeSYSUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RAUL
 */
@ManagedBean
@ViewScoped
public class MantLibroPorAutor extends MensajeSYSUtils implements Serializable{
    
    private Session session;
    private Transaction transaction;
    
    private Libroporautor libroporautor;
    private LibroporautorDAO libroporautorDAO;
    
    private int idLibro;
    private int idAutor;
    
    private Libro libro;
    private Autor autor;
    
    private LibroDAO libroDAO;
    private AutorDAO autorDAO;
    
    private Boolean insert;
    
    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        libroporautor = new Libroporautor();
        libroporautorDAO = new LibroporautorDAO();
        
        libro = new Libro();
        
        autor = new Autor();
        this.insert = true;
    }

    private void initlistDep() {
    }

    public String registrarLibroPorAutor(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;
            
            
            this.autor = autorDAO.ListadoAutorId(this.session, this.idAutor);
            this.libro = libroDAO.ListadoLibroId(this.session, this.idLibro);
            this.libroporautor.setAutor(this.autor);
            this.libroporautor.setLibro(this.libro);
            int countReg = libroporautorDAO.ContadorRegistroTipo(this.session);
            int idCargo = 0;
            if (countReg != 0) {
                this.libroporautor.setIdLibroPorAutor(countReg+1);
            } else {
                this.libroporautor.setIdLibroPorAutor(1);
            }
            respuesta = libroporautorDAO.RegistrarLibroPorAutor(this.libroporautor);
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
        return "/MANTENIMIENTOS/FrmMantLibroPorAutor";
    }
    
    
    public String limpiarcajas() {
        this.autor=null;
        this.libro=null;
        return "/MANTENIMIENTOS/FrmMantLibroPorAutor";
    }
    
    
    
    public Libroporautor getLibroporautor() {
        return libroporautor;
    }

    public void setLibroporautor(Libroporautor libroporautor) {
        this.libroporautor = libroporautor;
    }

    public LibroporautorDAO getLibroporautorDAO() {
        return libroporautorDAO;
    }

    public void setLibroporautorDAO(LibroporautorDAO libroporautorDAO) {
        this.libroporautorDAO = libroporautorDAO;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public LibroDAO getLibroDAO() {
        return libroDAO;
    }

    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public AutorDAO getAutorDAO() {
        return autorDAO;
    }

    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }
    
    
}
