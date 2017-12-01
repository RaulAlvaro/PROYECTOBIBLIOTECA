/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.TipopersonalDAO;
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
public class MantTipopersonalMBR extends MensajeSYSUtils implements Serializable{
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
        listadoTipopersonal();
    }
  
  /* public String buscaTipoxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Autor buscaautor = new Autor();
            buscaautor = this.autorDAO.ListadoAutorId(id);
            String nombretipo = buscatipo.getTipo();
            return nombretipo;
    }*/
  
  
  
    public String eliminarTipopersonal(Tipopersonal tipopersonal){
        String respuesta;
        try {
            respuesta = tipopersonalDAO.EliminarTipopersonalId(tipopersonal);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del Tipo de personal");
            } else {
                messageError("NO Se realizo la eliminación del Tipo de personal");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
  
    public void cargarCombosTipopersonal(){
        try {
            //CARGAR COMBOS
            this.tipopersonal.setIdTipoPersonal(this.tipopersonalcombos.getIdTipoPersonal());
            this.tipopersonal.setNombre(this.tipopersonalcombos.getNombre());
            this.insert = Boolean.FALSE;
  
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
  
        }
    }
  
    public String registrarTipopersonal(){
        try {
            String respuesta;
            int idCargo = 0;
  
            this.tipopersonal.setIdTipoPersonal(0);
            respuesta = tipopersonalDAO.RegistrarTipopersonal(this.tipopersonal);
  
            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del Tipo de personal");
            } else {
                messageError("NO Se realizo la creación del Tipo de personal");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
  
    public String limpiarcajas() {
        this.tipopersonal=null;
        this.tipopersonalcombos = null;
        return "/MANTENIMIENTOS/FrmMantTipopersonal";
    }
  
    public void listadoTipopersonal(){
        try {
            this.listatipopersonal = tipopersonalDAO.ListadoTipopersonalTodos();
            //return this.listatipo;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
  
    public String actualizarTipopersonal(){
        try {
            tipopersonalDAO.ActualizarTipopersonal(this.tipopersonal);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");
  
            insert = Boolean.FALSE;
  
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
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
