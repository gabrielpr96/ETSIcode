/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.controller;

import com.b0ve.daw.proyecto.helpers.EstadoArticulo;
import com.b0ve.daw.proyecto.helpers.VisibilidadComentario;
import com.b0ve.daw.proyecto.model.Articulo;
import com.b0ve.daw.proyecto.model.Comentario;
import com.b0ve.daw.proyecto.model.Usuario;
import com.b0ve.daw.proyecto.service.EncriptacionServicio;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
@WebServlet(name = "InteresControlador", urlPatterns = {"/interes"})
public class InteresControlador extends HttpServlet {
    
@PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private final EncriptacionServicio encriptacionServicio = EncriptacionServicio.instance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            requireLogin(request);
        } catch (IllegalStateException e) {
            response.sendError(403);
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/interes.jsp");
        rd.forward(request, response);
    }
    
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

}
