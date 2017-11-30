/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Personal;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PersonalService {
    
    public abstract String RegistrarPersonal(Personal personal);
    public abstract List<Personal> ListadoPersonalTodos();
    public abstract Personal ListadoPersonalId(int idPersonal);
    public abstract String ActualizarPersonal(Personal personal);
    public abstract String EliminarPersonalId(Personal personal);
    
}
