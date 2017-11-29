/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Tipo;
import com.pe.appweb.proybiblioteca.service.TipoService;
import com.pe.appweb.proybiblioteca.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RAUL
 */
public class TipoDAO implements TipoService {

    private Session session;
    
    @Override
    public Tipo ListadoTipoId(Session session, int idTipo) {
        return (Tipo) session.get(Tipo.class, idTipo);
    }
    
    @Override
    public boolean RegistrarTipo(Tipo tipo) {
        boolean resp = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(tipo);
            transaction.commit();
            resp = true;
        } catch (Exception e) {
            session.close();
            resp = false;
        }
        return resp;
    }

    @Override
    public List<Tipo> ListadoTipoTodos(Session session) {
        String hql = " from Tipo";
        Query query = session.createQuery(hql);
        List<Tipo> listatipo = (List<Tipo>) query.list();
        //System.out.println("Total es: "+listaTCP.size());
        return listatipo;
    }

    @Override
    public boolean ActualizarTipo(Session session, Tipo tipo) {
        boolean resp = false;
        try {
            session.update(tipo);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }

    @Override
    public Integer ContadorRegistroTipo(Session session) {
        String hql = "select count(*) from Tipo";
        Query query = session.createQuery(hql);
        Long FilasTab = (Long) query.uniqueResult();
        Integer cont = FilasTab.intValue();
        return cont;
    }

    @Override
    public boolean EliminarTipoId(Session session, Tipo tipo) {
        boolean resp = false;
        try {
            session.delete(tipo);
            resp = true;
        } catch (Exception e) {
            resp = false;
        }
        return resp;
    }


}
