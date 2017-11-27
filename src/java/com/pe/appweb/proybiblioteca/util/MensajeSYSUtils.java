/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.appweb.proybiblioteca.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author RAUL
 */
public class MensajeSYSUtils {
    public void messageError(String sMensaje)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, accesosConstantes.pubNomMod, sMensaje));
    }        
    
    public void messageFatal(String sMensaje)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, accesosConstantes.pubNomMod, sMensaje));
    }   
    
    public void messageInfo(String sMensaje)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, accesosConstantes.pubNomMod, sMensaje));
    }  
    
    public void messageWarn(String sMensaje)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, accesosConstantes.pubNomMod, sMensaje));
    }     
    
}
