/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.PersonalDAO;
import com.pe.appweb.proybiblioteca.dao.TipopersonalDAO;
import com.pe.appweb.proybiblioteca.entidades.Personal;
import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
import com.pe.appweb.proybiblioteca.util.MensajeSYSUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class MantPersonalMBR extends MensajeSYSUtils implements Serializable {

private Personal personal;
    private Personal personalcombos;
    private PersonalDAO personalDAO;
    private List<Personal> listapersonal;
    private boolean insert;
    
    private int idTipoPersonal;
    private TipopersonalDAO tipopersonalDAO;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.personal = new Personal();
        this.personalDAO = new PersonalDAO();
        this.listapersonal = new ArrayList();
        this.personalcombos = new Personal();
        this.insert = true;
        this.tipopersonalDAO = new TipopersonalDAO();
    }

    private void initlistDep() {
        listadoPersonal();
    }

    public String registrarPersonal() {
        try {
            String respuesta;

            this.personal.setIdPersonal(0);
            personal.setIdTipopersonal(this.idTipoPersonal);
            respuesta = personalDAO.RegistrarPersonal(this.personal);
            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del personal");
            } else {
                messageError("NO Se realizo la creación del personal");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public String limpiarcajas() {
        this.personal=null;
        this.personalcombos = null;
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public void listadoPersonal(){
        try {
            
            this.listapersonal = personalDAO.ListadoPersonalTodos();
            //return this.listalibros;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
    
    public String actualizarPersonal(){
        try {
            personal.setIdTipopersonal(this.idTipoPersonal);
            personalDAO.ActualizarPersonal(this.personal);
            this.idTipoPersonal = 0;

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public void cargarCombosPersonal(){
        try {
            //CARGAR COMBOS
            this.personal.setIdPersonal(this.personalcombos.getIdPersonal());
            this.personal.setNombre(this.personalcombos.getNombre());
            this.personal.setApellido(this.personalcombos.getApellido());
            this.personal.setTelefono(this.personalcombos.getTelefono());
            this.personal.setCorreo(this.personalcombos.getCorreo());
            this.personal.setSexo(this.personalcombos.getSexo());
            //this.personal.setIdTipopersonal(this.personalcombos.getIdTipopersonal());
            idTipoPersonal = this.personalcombos.getIdTipopersonal();
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());  
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        } 
    }
    
    
    public String eliminarPersonal(Personal personal){
        String respuesta;
        try {
            respuesta = personalDAO.EliminarPersonalId(personal);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del personal");
            } else {
                messageError("NO Se realizo la eliminación del personal");
            }
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        }
        return "/MANTENIMIENTOS/FrmMantPersonal";
    }
    
    public String buscaPersonalxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Personal buscarpersonal = new Personal();
            buscarpersonal = this.personalDAO.ListadoPersonalId(id);
            String nombrepersonal = buscarpersonal.getNombre();
            String apellidopersonal = buscarpersonal.getNombre();
            String telefonopersonal = buscarpersonal.getNombre();
            String correopersonal = buscarpersonal.getNombre();
            String sexopersonal = buscarpersonal.getNombre();
            String cadena = nombrepersonal+apellidopersonal+telefonopersonal+correopersonal+sexopersonal;
            return cadena; 
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

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getIdTipoPersonal() {
        return idTipoPersonal;
    }

    public void setIdTipoPersonal(int idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
    }

    public TipopersonalDAO getTipopersonalDAO() {
        return tipopersonalDAO;
    }

    public void setTipopersonalDAO(TipopersonalDAO tipopersonalDAO) {
        this.tipopersonalDAO = tipopersonalDAO;
    }
    
    
}
