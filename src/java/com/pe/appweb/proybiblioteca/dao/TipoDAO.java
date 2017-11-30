/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Tipo;
import com.pe.appweb.proybiblioteca.service.TipoService;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAUL
 */
public class TipoDAO implements TipoService {
    
    private AccesoDB db;
    
    public TipoDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistrarTipo(Tipo tipo) {
       String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertartipolibro(?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, tipo.getIdTipo());
                cs.setString(2, tipo.getTipo());
                //cs.setString(1, alumno.getNombre());
                //cs.setString(2, alumno.getApellido());
                //cs.setInt(3, alumno.getEdad());
                //cs.setString(4, alumno.getDni());
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
    public Tipo ListadoTipoId(int idTipo) {
        Tipo tipo = new Tipo();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datostipolibroporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idTipo);
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                    tipo.setIdTipo(rs.getInt("idTipo"));
                    tipo.setTipo("tipo");
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return tipo;
    }

    @Override
    public List<Tipo> ListadoTipoTodos() {
        List<Tipo> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listartipolibro()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Tipo tipo = new Tipo();
                      tipo.setIdTipo(rs.getInt("idTipo"));
                      tipo.setTipo(rs.getString("tipo"));
                      lista.add(tipo);
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
    public String ActualizarTipo(Tipo tipo) {
         String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizartipolibro(?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, tipo.getIdTipo());
                cs.setString(2, tipo.getTipo());
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
    public String EliminarTipoId(Tipo tipo) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminartipolibro(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, tipo.getIdTipo());
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
