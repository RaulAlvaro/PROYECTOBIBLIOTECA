/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Libroporautor;
import com.pe.appweb.proybiblioteca.service.LibroPorAutorService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RAUL
 */
public class LibroporautorDAO implements LibroPorAutorService{
    Session session;
    
    @Override
    public boolean RegistrarLibroPorAutor(Libroporautor libroPorAutor) {
        boolean resp = false;
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(libroPorAutor);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        
        return resp;
    }
    
    @Override
    public Integer ContadorRegistroTipo(Session session) {
        String hql = "select count(*) from Libroporautor";
        Query query = session.createQuery(hql);
        Long FilasTab = (Long) query.uniqueResult();
        Integer cont = FilasTab.intValue();
        return cont;
    }
    
}
