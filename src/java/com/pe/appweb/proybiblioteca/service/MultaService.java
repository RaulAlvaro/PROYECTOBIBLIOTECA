/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Multa;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public interface MultaService {
    
    public abstract String RegistrarMulta(Multa multa);
    public abstract List<Multa> ListadoMultaTodos();
    public abstract Multa ListadoMultaId(int idMulta);
    public abstract String ActualizarMulta(Multa multa);
    public abstract String EliminarMultaId(Multa multa);
    
}
