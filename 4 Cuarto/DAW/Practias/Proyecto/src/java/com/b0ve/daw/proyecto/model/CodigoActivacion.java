/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author borja
 */
@Entity
@NamedQueries({
    @NamedQuery(name="CodigoActivacion.findByCodigo",
                query="SELECT c FROM CodigoActivacion c WHERE c.codigo = :codigo"),
    @NamedQuery(name="CodigoActivacion.findByEmailAndPass",
                query="SELECT c FROM CodigoActivacion c INNER JOIN Usuario u WHERE u.email = :email AND u.pass = :pass")
})
public class CodigoActivacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String codigo;
    
    @OneToOne()
    private Usuario usuario;

    //<editor-fold defaultstate="collapsed" desc="auto">
    public Long getId() {
        return id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigoActivacion)) {
            return false;
        }
        CodigoActivacion other = (CodigoActivacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "CodigoActivacion[ id=" + id + " ]";
    }
//</editor-fold>
    
}
