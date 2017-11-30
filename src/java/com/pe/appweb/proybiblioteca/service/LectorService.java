/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Lector;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface LectorService {
    public abstract String RegistrarLector(Lector lector);
    public abstract List<Lector> ListadoLectorTodos();
    public abstract Lector ListadoLectorId(int idLector);
    public abstract String ActualizarLector(Lector lector);
    public abstract String EliminarLectorID(Lector idLector);
}
