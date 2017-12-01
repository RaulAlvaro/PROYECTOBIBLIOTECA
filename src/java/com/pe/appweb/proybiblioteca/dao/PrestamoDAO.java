/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Prestamo;
import com.pe.appweb.proybiblioteca.service.PrestamoService;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class PrestamoDAO implements PrestamoService{
    
    private AccesoDB db;
    
    public PrestamoDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistrarPrestamo(Prestamo prestamo) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarPrestamo(?,?,?,?,?,?,?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, prestamo.getIdPrestamo());
                cs.setDate(2, prestamo.getInicio());
                cs.setDate(3, prestamo.getFin());
                if(prestamo.isDevuelto()==false){
                    cs.setInt(4,0);
                }else{
                    cs.setInt(4,1);
                }
                if(prestamo.isAgregado()==false){
                    cs.setInt(5,0);
                }else{
                    cs.setInt(5,1);
                }
                cs.setInt(6, prestamo.getIdLector());
                cs.setInt(7, prestamo.getIdPersonal());
                cs.setInt(8, prestamo.getIdLector());
                cs.setInt(9, prestamo.getIdMulta());
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
    public List<Prestamo> ListadoPrestamoTodos() {
        List<Prestamo> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarPrestamo()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Prestamo prestamo = new Prestamo();
                      prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
                      prestamo.setInicio(rs.getDate("inicio"));
                      prestamo.setFin(rs.getDate("fin"));
                      prestamo.setAgregado(rs.getBoolean("agregado"));
                      prestamo.setDevuelto(rs.getBoolean("devuelto"));
                      prestamo.setCadinicio(rs.getString("cadinicio"));
                      prestamo.setCadfin(rs.getString("cadfin"));
                      prestamo.setIdCopia(rs.getInt("copia_idcopia"));
                      prestamo.setIdLector(rs.getInt("lector_codigo"));
                      prestamo.setIdMulta(rs.getInt("multa_idmulta"));
                      prestamo.setIdPersonal(rs.getInt("personal_idPersonal"));
                      lista.add(prestamo);
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
    public Prestamo ListadoPrestamoId(int idPrestamo) {
        Prestamo prestamo = new Prestamo();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datosPrestamoporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idPrestamo);
                
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                     prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
                      prestamo.setInicio(rs.getDate("inicio"));
                      prestamo.setFin(rs.getDate("fin"));
                      prestamo.setAgregado(rs.getBoolean("agregado"));
                      prestamo.setDevuelto(rs.getBoolean("devuelto"));
                      prestamo.setCadinicio(rs.getString("cadinicio"));
                      prestamo.setCadfin(rs.getString("cadfin"));
                      prestamo.setIdCopia(rs.getInt("copia_idcopia"));
                      prestamo.setIdLector(rs.getInt("lector_codigo"));
                      prestamo.setIdMulta(rs.getInt("multa_idmulta"));
                      prestamo.setIdPersonal(rs.getInt("personal_idPersonal"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return prestamo;
    }

    @Override
    public String ActualizarPrestamo(Prestamo prestamo) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarPrestamo(?,?,?,?,?,?,?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, prestamo.getIdPrestamo());
                cs.setDate(2, prestamo.getInicio());
                cs.setDate(3, prestamo.getFin());
                if(prestamo.isDevuelto()==false){
                    cs.setInt(4,0);
                }else{
                    cs.setInt(4,1);
                }
                if(prestamo.isAgregado()==false){
                    cs.setInt(5,0);
                }else{
                    cs.setInt(5,1);
                }
                cs.setInt(6, prestamo.getIdLector());
                cs.setInt(7, prestamo.getIdPersonal());
                cs.setInt(8, prestamo.getIdLector());
                cs.setInt(9, prestamo.getIdMulta());
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
    public String EliminarPrestamoId(Prestamo prestamo) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarPrestamo(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, prestamo.getIdPrestamo());
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
