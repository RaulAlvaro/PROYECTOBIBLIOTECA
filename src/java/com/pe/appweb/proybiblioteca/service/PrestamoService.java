/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Prestamo;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PrestamoService {
    
    public abstract String RegistrarPrestamo(Prestamo prestamo);
    public abstract List<Prestamo> ListadoPrestamoTodos();
    public abstract Prestamo ListadoPrestamoId(int idPrestamo);
    public abstract String ActualizarPrestamo(Prestamo prestamo);
    public abstract String EliminarPrestamoId(Prestamo prestamo);
}
