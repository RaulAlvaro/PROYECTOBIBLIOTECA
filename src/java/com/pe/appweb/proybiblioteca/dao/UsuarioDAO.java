/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Usuario;
import com.pe.appweb.proybiblioteca.service.UsuarioService;
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
public class UsuarioDAO implements UsuarioService {
    
    private AccesoDB db;
    
    public UsuarioDAO() {
        db = new AccesoDB();
    }
    
    @Override
    public String RegistroUsuario(Usuario usuario) {
        String rpta=null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado=("{CALL sp_insertarusuario(?,?,?,?)}");
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, usuario.getIdUsuario());
                cs.setString(2, usuario.getNombreusuario());
                cs.setString(3, usuario.getClaveusuario());
                cs.setInt(4, usuario.getIdPersonal());
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
    public List<Usuario> ListadoUsuarioTodos() {
        List<Usuario> lista=new ArrayList<>();
        String procedimientoalmacenado="{CALL sp_listarusuario()}";
        Connection cn = db.getConnection();
        if(cn!=null){
            try{
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                      Usuario usuario = new Usuario();
                      usuario.setIdUsuario(rs.getInt("idUsuario"));
                      usuario.setNombreusuario(rs.getString("nombreusuario"));
                      usuario.setClaveusuario(rs.getString("claveusuario"));
                      usuario.setIdPersonal(rs.getInt("personal_IdPersonal"));
                      lista.add(usuario);
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
    public Usuario ListadoUsuarioId(int idUsuario) {
        Usuario usuario = new Usuario();
        Connection cn = db.getConnection();
        String procedimientoalmacenado = "{CALL sp_datosusuarioporid(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, idUsuario);
                
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                     usuario.setIdUsuario(rs.getInt("IdUsuario"));
                     usuario.setNombreusuario(rs.getString("nombreusuario"));
                     usuario.setClaveusuario(rs.getString("claveusuario"));
                     usuario.setIdPersonal(rs.getInt("IdPersonal"));
                }
            }catch(SQLException ex){}
            finally {
                try{
                    cn.close();
                }catch(SQLException e){
                }}
        }
        return usuario;
    }

    @Override
    public String ActualizarUsuario(Usuario usuario) {
        String rpta =null;
        Connection cn=db.getConnection();
        String procedimientoalmacenado="{CALL sp_actualizarusuario(?,?,?,?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, usuario.getIdUsuario());
                cs.setString(2, usuario.getNombreusuario());
                cs.setString(3, usuario.getClaveusuario()); 
                cs.setInt(4, usuario.getIdPersonal());
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
    public String EliminarUsuario(Usuario usuario) {
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado="{CALL sp_eliminarUsuario(?)}";
        if(cn!=null){
            try{
                CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, usuario.getIdUsuario());
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
