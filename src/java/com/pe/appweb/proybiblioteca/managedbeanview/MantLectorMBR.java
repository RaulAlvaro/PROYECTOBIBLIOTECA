/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.LectorDAO;
import com.pe.appweb.proybiblioteca.entidades.Lector;
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
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class MantLectorMBR extends MensajeSYSUtils implements Serializable{
    
    Session session;
    Transaction transaction;

    private Lector lector;
    private Lector lectorcombos;
    private LectorDAO lectorDAO;
    private List<Lector> listalector;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.lector = new Lector();
        this.lectorDAO = new LectorDAO();
        this.listalector = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
    }
    
    public String eliminarLector(Lector lector){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = lectorDAO.EliminarLectorID(session, lector);
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
        return "/MANTENIMIENTOS/FrmMantLector";
    }
    
    
    public void cargarCombos() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.lector.setCodigo(this.lectorcombos.getCodigo());
            this.lector.setNombres(this.lectorcombos.getNombres());
            this.lector.setApellidos(this.lectorcombos.getApellidos());
            this.lector.setSexo(this.lectorcombos.getSexo());
            this.lector.setTelefono(this.lectorcombos.getTelefono());
            this.lector.setStrikes(this.lectorcombos.getStrikes());
            this.lector.setEstado(this.lectorcombos.isEstado());
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

    public String registrarLector() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = lectorDAO.ContadorRegistroLector(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.lector.setCodigo(countReg + 1);
            } else {
                this.lector.setCodigo(1);
            }
            respuesta = lectorDAO.RegistrarLector(this.lector);
            this.transaction.commit();

            if (respuesta) {
                messageInfo("Se realizo la creación del Lector");
            } else {
                messageError("NO Se realizo la creación del Lector");
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
        return "/MANTENIMIENTOS/FrmMantLector";
    }

    public String limpiarcajas() {
        this.lector=null;
        this.lectorcombos = null;
        return "/MANTENIMIENTOS/FrmMantLector";
    }

    public void ListarLectorxId() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.lector = lectorDAO.ListadoLectorId(session, this.lector.getCodigo());
            System.out.println(lector.getNombres());
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

    public List<Lector> listadolector() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.listalector = lectorDAO.ListadoLectorTodos(this.session);
            this.transaction.commit();
            return this.listalector;
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

    public String actualizarLector() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            Lector mlector = new Lector();
            
            mlector.setNombres(this.lector.getNombres());
            mlector.setApellidos(this.lector.getApellidos());
            mlector.setSexo(this.lector.getSexo());
            mlector.setTelefono(this.lector.getTelefono());
            mlector.setEstado(this.lector.isEstado());
            mlector.setStrikes(this.lector.getStrikes());
            
            lectorDAO.ActualizarLector(session, this.lector);
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
        return "/MANTENIMIENTOS/FrmMantLector";
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Lector getLectorcombos() {
        return lectorcombos;
    }

    public void setLectorcombos(Lector lectorcombos) {
        this.lectorcombos = lectorcombos;
    }

    public LectorDAO getLectorDAO() {
        return lectorDAO;
    }

    public void setLectorDAO(LectorDAO lectorDAO) {
        this.lectorDAO = lectorDAO;
    }

    public List<Lector> getListalector() {
        return listalector;
    }

    public void setListalector(List<Lector> listalector) {
        this.listalector = listalector;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }
    
}
