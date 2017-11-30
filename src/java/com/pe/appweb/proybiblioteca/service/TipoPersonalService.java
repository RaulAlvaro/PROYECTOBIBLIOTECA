/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface TipoPersonalService {
    
    public abstract String RegistrarTipopersonal(Tipopersonal tipo);
    public abstract List<Tipopersonal> ListadoTipopersonalTodos();
    public abstract String ActualizarTipopersonal(Tipopersonal tipo);
    public abstract String EliminarTipopersonalId(Tipopersonal tipo);

}
