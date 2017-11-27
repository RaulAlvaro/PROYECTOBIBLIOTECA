/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Autor;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.pe.appweb.proybiblioteca.service.AutorService;

/**
 *
 * @author RAUL
 */
public class AutorDAO implements AutorService{

    private Session session;
    
    @Override
    public boolean RegistrarAutor(Autor autor) {
        boolean resp = false;
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(autor);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }
    
    @Override
    public Autor ListadoAutorId(Session session, int idAutor) {
         return (Autor) session.get(Autor.class, idAutor);
    }
    
    
    @Override
    public boolean EliminarAutorID(Session session, Autor autor) {
        boolean resp = false;
        try {
            session.delete(autor);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }
    
    @Override
    public List<Autor> ListadoAutorTodos(Session session) {
        String hql = " from Autor";        
        Query query = session.createQuery(hql);
        List<Autor> listaautor= (List<Autor>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listaautor;
    }
    
    
    @Override
    public boolean ActualizarAutor(Session session, Autor autor) {
        boolean resp = false;
        try {
            session.update(autor);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroAutor(Session session) {
        String hql = "select count(*) from Autor";
        Query query = session.createQuery(hql);
        Long FilasTab=(Long) query.uniqueResult();
        Integer cont=FilasTab.intValue();
        return cont;
    }

}
