/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.LibroDAO;
import com.pe.appweb.proybiblioteca.entidades.Libro;
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
public class MantLibroMBR extends MensajeSYSUtils implements Serializable{
    
    Session session;
    Transaction transaction;
    
    private Libro libro;
    private LibroDAO libroDAO;
    private List<Libro> listalibros;
    
    
    @PostConstruct
    private void init(){
        initInstancia();
        initlistDep();
    }

    private void initInstancia(){        
        this.libro = new Libro();
        this.libroDAO = new LibroDAO(); 
        this.listalibros = new ArrayList();
    }
    
    private void initlistDep() {    
    }
    
    public void ListarLibrosxId(){
        this.session=null;
        this.transaction=null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            Libro libron = new Libro();
            libron = libroDAO.ListadoLibroId(this.session, this.libro.getIdLibro());
            
            System.out.println(libron.getTitulo());
            System.out.println("entra aaca");
            
        } catch (Exception e) {
            System.out.println("ERROR :"+e.getMessage());
        }finally{
            if (this.session!=null){
                this.session.close();
                this.transaction.rollback();
            }
        }
    }
    
    
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
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
    
    
    
}
