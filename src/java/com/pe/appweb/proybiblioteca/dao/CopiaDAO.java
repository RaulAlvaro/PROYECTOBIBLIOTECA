/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Copia;
import com.pe.appweb.proybiblioteca.service.CopiaService;
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
public class CopiaDAO implements CopiaService{
    
    private AccesoDB db;
    
    public CopiaDAO(){
        db = new AccesoDB();
    }
    
    @Override
    public String RegistraCopia(Copia copia) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarcopia(?,?,?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, copia.getIdCopia());
                cs.setInt(2, copia.getEstado());
                cs.setInt(3, copia.getEdicion());
                cs.setString(4, copia.getEditorial());
                cs.setInt(5, copia.getIdLibro());
                
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
    public List<Copia> ListadoCopiassTodos() {
        List<Copia> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarcopia()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Copia copia = new Copia();
                      copia.setIdCopia(rs.getInt("idCopia"));
                      copia.setEstado(rs.getInt("estado"));
                      copia.setIdLibro(rs.getInt("libro_idLibro"));
                      copia.setEdicion(rs.getInt("edicion"));
                      copia.setEditorial(rs.getString("editorial"));
                      lista.add(copia);
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
    public Copia ListadoCopiaId(int idCopia) {
        Copia copia = new Copia();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datoscopiaporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idCopia);
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                      copia.setIdLibro(rs.getInt("idCopia"));
                      copia.setEstado(rs.getInt("estado"));
                      copia.setIdLibro(rs.getInt("libro_idLibro"));
                      copia.setEdicion(rs.getInt("edicion"));
                      copia.setEditorial(rs.getString("editorial"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return copia;
    }

    @Override
    public String ActualizarCopia(Copia copia) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarcopia(?,?,?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, copia.getIdCopia());
                cs.setInt(2, copia.getEstado());
                cs.setInt(3, copia.getEdicion());
                cs.setString(4, copia.getEditorial());
                cs.setInt(5, copia.getIdLibro());
                
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
    public String EliminarCopia(Copia copia) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarcopia(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, copia.getIdCopia());
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
