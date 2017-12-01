/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Multa;
import com.pe.appweb.proybiblioteca.service.MultaService;
import java.util.List;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class MultaDAO implements MultaService{
    private AccesoDB db;

    public MultaDAO() {
            db = new AccesoDB();
    }

    @Override
    public String RegistrarMulta(Multa multa) {
            String rpta=null;
            Connection cn = db.getConnection();
            String procedimientoalmacenado=("{CALL sp_insertarmulta(?,?,?,?)}");
            if(cn!=null) {
                    try{
                            CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                            cs.setInt(1, multa.getIdMulta());
                            cs.setDate(2, (Date) multa.getInicio());
                            cs.setDate(3, (Date) multa.getFin());
                            cs.setInt(4, multa.getMonto());
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
    public Multa ListadoMultaId(int idMulta) {
            Multa multa = new Multa();
            Connection cn = db.getConnection();
            String procedimientoalmacenado = "{CALL sp_datosmultaporid(?)}";
            if(cn!=null) {
                    try{
                            CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                            cs.setInt(1, idMulta);
                            ResultSet rs = cs.executeQuery();
                            if(rs.next()) {
                                    multa.setIdMulta(rs.getInt("idMulta"));
                                    multa.setInicio(rs.getDate("inicio"));
                                    multa.setFin(rs.getDate("fin"));
                                    multa.setMonto(rs.getInt("monto"));
                            }
                    }catch(SQLException ex) {}
                    finally {
                            try{
                                    cn.close();
                            }catch(SQLException e) {
                            }
                    }
            }
            return multa;
    }

    @Override
    public List<Multa> ListadoMultaTodos() {
            List<Multa> lista=new ArrayList<>();
            String procedimientoalmacenado="{CALL sp_listarmulta()}";
            Connection cn = db.getConnection();
            if(cn!=null) {
                    try{
                            CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                            ResultSet rs = cs.executeQuery();
                            while(rs.next()) {
                                    Multa multa = new Multa();
                                    multa.setIdMulta(rs.getInt("idMulta"));
                                    multa.setInicio(rs.getDate("inicio"));
                                    multa.setFin(rs.getDate("fin"));
                                    multa.setMonto(rs.getInt("monto"));
                                    lista.add(multa);
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
    public String ActualizarMulta(Multa multa) {
            String rpta =null;
            Connection cn=db.getConnection();
            String procedimientoalmacenado="{CALL sp_actualizarmulta(?,?,?,?)}";
            if(cn!=null) {
                    try{
                            CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                            cs.setInt(1, multa.getIdMulta());
                            cs.setDate(2, (Date) multa.getInicio());
                            cs.setDate(3, (Date) multa.getFin());
                            cs.setInt(4, multa.getMonto());
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
    public String EliminarMultaId(Multa multa) {
            String rpta = null;
            Connection cn = db.getConnection();
            String procedimientoalmacenado="{CALL sp_eliminarmulta(?)}";
            if(cn!=null) {
                    try{
                            CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                            cs.setInt(1, multa.getIdMulta());
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
