/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.TipoDAO;
import com.pe.appweb.proybiblioteca.entidades.Tipo;
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
public class MantTipoMBR extends MensajeSYSUtils implements Serializable{
    
    Session session;
    Transaction transaction;
    
    private Tipo tipo;
    private Tipo tipocombos;
    private TipoDAO tipoDAO;
    private List<Tipo> listatipo;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.tipo = new Tipo();
        this.tipoDAO = new TipoDAO();
        this.listatipo = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
    }

    public String eliminarTipo(Tipo tipo){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = tipoDAO.EliminarTipoId(session, tipo);
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
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public void cargarCombosAutor(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.tipo.setIdTipo(this.tipocombos.getIdTipo());
            this.tipo.setTipo(this.tipocombos.getTipo());
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
    
    public String registrarTipo(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = tipoDAO.ContadorRegistroTipo(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.tipo.setIdTipo(countReg + 1);
            } else {
                this.tipo.setIdTipo(1);
            }
            respuesta = tipoDAO.RegistrarTipo(this.tipo);
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
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public String limpiarcajas() {
        this.tipo=null;
        this.tipocombos = null;
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public List<Tipo> listadoTipo(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listatipo = tipoDAO.ListadoTipoTodos(this.session);
            this.transaction.commit();
            return this.listatipo;
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
    
    public String actualizarTipo(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            tipoDAO.ActualizarTipo(session, this.tipo);
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
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipocombos() {
        return tipocombos;
    }

    public void setTipocombos(Tipo tipocombos) {
        this.tipocombos = tipocombos;
    }

    public TipoDAO getTipoDAO() {
        return tipoDAO;
    }

    public void setTipoDAO(TipoDAO tipoDAO) {
        this.tipoDAO = tipoDAO;
    }

    public List<Tipo> getListatipo() {
        return listatipo;
    }

    public void setListatipo(List<Tipo> listatipo) {
        this.listatipo = listatipo;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }
    
    
    
    
    
}
