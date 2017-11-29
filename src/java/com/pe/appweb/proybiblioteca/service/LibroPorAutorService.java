/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Libroporautor;
import org.hibernate.Session;

/**
 *
 * @author RAUL
 */
public interface LibroPorAutorService {
    public boolean RegistrarLibroPorAutor(Libroporautor libroPorAutor);
    public abstract Integer ContadorRegistroTipo(Session session);

}
