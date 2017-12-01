/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Autor;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface AutorService {
    
  public abstract String RegistrarAutor(Autor autor);
  public abstract List<Autor> ListadoAutorTodos();
  public abstract Autor ListadoAutorId(int idAutor);
  public abstract String ActualizarAutor(Autor autor);
  public abstract String EliminarAutorId(Autor autor);
  
}
