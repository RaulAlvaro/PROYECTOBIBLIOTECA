package com.pe.appweb.proybiblioteca.entidades;
// Generated 29/11/2017 02:28:28 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Lector generated by hbm2java
 */
public class Lector  implements java.io.Serializable {


     private int codigo;
     private String nombres;
     private String apellidos;
     private int telefono;
     private String sexo;
     private boolean estado;
     private int strikes;
     private Set prestamos = new HashSet(0);
     private Set multas = new HashSet(0);

    public Lector() {
    }

	
    public Lector(int codigo, String nombres, String apellidos, int telefono, boolean estado, int strikes) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.estado = estado;
        this.strikes = strikes;
    }
    public Lector(int codigo, String nombres, String apellidos, int telefono, String sexo, boolean estado, int strikes, Set prestamos, Set multas) {
       this.codigo = codigo;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.telefono = telefono;
       this.sexo = sexo;
       this.estado = estado;
       this.strikes = strikes;
       this.prestamos = prestamos;
       this.multas = multas;
    }
   
    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public boolean isEstado() {
        return this.estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public int getStrikes() {
        return this.strikes;
    }
    
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }
    public Set getPrestamos() {
        return this.prestamos;
    }
    
    public void setPrestamos(Set prestamos) {
        this.prestamos = prestamos;
    }
    public Set getMultas() {
        return this.multas;
    }
    
    public void setMultas(Set multas) {
        this.multas = multas;
    }




}


