/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Usuario;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface UsuarioService {
    public abstract String RegistroUsuario(Usuario usuario);
    public abstract List<Usuario> ListadoUsuarioTodos();
    public abstract Usuario ListadoUsuarioId(int id);
    public abstract String ActualizarUsuario(Usuario usuario);
    public abstract String EliminarUsuario(Usuario usuario);
}
