/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.controller;

import com.b0ve.daw.proyecto.helpers.EstadoArticulo;
import com.b0ve.daw.proyecto.model.Categoria;
import com.b0ve.daw.proyecto.model.Usuario;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "PublicarControlador", urlPatterns = {"/publicar"})
public class PublicarControlador extends HttpServlet {
    
@PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            requireLogin(request);
        } catch (IllegalStateException e) {
            response.sendError(403);
            return;
        }
        
        TypedQuery<Categoria> q = em.createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> categorias = q.getResultList();
        request.setAttribute("categorias", categorias);
        
        List<String> estados = Arrays.stream(EstadoArticulo.values()).map(EstadoArticulo::getNombre).collect(Collectors.toList());
        request.setAttribute("estados", estados);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/publicar.jsp");
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

    private void requireLogin(HttpServletRequest request) {
        Long id = loggedId(request);
        if (id == null) {
            throw new IllegalStateException("No se puede acceder sin iniciar sesion");
        }
        Usuario u = em.find(Usuario.class, id);
        if (u == null) {
            throw new IllegalStateException("La sesion no es valida. Me la han intentado colar.");
        }
    }
    
    private Long loggedId(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute("id");
    }
}
