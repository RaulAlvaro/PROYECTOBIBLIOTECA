/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Personal;
import com.pe.appweb.proybiblioteca.service.PersonalService;
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

public class PersonalDAO implements PersonalService{

    private AccesoDB db;
    
    public PersonalDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistrarPersonal(Personal personal) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarPersonal(?,?,?,?,?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, personal.getIdPersonal());
                cs.setString(2, personal.getNombre());
                cs.setString(3, personal.getApellido());
                cs.setInt(4, personal.getTelefono());
                cs.setString(5, personal.getCorreo());
                cs.setString(6, personal.getSexo());
                cs.setInt(7, personal.getIdTipopersonal());
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
    public List<Personal> ListadoPersonalTodos() {
        List<Personal> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarPersonal()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Personal personal = new Personal();
                      personal.setIdPersonal(rs.getInt("idPersonal"));
                      personal.setNombre(rs.getString("nombre"));
                      personal.setApellido(rs.getString("apellido"));
                      personal.setTelefono(rs.getInt("telefono"));
                      personal.setCorreo(rs.getString("correo"));
                      personal.setSexo(rs.getString("sexo"));
                      personal.setIdTipopersonal(rs.getInt("tipopersonal_idTipopersonal"));
                      lista.add(personal);
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
    public Personal ListadoPersonalId(int idLibro) {
        Personal personal = new Personal();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datospersonalporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idLibro);
                
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                     personal.setIdPersonal(rs.getInt("idPersonal"));
                     personal.setNombre(rs.getString("nombre"));
                     personal.setApellido(rs.getString("apellido"));
                     personal.setTelefono(rs.getInt("telefono"));
                     personal.setCorreo(rs.getString("correo"));
                     personal.setSexo(rs.getString("sexo"));
                     personal.setIdTipopersonal(rs.getInt("tipopersonal_idTipopersonal"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return personal;
    }

    @Override
    public String ActualizarPersonal(Personal personal) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarpersonal(?,?,?,?,?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, personal.getIdPersonal());
                cs.setString(2, personal.getNombre());
                cs.setString(3, personal.getApellido());
                cs.setInt(4, personal.getTelefono());
                cs.setString(5, personal.getCorreo());
                cs.setString(6, personal.getSexo());
                cs.setInt(7, personal.getIdTipopersonal());
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
    public String EliminarPersonalId(Personal personal) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarPersonal(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, personal.getIdPersonal());
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
