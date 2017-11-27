/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * HOLI UWU*/
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.AutorDAO;
import com.pe.appweb.proybiblioteca.dao.LibroDAO;
import com.pe.appweb.proybiblioteca.entidades.Autor;
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
public class MantAutorMBR extends MensajeSYSUtils implements Serializable {

    Session session;
    Transaction transaction;

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
    }
    
    public String eliminarAutor(Autor autor){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = autorDAO.EliminarAutorID(session, autor);
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
        return "/MANTENIMIENTOS/FrmMantAutor";
    }
    
    
    public void cargarCombos() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.autor.setIdAutor(this.autorcombos.getIdAutor());
            this.autor.setNombre(this.autorcombos.getNombre());
            this.autor.setApellido(this.autorcombos.getApellido());
            this.autor.setSexo(this.autorcombos.getSexo());
            this.autor.setNacionalidad(this.autorcombos.getNacionalidad());

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

    public String registrarAutor() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = autorDAO.ContadorRegistroAutor(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.autor.setIdAutor(countReg + 1);
            } else {
                this.autor.setIdAutor(1);
            }
            respuesta = autorDAO.RegistrarAutor(this.autor);
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
        return "/MANTENIMIENTOS/FrmMantAutor";
    }

    public String limpiarcajas() {
        this.autor=null;
        this.autorcombos = null;
        return "/MANTENIMIENTOS/FrmMantAutor";
    }

    public void ListarAutorxId() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.autor = autorDAO.ListadoAutorId(session, this.autor.getIdAutor());
            System.out.println(autor.getNombre());
            System.out.println("entra aaca");

        } catch (Exception e) {
            System.out.println("ERROR :" + e.getMessage());
        } finally {
            if (this.session != null) {
                this.session.close();
                this.transaction.rollback();
            }
        }
    }

    public List<Autor> listadoautor() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.listaautor = autorDAO.ListadoAutorTodos(this.session);
            this.transaction.commit();
            return this.listaautor;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
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
    }

    public String actualizarAutor() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            Autor mautor = new Autor();
            
            mautor.setNombre(this.autor.getNombre());
            mautor.setApellido(this.autor.getApellido());
            mautor.setSexo(this.autor.getSexo());
            mautor.setNacionalidad(this.autor.getNacionalidad());
            
            autorDAO.ActualizarAutor(session, this.autor);
            this.transaction.commit();

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
