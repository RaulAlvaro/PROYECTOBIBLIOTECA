/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public interface TipoPersonalService {
    
    public abstract boolean RegistrarTipopersonal(Tipopersonal tipo);
    public abstract List<Tipopersonal> ListadoTipopersonalTodos(Session session);
    public abstract boolean ActualizarTipopersonal(Session session, Tipopersonal tipo);
    public abstract Integer ContadorRegistroTipopersonal(Session session);
    public boolean EliminarTipopersonalId(Session session, Tipopersonal tipo);
    public abstract Tipopersonal BuscarTipoPersonalId(Session session, int idLibro);

}
