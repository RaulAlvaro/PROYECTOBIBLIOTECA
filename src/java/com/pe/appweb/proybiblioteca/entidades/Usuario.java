package com.pe.appweb.proybiblioteca.entidades;
// Generated 25/11/2017 05:12:29 PM by Hibernate Tools 4.3.1



/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idUsuario;
     private Personal personal;
     private String nombreusuario;
     private String claveusuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, Personal personal, String nombreusuario, String claveusuario) {
       this.idUsuario = idUsuario;
       this.personal = personal;
       this.nombreusuario = nombreusuario;
       this.claveusuario = claveusuario;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public String getNombreusuario() {
        return this.nombreusuario;
    }
    
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }
    public String getClaveusuario() {
        return this.claveusuario;
    }
    
    public void setClaveusuario(String claveusuario) {
        this.claveusuario = claveusuario;
    }




}


