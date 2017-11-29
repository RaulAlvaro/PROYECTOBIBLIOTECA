/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Libro;
import com.pe.appweb.proybiblioteca.service.LibroService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RAUL
 */
public class LibroDAO implements LibroService{
    private Session session;
    
    @Override
    public Libro ListadoLibroId(Session session, int idLibro) {
       return (Libro) session.get(Libro.class, idLibro);

    }
    
    @Override
    public boolean RegistroLibro(Libro libro) {
        boolean resp = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(libro);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }

    @Override
    public List<Libro> ListadoLibrosTodos(Session session) {
        String hql = " from Libro";
        Query query = session.createQuery(hql);
        List<Libro> listalibro = (List<Libro>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listalibro;
    }

    @Override
    public boolean ActualizarLibro(Session session,Libro libro) {
        boolean resp = false;
        try {
            session.update(libro);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public boolean EliminarLibro(Session session, Libro libro) {
        boolean resp = false;
        try {
            session.delete(libro);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroLibro(Session session) {
        String hql = "select count(*) from Libro";
        Query query = session.createQuery(hql);
        Long FilasTab = (Long) query.uniqueResult();
        Integer cont = FilasTab.intValue();
        return cont;
    }





}
