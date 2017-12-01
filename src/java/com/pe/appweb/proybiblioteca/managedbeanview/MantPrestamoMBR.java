/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.managedbeanview;

import com.pe.appweb.proybiblioteca.dao.CopiaDAO;
import com.pe.appweb.proybiblioteca.dao.LectorDAO;
import com.pe.appweb.proybiblioteca.dao.MultaDAO;
import com.pe.appweb.proybiblioteca.dao.PersonalDAO;
import com.pe.appweb.proybiblioteca.dao.PrestamoDAO;
import com.pe.appweb.proybiblioteca.entidades.Multa;
import com.pe.appweb.proybiblioteca.entidades.Prestamo;
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
public class MantPrestamoMBR extends MensajeSYSUtils implements Serializable{
    
    private Prestamo prestamo;
    private Prestamo prestamocombos;
    private PrestamoDAO prestamoDAO;
    private List<Prestamo> listaprestamo;
    private Boolean insert;
    
    private PersonalDAO personalDAO;
    private int idPersonal;
    private CopiaDAO copiaDAO;
    private int idCopia;
    private LectorDAO lectorDAO;
    private int idLector;
    private MultaDAO multaDAO;
    private int idMulta;

    @PostConstruct
    private void init() {
        initInstancia();
        initlistDep();
    }

    private void initInstancia() {
        this.prestamo = new Prestamo();
        this.prestamoDAO = new PrestamoDAO();
        this.listaprestamo = new ArrayList();
        this.insert = true;
    }

    private void initlistDep() {
        listadoPrestamo();
    }
    
    public String buscaPrestamoxId(int id){
            //this.musu= usudao.ListadoUsuarioxId(this.session,id);
            Prestamo buscaprestamo = new Prestamo();
            buscaprestamo = this.prestamoDAO.ListadoPrestamoId(id);
            Date ini = buscaprestamo.getInicio();
            Date f = buscaprestamo.getFin();
            int idPres = buscaprestamo.getIdPrestamo();
            String cadini= buscaprestamo.getCadinicio();
            String cadf= buscaprestamo.getCadfin();
            int idLec= buscaprestamo.getIdLector();
            int idPer= buscaprestamo.getIdPersonal();
            int idCop= buscaprestamo.getIdCopia();
            boolean dev= buscaprestamo.isDevuelto();
            boolean agr= buscaprestamo.isAgregado();
            int idMul= buscaprestamo.getIdMulta();
            String cadena = ini.toString() + f.toString() + String.valueOf(idPres) + 
                    cadini + cadf+String.valueOf(idLec)+String.valueOf(idPer)+
                    String.valueOf(idCop)+dev+agr+String.valueOf(idMul);
            return cadena; 
    }
    
    
    
    public String eliminarPrestamo(Prestamo prestamo){
        String respuesta;
        try {
            respuesta = prestamoDAO.EliminarPrestamoId(prestamo);
            if (respuesta.equals("correcto")) {
                messageInfo("Se realizo la elminación del prestamo");
            } else {
                messageError("NO Se realizo la eliminación del prestamo");
            }
        } catch (Exception ex) {   
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantPrestamo";
    }
    
    public void cargarCombosPrestamo(){
        try {
            //CARGAR COMBOS
            this.prestamo.setIdMulta(this.prestamocombos.getIdMulta());
            this.prestamo.setInicio(this.prestamocombos.getInicio());
            this.prestamo.setFin(this.prestamocombos.getFin());
            this.prestamo.setAgregado(this.prestamocombos.isAgregado());
            this.prestamo.setDevuelto(this.prestamocombos.isDevuelto());
            this.prestamo.setCadfin(this.prestamocombos.getCadfin());
            this.prestamo.setCadinicio(this.prestamocombos.getCadinicio());
            this.prestamo.setIdCopia(this.prestamocombos.getIdCopia());
            this.prestamo.setIdLector(this.prestamocombos.getIdLector());
            this.prestamo.setIdMulta(this.prestamocombos.getIdMulta());
            this.prestamo.setIdPersonal(this.prestamocombos.getIdPersonal());
            this.insert = Boolean.FALSE;

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());

        } 
    }
    
    public String registrarPrestamo(){
        try {
            String respuesta;
            int idCargo = 0;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.prestamo.setIdMulta(0);
            //this.multa.setInicio(LocalDate.parse(multa.getCadinicio(), form));
            java.util.Date parsed1 = format.parse(prestamo.getCadinicio());
            
            Date sql1 = new java.sql.Date(parsed1.getTime());
            this.prestamo.setInicio(sql1); 
            java.util.Date parsed2 = format.parse(prestamo.getCadfin());
            
            Date sql2 = new java.sql.Date(parsed2.getTime());
            this.prestamo.setFin(sql2);
            respuesta = prestamoDAO.RegistrarPrestamo(this.prestamo);

            if (respuesta.equals("Registrado")) {
                messageInfo("Se realizo la creación de la multa");
            } else {
                messageError("NO Se realizo la creación de la multa");
            }
        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
            return null;
        }
        return "/MANTENIMIENTOS/FrmMantPrestamo";
    }
    
    public String limpiarcajas() {
        this.prestamo=null;
        this.prestamocombos = null;
        return "/MANTENIMIENTOS/FrmMantPrestamo";
    }
    
    public void listadoPrestamo(){
        try {
            this.listaprestamo = prestamoDAO.ListadoPrestamoTodos();
        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
    }
    
    public String actualizarPrestamo(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = format.parse(prestamo.getCadinicio());
            Date sql1 = new java.sql.Date(parsed1.getTime());
            this.prestamo.setInicio(sql1); 
            java.util.Date parsed2 = format.parse(prestamo.getCadfin());
            Date sql2 = new java.sql.Date(parsed2.getTime());
            this.prestamo.setFin(sql2);
            
            prestamoDAO.ActualizarPrestamo(this.prestamo);
            messageInfo("Correcto: Los cambios fueron guardados correctamente");

            insert = Boolean.FALSE;

        } catch (Exception ex) {
            messageFatal("Error Fatal: Por favor contacte con su administrador" + ex.getMessage());
        }
        return "/MANTENIMIENTOS/FrmMantPrestamo";
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Prestamo getPrestamocombos() {
        return prestamocombos;
    }

    public void setPrestamocombos(Prestamo prestamocombos) {
        this.prestamocombos = prestamocombos;
    }

    public PrestamoDAO getPrestamoDAO() {
        return prestamoDAO;
    }

    public void setPrestamoDAO(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public List<Prestamo> getListaprestamo() {
        return listaprestamo;
    }

    public void setListaprestamo(List<Prestamo> listaprestamo) {
        this.listaprestamo = listaprestamo;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    
    
}
