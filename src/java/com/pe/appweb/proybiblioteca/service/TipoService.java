/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Tipo;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface TipoService {
    
    public abstract String RegistrarTipo(Tipo tipo);
    public abstract List<Tipo> ListadoTipoTodos();
    public abstract Tipo ListadoTipoId(int idTipo);
    public abstract String ActualizarTipo(Tipo tipo);
    public abstract String EliminarTipoId(Tipo tipo);
    
}
