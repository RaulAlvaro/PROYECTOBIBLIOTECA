/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
import com.pe.appweb.proybiblioteca.service.TipoPersonalService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class TipopersonalDAO implements TipoPersonalService{
    
    private Session session;

    @Override
    public boolean RegistrarTipopersonal(Tipopersonal tipopersonal) {
        boolean resp = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(tipopersonal);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }

    @Override
    public List<Tipopersonal> ListadoTipopersonalTodos(Session session) {
        String hql = " from Tipopersonal";
        Query query = session.createQuery(hql);
        List<Tipopersonal> listatipopersonal = (List<Tipopersonal>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listatipopersonal;
    }

    @Override
    public boolean ActualizarTipopersonal(Session session, Tipopersonal tipopersonal) {
        boolean resp = false;
        try {
            session.update(tipopersonal);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroTipopersonal(Session session) {
        String hql = "select count(*) from Tipopersonal";
        Query query = session.createQuery(hql);
        Long FilasTab = (Long) query.uniqueResult();
        Integer cont = FilasTab.intValue();
        return cont;
    }

    @Override
    public boolean EliminarTipopersonalId(Session session, Tipopersonal tipopersonal) {
        boolean resp = false;
        try {
            session.delete(tipopersonal);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Tipopersonal BuscarTipoPersonalId(Session session, int idTipopersonal) {
        return (Tipopersonal) session.get(Tipopersonal.class, idTipopersonal);
    }
    
    
}
