/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.LectorDAO;
import com.pe.appweb.proybiblioteca.entidades.Lector;
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
public class MantLectorMBR extends MensajeSYSUtils implements Serializable{

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
        listadoLector();
    }
    
    public String buscaLectorxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Lector buscalector = new Lector();
            buscalector = this.lectorDAO.ListadoLectorId(id);
            String nombreslector = buscalector.getNombres();
            String apellidoslector = buscalector.getApellidos();
            int telefonolector = buscalector.getTelefono();
            String sexolector = buscalector.getSexo();
            int strikeslector = buscalector.getStrikes();
            int estadolector = buscalector.getEstado();
            String cadena = nombreslector + apellidoslector + String.valueOf(telefonolector) + sexolector + String.valueOf(strikeslector) + String.valueOf(estadolector);
            return cadena; 
    }
    
    
    
    public String eliminarLector(Lector lector){
        String respuesta;
        try {
            respuesta = lectorDAO.EliminarLectorID(lector);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del lector");
            } else {
                messageError("NO Se realizo la eliminación del lector");
            }
        } catch (Exception ex) {   
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantLector";
    }
    
    public void cargarCombosLector(){
        try {
            //CARGAR COMBOS
            this.lector.setCodigo(this.lectorcombos.getCodigo());
            this.lector.setNombres(this.lectorcombos.getNombres());
            this.lector.setApellidos(this.lectorcombos.getApellidos());
            this.lector.setTelefono(this.lectorcombos.getTelefono());
            this.lector.setSexo(this.lectorcombos.getSexo());
            this.lector.setEstado(this.lectorcombos.getEstado());
            this.lector.setStrikes(this.lectorcombos.getStrikes());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } 
    }
    
    public String registrarLector(){
        try {
            String respuesta;
            int idCargo = 0;
            
            this.lector.setCodigo(0);
            respuesta = lectorDAO.RegistrarLector(this.lector);

            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación del lector");
            } else {
                messageError("NO Se realizo la creación del lector");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantLector";
    }
    
    public String limpiarcajas() {
        this.lector=null;
        this.lectorcombos = null;
        return "/MANTENIMIENTOS/FrmMantLector";
    }
    
    public void listadoLector(){
        try {
            this.listalector = lectorDAO.ListadoLectorTodos();
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
    }
    
    public String actualizarLector(){
        try {
            lectorDAO.ActualizarLector(this.lector);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
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
