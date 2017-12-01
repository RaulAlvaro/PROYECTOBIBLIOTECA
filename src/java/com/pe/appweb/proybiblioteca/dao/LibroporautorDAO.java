/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.dao;

import com.pe.appweb.proybiblioteca.entidades.Libroporautor;
import com.pe.appweb.proybiblioteca.service.LibroPorAutorService;
import com.pe.appweb.proybiblioteca.util.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author RAUL
 */
public class LibroporautorDAO implements LibroPorAutorService {
    
    private AccesoDB db;
    
    public LibroporautorDAO() {
        db = new AccesoDB();
    }
   
    @Override
    public String RegistrarLibroPorAutor(Libroporautor libroPorAutor) {
         /*
        String rpta = null;
        Connection cn = db.getConnection();
        String procedimientoalmacenado = ("{CALL sp_insertartipolibro(?,?)}");
        if (cn != null) {
            try {
                CallableStatement cs = cn.prepareCall(procedimientoalmacenado);
                cs.setInt(1, tipo.getIdTipo());
                cs.setString(2, tipo.getTipo());
                int inserto = cs.executeUpdate();
                if (inserto == 0) {
                    rpta = "Error";
                } else {
                    rpta = "Registrado";
                }

            } catch (SQLException ex) {
                rpta = ex.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    rpta = e.getMessage();
                }
            }
        }
        return rpta;
    */
         return null;
    }
    
}
