/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Lector;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Camilo
 */
public interface LectorService {
    public abstract boolean RegistrarLector(Lector lector);
    public abstract List<Lector> ListadoLectorTodos(Session session);
    public abstract Lector ListadoLectorId(Session session,int idLector);
    public abstract boolean ActualizarLector(Session session, Lector lector);
    public abstract Integer ContadorRegistroLector(Session session);
    public abstract boolean EliminarLectorID(Session session, Lector idLector);
}
