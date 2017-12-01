/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Autor;
import java.util.List;
import com.pe.appweb.proybiblioteca.service.AutorService;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RAUL
 */
public class AutorDAO implements AutorService{
    private AccesoDB db;

  public AutorDAO() {
      db = new AccesoDB();
  }

  @Override
  public String RegistrarAutor(Autor autor) {
     String rpta=null;
      Connection cn = db.getConnection();
      String procedimientoalmacenado=("{CALL sp_insertarautor(?,?,?,?,?)}");
      if(cn!=null){
          try{
              CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
              cs.setInt(1, autor.getIdAutor());
              cs.setString(2, autor.getNombre());
              cs.setString(3, autor.getApellido());
              cs.setString(4, autor.getNacionalidad());
              cs.setString(5, autor.getSexo());
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
  public Autor ListadoAutorId(int idAutor) {
      Autor autor = new Autor();
      Connection cn = db.getConnection();
      String procedimientoalmacenado = "{CALL sp_datosAutorporid(?)}";
      if(cn!=null){
          try{
              CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
              cs.setInt(1, idAutor);
              ResultSet rs = cs.executeQuery();
              if(rs.next()){
                  autor.setIdAutor(rs.getInt("idAutor"));
                  autor.setNombre(rs.getString("nombre"));
                  autor.setApellido(rs.getString("apellido"));
                  autor.setNacionalidad(rs.getString("nacionalidad"));
                  autor.setSexo(rs.getString("sexo"));
              }
          }catch(SQLException ex){}
          finally {
              try{
                  cn.close();
              }catch(SQLException e){
              }}
      }
      return autor;
  }

  @Override
  public List<Autor> ListadoAutorTodos() {
      List<Autor> lista=new ArrayList<>();
      String procedimientoalmacenado="{CALL sp_listarautor()}";
      Connection cn = db.getConnection();
      if(cn!=null){
          try{
              CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
              ResultSet rs = cs.executeQuery();
              while(rs.next()){
                    Autor autor = new Autor();
                    autor.setIdAutor(rs.getInt("idAutor"));
                    autor.setNombre(rs.getString("nombre"));
                    autor.setApellido(rs.getString("apellido"));
                    autor.setNacionalidad(rs.getString("nacionalidad"));
                    autor.setSexo(rs.getString("sexo"));
                    lista.add(autor);
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
  public String ActualizarAutor(Autor autor) {
      String rpta =null;
      Connection cn=db.getConnection();
      String procedimientoalmacenado="{CALL sp_actualizarAutor(?,?,?,?,?)}";
      if(cn!=null){
          try{
              CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
              cs.setInt(1, autor.getIdAutor());
              cs.setString(2, autor.getNombre());
              cs.setString(3, autor.getApellido());
              cs.setString(4, autor.getNacionalidad());
              cs.setString(5, autor.getSexo());
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
  public String EliminarAutorId(Autor autor) {
      String rpta = null;
      Connection cn = db.getConnection();
      String procedimientoalmacenado="{CALL sp_eliminarautor(?)}";
      if(cn!=null){
          try{
              CallableStatement cs=cn.prepareCall(procedimientoalmacenado);
              cs.setInt(1, autor.getIdAutor());
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
