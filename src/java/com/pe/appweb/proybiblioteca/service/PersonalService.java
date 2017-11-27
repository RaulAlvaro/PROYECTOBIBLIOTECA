/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Personal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public interface PersonalService {
    
    public abstract boolean RegistrarPersonal(Personal personal);
    public abstract List<Personal> ListadoPersonalTodos(Session session);
    public abstract boolean ActualizarPersonal(Session session, Personal personal);
    public abstract Integer ContadorRegistroPersonal(Session session);
    public boolean EliminarPersonalId(Session session, Personal personal);
    
}
