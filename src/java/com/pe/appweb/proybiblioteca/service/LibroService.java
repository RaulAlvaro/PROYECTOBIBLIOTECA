/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Libro;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface LibroService {
    public abstract String RegistroLibro(Libro libro);
    public abstract List<Libro> ListadoLibrosTodos();
    public abstract Libro ListadoLibroId(int id);
    public abstract String ActualizarLibro(Libro libro);
    public abstract String EliminarLibro(Libro libro);

    
}
