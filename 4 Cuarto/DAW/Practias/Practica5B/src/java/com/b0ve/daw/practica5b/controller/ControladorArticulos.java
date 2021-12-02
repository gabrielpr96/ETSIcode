/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.practica5b.controller;

import com.b0ve.daw.practica5b.model.Articulo;
import com.b0ve.daw.practica5b.model.Permiso;
import com.b0ve.daw.practica5b.model.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author borja
 */
@WebServlet(name = "Articulos", urlPatterns = {"/articulos/*"})
public class ControladorArticulos extends HttpServlet {

    @PersistenceContext(unitName = "Practica5BPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion;
        accion = request.getPathInfo();
        String vista;
        
        TypedQuery<Articulo> qa;
        TypedQuery<Usuario> qu;
        List<Articulo> la;
        List<Usuario> lu;
        Articulo a;
        Usuario u;
        
        Long id;
        String name;
        Double precio;

        switch (accion) {
            case "/show":
                qa = em.createNamedQuery("Articulos.findAll", Articulo.class);
                la = qa.getResultList();
                request.setAttribute("articulos", la);
                vista = "/showArticulos.jsp";
                break;
            case "/alta":
                qu = em.createNamedQuery("Usuarios.findAll", Usuario.class);
                lu = qu.getResultList();
                request.setAttribute("usuarios", lu);
                vista = "/altaArticulos.jsp";
                break;
            case "/persist":
                name = request.getParameter("nombre");
                precio = Double.parseDouble(request.getParameter("precio"));
                id = Long.parseLong(request.getParameter("usuario"));
                u = em.find(Usuario.class, id);
                if (name != null && precio != null) {
                    a = new Articulo();
                    a.setNombre(name);
                    a.setPrecio(precio);
                    a.setUsuario(u);
                    u.getArticulos().add(a);
                    try {
                        persist(a);
                        merge(u);
                        System.out.println("Articulo: " + name + "creado");
                    }catch (Exception ex) {
                        System.out.println("Error: Imposible persistir  Articulo: " + name);
                    } 
                } else {
                    System.out.println("Error: Parametros incorrectos");
                }
                qa = em.createNamedQuery("Articulos.findAll", Articulo.class);
                la = qa.getResultList();
                request.setAttribute("articulos", la);
                vista = "/showArticulos.jsp";
                break;
            case "/edit":
                id = Long.parseLong(request.getParameter("id"));
                a = em.find(Articulo.class, id);
                request.setAttribute("articulo", a);
                vista = "/editarArticulos.jsp";
                break;
            case "/merge":
                id = Long.parseLong(request.getParameter("id"));
                name = request.getParameter("nombre");
                precio = Double.parseDouble(request.getParameter("precio"));
                a = em.find(Articulo.class, id);
                a.setNombre(name);
                a.setPrecio(precio);
                merge(a);
                qa = em.createNamedQuery("Articulos.findAll", Articulo.class);
                la = qa.getResultList();
                request.setAttribute("articulos", la);
                vista = "/showArticulos.jsp";
                break;
            case "/delete":
                id = Long.parseLong(request.getParameter("id"));
                a = em.find(Articulo.class, id);
                delete(a);
                qa = em.createNamedQuery("Articulos.findAll", Articulo.class);
                la = qa.getResultList();
                request.setAttribute("articulos", la);
                vista = "/showArticulos.jsp";
                break;
            default:
                vista = "/index.html";
                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    private void delete(Object object) {
        try {
            utx.begin();
            object = (Object) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }        
    }

}
