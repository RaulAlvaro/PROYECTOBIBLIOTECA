/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Tipo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author RAUL
 */
public interface TipoService {
    
    public abstract boolean RegistrarTipo(Tipo tipo);
    public abstract Tipo ListadoTipoId(Session session,int idTipo);
    public abstract List<Tipo> ListadoTipoTodos(Session session);
    public abstract boolean ActualizarTipo(Session session, Tipo tipo);
    public abstract Integer ContadorRegistroTipo(Session session);
    public boolean EliminarTipoId(Session session, Tipo tipo);
    
}
