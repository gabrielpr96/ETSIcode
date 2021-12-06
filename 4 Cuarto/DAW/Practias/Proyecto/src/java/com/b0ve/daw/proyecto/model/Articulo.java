/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.model;

import com.b0ve.daw.proyecto.helpers.EstadoArticulo;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author borja
 */
@Entity
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String descripcion;
    
    @Column(nullable = false)
    private float precio;
    
    private Integer ano;
    
    private EstadoArticulo estado;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "blob")
    private byte[] imagen;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    //<editor-fold defaultstate="collapsed" desc="auto">
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public float getPrecio() {
        return precio;
    }
    
    public byte[] getImagen() {
        return imagen;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Integer getAno() {
        return ano;
    }
    
    public EstadoArticulo getEstado(){
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setEstado(EstadoArticulo estado) {
        this.estado = estado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.b0ve.daw.proyecto.model.Articulo[ id=" + id + " ]";
    }
//</editor-fold>

}
