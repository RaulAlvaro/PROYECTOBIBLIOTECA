/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Lector;
import com.pe.appweb.proybiblioteca.service.LectorService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class LectorDAO implements LectorService {
    
    private Session session;
    
    @Override
    public boolean RegistrarLector(Lector lector) {
        boolean resp = false;
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(lector);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }
    
    @Override
    public Lector ListadoLectorId(Session session, int idLector) {
         return (Lector) session.get(Lector.class, idLector);
    }
    
    
    @Override
    public boolean EliminarLectorID(Session session, Lector lector) {
        boolean resp = false;
        try {
            session.delete(lector);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }
    
    @Override
    public List<Lector> ListadoLectorTodos(Session session) {
        String hql = " from Lector";        
        Query query = session.createQuery(hql);
        List<Lector> listalector= (List<Lector>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listalector;
    }
    
    
    @Override
    public boolean ActualizarLector(Session session, Lector lector) {
        boolean resp = false;
        try {
            session.update(lector);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroLector(Session session) {
        String hql = "select count(*) from Lector";
        Query query = session.createQuery(hql);
        Long FilasTab=(Long) query.uniqueResult();
        Integer cont=FilasTab.intValue();
        return cont;
    }
}
