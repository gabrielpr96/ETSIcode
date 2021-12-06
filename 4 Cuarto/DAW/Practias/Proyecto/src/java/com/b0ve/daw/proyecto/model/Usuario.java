/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @NamedQuery(name = "Usuario.findByEmail",
            query = "SELECT u FROM Usuario u WHERE u.email = :email")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String nombre;
    
    private String direccion;
    
    @Column(nullable = false)
    private Integer cp;
    
    private String facebook;
    
    private String twitter;
    
    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String pass;

    @OneToMany(mappedBy = "usuario")
    private List<Articulo> articulos;
    
    @OneToMany
    private List<Articulo> intereses;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;

    //<editor-fold defaultstate="collapsed" desc="auto">
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Articulo> getIntereses() {
        return intereses;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCp() {
        return cp;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setIntereses(List<Articulo> intereses) {
        this.intereses = intereses;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario[ id=" + id + " ]";
    }
//</editor-fold>

}
