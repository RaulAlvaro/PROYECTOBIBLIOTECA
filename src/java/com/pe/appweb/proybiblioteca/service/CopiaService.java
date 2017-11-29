/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Copia;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author RAUL
 */
public interface CopiaService {
    public abstract boolean RegistraCopia(Copia copia);
    public abstract List<Copia> ListadoLibrosTodos(Session session);
    public abstract Copia ListadoCopiaId(Session session,int idCopia);
    public abstract boolean EliminarCopia(Session session, Copia copia);
    public abstract Integer ContadorRegistroCopia(Session session);

}
