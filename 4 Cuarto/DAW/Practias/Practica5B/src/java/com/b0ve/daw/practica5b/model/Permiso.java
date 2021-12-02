/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.practica5b.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author borja
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Roles.findAll",
                query="SELECT p FROM Permiso p"),
    @NamedQuery(name="Roles.findByName",
                query="SELECT p FROM Permiso p WHERE p.nombre = :nombre"),
})
public class Permiso implements Serializable {

    public Permiso(){
        
    }

    public Permiso(Long id) {
        this.id = id;
    }
    
    
    
    /**
     * @return the usuario
     */
    public List<Usuario> getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    
    @ManyToMany(mappedBy = "permisos", cascade = CascadeType.DETACH)
    private List<Usuario> usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b0ve.daw.practica5b.model.Role[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param name the name to set
     */
    public void setNombre(String name) {
        this.nombre = name;
    }
    
}
