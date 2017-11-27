/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.TipopersonalDAO;
import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
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
public class MantTipopersonalMBR extends MensajeSYSUtils implements Serializable{
    Session session;
    Transaction transaction;
    
    private Tipopersonal tipopersonal;
    private Tipopersonal tipopersonalcombos;
    private TipopersonalDAO tipopersonalDAO;
    private List<Tipopersonal> listatipopersonal;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.tipopersonal = new Tipopersonal();
        this.tipopersonalDAO = new TipopersonalDAO();
        this.listatipopersonal = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
    }

    public String eliminarTipopersonal(Tipopersonal tipopersonal){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = tipopersonalDAO.EliminarTipopersonalId(session, tipopersonal);
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
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
    
    public void cargarCombosAutor(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.tipopersonal.setIdTipoPersonal(this.tipopersonalcombos.getIdTipoPersonal());
            this.tipopersonal.setNombre(this.tipopersonalcombos.getNombre());
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
    
    public String registrarTipopersonal(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = tipopersonalDAO.ContadorRegistroTipopersonal(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.tipopersonal.setIdTipoPersonal(countReg + 1);
            } else {
                this.tipopersonal.setIdTipoPersonal(1);
            }
            respuesta = tipopersonalDAO.RegistrarTipopersonal(this.tipopersonal);
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
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
    
    public String limpiarcajas() {
        this.tipopersonal=null;
        this.tipopersonalcombos = null;
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
    
    public List<Tipopersonal> listadoTipopersonal(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listatipopersonal = tipopersonalDAO.ListadoTipopersonalTodos(this.session);
            this.transaction.commit();
            return this.listatipopersonal;
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
    
    public String actualizarTipopersonal(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            tipopersonalDAO.ActualizarTipopersonal(session, this.tipopersonal);
            this.transaction.commit();

            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.TRUE;

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
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
    
    
    public Tipopersonal getTipopersonal() {
        return tipopersonal;
    }

    public void setTipopersonal(Tipopersonal tipopersonal) {
        this.tipopersonal = tipopersonal;
    }

    public Tipopersonal getTipopersonalcombos() {
        return tipopersonalcombos;
    }

    public void setTipopersonalcombos(Tipopersonal tipopersonalcombos) {
        this.tipopersonalcombos = tipopersonalcombos;
    }

    public TipopersonalDAO getTipopersonalDAO() {
        return tipopersonalDAO;
    }

    public void setTipopersonalDAO(TipopersonalDAO tipopersonalDAO) {
        this.tipopersonalDAO = tipopersonalDAO;
    }

    public List<Tipopersonal> getListatipopersonal() {
        return listatipopersonal;
    }

    public void setListatipopersonal(List<Tipopersonal> listatipopersonal) {
        this.listatipopersonal = listatipopersonal;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }
}
