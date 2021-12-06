/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.model;

import com.b0ve.daw.proyecto.helpers.VisibilidadComentario;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author borja
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Comentarios.findByArticuloForUsuario",
            query = "SELECT c FROM Comentario c WHERE (c.usuario.id = :usuarioId OR (c.articulo.usuario.id = :usuarioId AND c.visibilidad = :visibiliadVendedor) OR c.visibilidad = :visibilidadPublico) AND c.articulo.id = :articuloId"),
    @NamedQuery(name = "Comentarios.findByArticuloPublic",
            query = "SELECT c FROM Comentario c WHERE  c.visibilidad = :visibilidadPublico AND c.articulo.id = :articuloId")
})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String texto;

    private VisibilidadComentario visibilidad;

    @ManyToOne
    private Articulo articulo;

    @ManyToOne
    private Usuario usuario;

    //<editor-fold defaultstate="collapsed" desc="auto">
    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public VisibilidadComentario getVisibilidad() {
        return visibilidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setVisibilidad(VisibilidadComentario visibilidad) {
        this.visibilidad = visibilidad;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b0ve.daw.proyecto.model.Comentario[ id=" + id + " ]";
    }
//</editor-fold>

}
