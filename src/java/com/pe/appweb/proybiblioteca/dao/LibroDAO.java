/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Libro;
import com.pe.appweb.proybiblioteca.service.LibroService;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author RAUL
 */
public class LibroDAO implements LibroService{
    private Session session;

    @Override
    public Libro ListadoLibroId(Session session,int idlibro) {
        return (Libro) session.get(Libro.class, idlibro);
    }


}
