/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.PersonalDAO;
import com.pe.appweb.proybiblioteca.entidades.Personal;
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
public class MantPersonalMBR extends MensajeSYSUtils implements Serializable{
    
    Session session;
    Transaction transaction;
    
    private Tipopersonal tipopersonal;
    private Personal personal;
    private Personal personalcombos;
    private PersonalDAO personalDAO;
    private List<Personal> listapersonal;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.tipopersonal = new Tipopersonal();
        this.personal = new Personal();
        this.personalDAO = new PersonalDAO();
        this.listapersonal = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
    }

    public String eliminarPersonal(Personal personal){
        this.session = null;
        this.transaction = null;
        boolean respuesta;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            respuesta = personalDAO.EliminarPersonalId(session, personal);
            this.transaction.commit();
            if (respuesta) {
                messageInfo("Se realizo la elminación del Personal");
            } else {
                messageError("NO Se realizo la eliminación del Personal");
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
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public void cargarCombosAutor(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            //CARGAR COMBOS
            this.personal.setIdPersonal(this.personalcombos.getIdPersonal());
            this.personal.setApellido(this.personalcombos.getApellido());
            this.personal.setNombre(this.personalcombos.getNombre());
            this.personal.setCorreo(this.personalcombos.getCorreo());
            this.personal.setSexo(this.personalcombos.getSexo());
            this.personal.setTipopersonal(this.personalcombos.getTipopersonal());
            this.personal.setTelefono(this.personalcombos.getTelefono());
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
    
    public String registrarPersonal(){
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            boolean respuesta;

            int countReg = personalDAO.ContadorRegistroPersonal(session);
            int idCargo = 0;
            if (countReg != 0) {
                this.personal.setIdPersonal(countReg + 1);
            } else {
                this.personal.setIdPersonal(1);
            }
            respuesta = personalDAO.RegistrarPersonal(this.personal);
            this.transaction.commit();

            if (respuesta) {
                messageInfo("Se realizo la creación del Personal");
            } else {
                messageError("NO Se realizo la creación del Personal");
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
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public String limpiarcajas() {
        this.personal=null;
        this.personalcombos = null;
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public List<Personal> listadoPersonal(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listapersonal = personalDAO.ListadoPersonalTodos(this.session);
            this.transaction.commit();
            return this.listapersonal;
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
    
    public String actualizarPersonal(){
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            personalDAO.ActualizarPersonal(session, this.personal);
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
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Personal getPersonalcombos() {
        return personalcombos;
    }

    public void setPersonalcombos(Personal personalcombos) {
        this.personalcombos = personalcombos;
    }

    public PersonalDAO getPersonalDAO() {
        return personalDAO;
    }

    public void setPersonalDAO(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    public List<Personal> getListapersonal() {
        return listapersonal;
    }

    public void setListapersonal(List<Personal> listapersonal) {
        this.listapersonal = listapersonal;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public Tipopersonal getTipopersonal() {
        return tipopersonal;
    }

    public void setTipopersonal(Tipopersonal tipopersonal) {
        this.tipopersonal = tipopersonal;
    }
    
    
}
