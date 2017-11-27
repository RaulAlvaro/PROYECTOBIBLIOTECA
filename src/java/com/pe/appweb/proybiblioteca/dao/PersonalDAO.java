/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Personal;
import com.pe.appweb.proybiblioteca.service.PersonalService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */

public class PersonalDAO implements PersonalService{
    private Session session;

    @Override
    public boolean RegistrarPersonal(Personal personal) {
        boolean resp = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(personal);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }

    @Override
    public List<Personal> ListadoPersonalTodos(Session session) {
        String hql = " from Personal";
        Query query = session.createQuery(hql);
        List<Personal> listapersonal = (List<Personal>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listapersonal;
    }

    @Override
    public boolean ActualizarPersonal(Session session, Personal personal) {
        boolean resp = false;
        try {
            session.update(personal);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroPersonal(Session session) {
        String hql = "select count(*) from Personal";
        Query query = session.createQuery(hql);
        Long FilasTab = (Long) query.uniqueResult();
        Integer cont = FilasTab.intValue();
        return cont;
    }

    @Override
    public boolean EliminarPersonalId(Session session, Personal personal) {
        boolean resp = false;
        try {
            session.delete(personal);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    
}
