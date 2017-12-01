/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Libro;
import com.pe.appweb.proybiblioteca.service.LibroService;
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
public class LibroDAO implements LibroService{
    
    private AccesoDB db;
    
    public LibroDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistroLibro(Libro libro) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarlibro(?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, libro.getIdLibro());
                cs.setString(2, libro.getTitulo());
                cs.setInt(3, libro.getIdTipo());
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
    public List<Libro> ListadoLibrosTodos() {
        List<Libro> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarlibro()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Libro libro = new Libro();
                      libro.setIdLibro(rs.getInt("idLibro"));
                      libro.setTitulo(rs.getString("titulo"));
                      lista.add(libro);
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
    public Libro ListadoLibroId(int idLibro) {
        Libro libro = new Libro();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datoslibroporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idLibro);
                
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                     libro.setIdLibro(rs.getInt("idLibro"));
                     libro.setTitulo(rs.getString("titulo"));
                     libro.setIdTipo(rs.getInt("tipo_idTipo"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return libro;
    }

    @Override
    public String ActualizarLibro(Libro libro) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarlibro(?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, libro.getIdLibro());
                cs.setString(2, libro.getTitulo());
                cs.setInt(3, libro.getIdTipo());
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
    public String EliminarLibro(Libro libro) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarlibro(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, libro.getIdLibro());
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
