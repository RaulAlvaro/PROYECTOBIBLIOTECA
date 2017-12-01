/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Tipopersonal;
import com.pe.appweb.proybiblioteca.service.TipoPersonalService;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TipopersonalDAO implements TipoPersonalService{

private AccesoDB db;

public TipopersonalDAO() {
        db = new AccesoDB();
}

@Override
public String RegistrarTipopersonal(Tipopersonal tipo) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertartipopersonal(?,?)}");
        if(cn!=null) {
                try{
                        CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                        cs.setInt(1, tipo.getIdTipoPersonal());
                        cs.setString(2, tipo.getNombre());
                        int inserto = cs.executeUpdate();
                        if(inserto==0) {
                                rpta="Error";
                        }
                        else{
                                rpta="Registrado";
                        }

                }catch(SQLException ex) {rpta=ex.getMessage();}
                finally{
                        try{
                                cn.close();
                        }catch(SQLException e) {
                                rpta = e.getMessage();
                        }
                }
        }
        return rpta;
}

@Override
public Tipopersonal ListadoTipopersonalId(int idTipopersonal) {
        Tipopersonal tipo = new Tipopersonal();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datostipopersonalporid(?)}";
        if(cn!=null) {
                try{
                        CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                        cs.setInt(1, idTipopersonal);
                        ResultSet rs = cs.executeQuery();
                        if(rs.next()) {
                                tipo.setIdTipoPersonal(rs.getInt("idTipoPersonal"));
                                tipo.setNombre(rs.getString("nombre"));
                        }
                }catch(SQLException ex) {}
                finally {
                        try{
                                cn.close();
                        }catch(SQLException e) {
                        }
                }
        }
        return tipo;
}

@Override
public List<Tipopersonal> ListadoTipopersonalTodos() {
        List<Tipopersonal> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listartipopersonal()}";
        Connection cn = db.getConnection();
        if(cn!=null) {
                try{
                        CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                        ResultSet rs = cs.executeQuery();
                        while(rs.next()) {
                                Tipopersonal tipo = new Tipopersonal();
                                tipo.setIdTipoPersonal(rs.getInt("idTipoPersonal"));
                                tipo.setNombre(rs.getString("nombre"));
                                lista.add(tipo);
                        }
                }catch(SQLException ex) {}
                finally{
                        try{
                                cn.close();
                        }catch (SQLException e) {

                        }
                }
        }
        return lista;
}

@Override
public String ActualizarTipopersonal(Tipopersonal tipo) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizartipopersonal(?,?)}";
        if(cn!=null) {
                try{
                        CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                        cs.setInt(1, tipo.getIdTipoPersonal());
                        cs.setString(2, tipo.getNombre());
                        int inserto = cs.executeUpdate();

                        if(inserto==0) {
                                rpta="Error";
                        }

                }catch(SQLException ex) {rpta=ex.getMessage();}
                finally{
                        try{
                                cn.close();
                        }catch(SQLException e) {
                                rpta = e.getMessage();
                        }
                }
        }
        return rpta;
}

@Override
public String EliminarTipopersonalId(Tipopersonal tipo) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminartipopersonal(?)}";
        if(cn!=null) {
                try{
                        CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                        cs.setInt(1, tipo.getIdTipoPersonal());
                        cs.executeUpdate();
                        rpta="correcto";
                }catch(SQLException ex) {rpta=ex.getMessage();}
                finally{
                        try{
                                cn.close();
                        }catch(SQLException e) {
                                rpta = e.getMessage();
                        }
                }
        }
        return rpta;
}


}
