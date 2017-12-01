/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Lector;
import com.pe.appweb.proybiblioteca.service.LectorService;
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
public class LectorDAO implements LectorService {

    private AccesoDB db;
    
    public LectorDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistrarLector(Lector lector) {
       String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarlector(?,?,?,?,?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, lector.getCodigo());
                cs.setString(2, lector.getNombres());
                cs.setString(3, lector.getApellidos());
                cs.setInt(4, lector.getTelefono());
                cs.setString(5, lector.getSexo());
                if(lector.isEstado()==false){
                    cs.setInt(6, 0);
                }else{
                    cs.setInt(6, 1);
                }
                cs.setInt(7, lector.getStrikes());
                
                int inserto = cs.executeUpdate();
                if(inserto==0){
                    rpta="Error";
                }
                else{
                    rpta="Registrado";
                }
                
                }catch(SQLException ex){rpta=ex.getMessage();}
            finally{
                try{
                    cn.close();
                }catch(SQLException e){
                    rpta = e.getMessage();
                }}
        }
        return rpta;
    }

    @Override
    public Lector ListadoLectorId(int codigo) {
        Lector lector = new Lector();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datoslectorporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, codigo);
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                    lector.setCodigo(rs.getInt("codigo"));
                    lector.setNombres(rs.getString("nombres"));
                    lector.setApellidos(rs.getString("apellidos"));
                    lector.setTelefono(rs.getInt("telefono"));
                    lector.setSexo(rs.getString("sexo"));
                    lector.setEstado(rs.getBoolean("estado"));
                    lector.setStrikes(rs.getInt("strikes"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return lector;
    }

    @Override
    public List<Lector> ListadoLectorTodos() {
        List<Lector> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarlector()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Lector lector = new Lector();
                      lector.setCodigo(rs.getInt("codigo"));
                      lector.setNombres(rs.getString("nombres"));
                      lector.setApellidos(rs.getString("apellidos"));
                      lector.setTelefono(rs.getInt("telefono"));
                      lector.setSexo(rs.getString("sexo"));
                      if(rs.getInt("estado")==0){
                          lector.setEstado(false);
                      }
                      else{
                          lector.setEstado(true);
                      }
                      lector.setStrikes(rs.getInt("strikes"));

                      lista.add(lector);
                }
            }catch(SQLException ex){}
            finally{
                try{
                    cn.close();
                }catch (SQLException e){
                    
                }
            }
        }
        return lista;
    }

    @Override
    public String ActualizarLector(Lector lector) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarlector(?,?,?,?,?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, lector.getCodigo());
                cs.setString(2, lector.getNombres());
                cs.setString(3, lector.getApellidos());
                cs.setInt(4, lector.getTelefono());
                cs.setString(5, lector.getSexo());
                cs.setBoolean(6, lector.isEstado());
                cs.setInt(7, lector.getStrikes());
                int inserto = cs.executeUpdate();
                
                if(inserto==0){
                    rpta="Error";
                }
                
            }catch(SQLException ex){rpta=ex.getMessage();}
            finally{
                try{
                    cn.close();
                }catch(SQLException e){
                    rpta = e.getMessage();
                }}
        }
        return rpta;
    }

    @Override
    public String EliminarLectorID(Lector lector) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarlector(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, lector.getCodigo());
                cs.setString(2, lector.getNombres());
                cs.setString(3, lector.getApellidos());
                cs.setInt(4, lector.getTelefono());
                cs.setString(5, lector.getSexo());
                cs.setBoolean(6, lector.isEstado());
                cs.setInt(7, lector.getStrikes());
                cs.executeUpdate();
                rpta="correcto";
            }catch(SQLException ex){rpta=ex.getMessage();}
            finally{
                try{
                    cn.close();
                }catch(SQLException e){
                    rpta = e.getMessage();
                }
            }
        }
        return rpta;
    }
    
}
