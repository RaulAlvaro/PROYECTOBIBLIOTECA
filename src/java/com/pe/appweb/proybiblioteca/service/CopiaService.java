/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.service;

import com.pe.appweb.proybiblioteca.entidades.Copia;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface CopiaService {
    public abstract String RegistraCopia(Copia copia);
    public abstract List<Copia> ListadoCopiassTodos();
    public abstract Copia ListadoCopiaId(int idCopia);
    public abstract String ActualizarCopia(Copia copia);
    public abstract String EliminarCopia(Copia copia);

}
