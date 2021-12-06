/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author borja
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll",
            query = "SELECT c FROM Categoria c")
})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    
    @OneToMany(mappedBy = "categoria")
    private List<Articulo> articulos;

    //<editor-fold defaultstate="collapsed" desc="auto">
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Articulo> getArticulos() {
        return articulos;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.b0ve.daw.proyecto.model.Categoria[ id=" + id + " ]";
    }
//</editor-fold>
    
}
