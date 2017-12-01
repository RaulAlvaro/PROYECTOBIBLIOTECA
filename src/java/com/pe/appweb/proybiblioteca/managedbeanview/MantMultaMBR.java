/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.MultaDAO;
import com.pe.appweb.proybiblioteca.entidades.Multa;
import com.pe.appweb.proybiblioteca.util.MensajeSYSUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
public class MantMultaMBR extends MensajeSYSUtils implements Serializable{
    
    private Multa multa;
    private Multa multacombos;
    private MultaDAO multaDAO;
    private List<Multa> listamulta;
    private Boolean insert;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.multa = new Multa();
        this.multaDAO = new MultaDAO();
        this.listamulta = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
        listadoMulta();
    }
    
    public String buscaMultaxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Multa buscamulta = new Multa();
            buscamulta = this.multaDAO.ListadoMultaId(id);
            Date ini = buscamulta.getInicio();
            Date f = buscamulta.getFin();
            int mon = buscamulta.getMonto();
            String cadena = ini.toString() + f.toString() + mon;
            return cadena; 
    }
    
    
    
    public String eliminarMulta(Multa multa){
        String respuesta;
        try {
            respuesta = multaDAO.EliminarMultaId(multa);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación de la multa");
            } else {
                messageError("NO Se realizo la eliminación de la multa");
            }
        } catch (Exception ex) {   
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantMulta";
    }
    
    public void cargarCombosMulta(){
        try {
            //CARGAR COMBOS
            this.multa.setIdMulta(this.multacombos.getIdMulta());
            this.multa.setInicio(this.multacombos.getInicio());
            this.multa.setFin(this.multacombos.getFin());
            this.multa.setMonto(this.multacombos.getMonto());
            this.multa.setCadfin(this.multacombos.getCadfin());
            this.multa.setCadinicio(this.multacombos.getCadinicio());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } 
    }
    
    public String registrarMulta(){
        try {
            String respuesta;
            int idCargo = 0;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.multa.setIdMulta(0);
            //this.multa.setInicio(LocalDate.parse(multa.getCadinicio(), form));
            java.util.Date parsed1 = format.parse(multa.getCadinicio());
            
            Date sql1 = new java.sql.Date(parsed1.getTime());
            this.multa.setInicio(sql1); 
            java.util.Date parsed2 = format.parse(multa.getCadfin());
            
            Date sql2 = new java.sql.Date(parsed2.getTime());
            this.multa.setFin(sql2);
            respuesta = multaDAO.RegistrarMulta(this.multa);

            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación de la multa");
            } else {
                messageError("NO Se realizo la creación de la multa");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantMulta";
    }
    
    public String limpiarcajas() {
        this.multa=null;
        this.multacombos = null;
        return "/MANTENIMIENTOS/FrmMantMulta";
    }
    
    public void listadoMulta(){
        try {
            this.listamulta = multaDAO.ListadoMultaTodos();
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
    }
    
    public String actualizarMulta(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = format.parse(multa.getCadinicio());
            Date sql1 = new java.sql.Date(parsed1.getTime());
            this.multa.setInicio(sql1); 
            java.util.Date parsed2 = format.parse(multa.getCadfin());
            Date sql2 = new java.sql.Date(parsed2.getTime());
            this.multa.setFin(sql2);
            
            multaDAO.ActualizarMulta(this.multa);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantMulta";
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public Multa getMultacombos() {
        return multacombos;
    }

    public void setMultacombos(Multa multacombos) {
        this.multacombos = multacombos;
    }

    public MultaDAO getMultaDAO() {
        return multaDAO;
    }

    public void setMultaDAO(MultaDAO multaDAO) {
        this.multaDAO = multaDAO;
    }

    public List<Multa> getListamulta() {
        return listamulta;
    }

    public void setListamulta(List<Multa> listamulta) {
        this.listamulta = listamulta;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

}
