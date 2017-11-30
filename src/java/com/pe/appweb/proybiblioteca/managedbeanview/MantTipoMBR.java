/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.TipoDAO;
import com.pe.appweb.proybiblioteca.entidades.Tipo;
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
public class MantTipoMBR extends MensajeSYSUtils implements Serializable{
        
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
        listadoTipo();
    }
    
    public String buscaTipoxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Tipo buscatipo = new Tipo();
            buscatipo = this.tipoDAO.ListadoTipoId(id);
            String nombretipo = buscatipo.getTipo();
            return nombretipo; 
    }
    
    
    
    public String eliminarTipo(Tipo tipo){
        String respuesta;
        try {
            respuesta = tipoDAO.EliminarTipoId(tipo);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del Tipo");
            } else {
                messageError("NO Se realizo la eliminación del Tipo");
            }
        } catch (Exception ex) {   
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public void cargarCombosAutor(){
        try {
            //CARGAR COMBOS
            this.tipo.setIdTipo(this.tipocombos.getIdTipo());
            this.tipo.setTipo(this.tipocombos.getTipo());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } 
    }
    
    public String registrarTipo(){
        try {
            String respuesta;
            int idCargo = 0;
            
            this.tipo.setIdTipo(0);
            respuesta = tipoDAO.RegistrarTipo(this.tipo);

            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del Autor");
            } else {
                messageError("NO Se realizo la creación del Autor");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public String limpiarcajas() {
        this.tipo=null;
        this.tipocombos = null;
        return "/MANTENIMIENTOS/FrmMantTipo";
    }
    
    public void listadoTipo(){
        try {
            this.listatipo = tipoDAO.ListadoTipoTodos();
            //return this.listatipo;
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            //return null;
        }
    }
    
    public String actualizarTipo(){
        try {
            tipoDAO.ActualizarTipo(this.tipo);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
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
