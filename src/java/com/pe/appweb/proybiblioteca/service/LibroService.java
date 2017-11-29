/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Libro;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author RAUL
 */
public interface LibroService {
    public abstract boolean RegistroLibro(Libro libro);
    public abstract List<Libro> ListadoLibrosTodos(Session session);
    public abstract Libro ListadoLibroId(Session session,int id);
    public abstract boolean ActualizarLibro(Session session, Libro libro);
    public abstract boolean EliminarLibro(Session session, Libro libro);
    public abstract Integer ContadorRegistroLibro(Session session);

    
}
