/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.controller;

import com.b0ve.daw.proyecto.helpers.VisibilidadComentario;
import com.b0ve.daw.proyecto.model.Articulo;
import com.b0ve.daw.proyecto.model.Comentario;
import com.b0ve.daw.proyecto.service.helpers.EncriptacionServicio;
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
@WebServlet(name = "ArticuloControlador", urlPatterns = {"/articulo/*"})
public class ArticuloControlador extends HttpServlet {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private final EncriptacionServicio encriptacionServicio = EncriptacionServicio.instance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        try {
            id = Long.parseLong(request.getPathInfo().replace("/", ""));
        } catch (java.lang.NumberFormatException e) {
            response.sendError(404, "Articulo no encontrado");
            return;
        }

        Articulo articulo = em.find(Articulo.class, id);
        if (articulo == null) {
            response.sendError(404, "Articulo no encontrado");
            return;
        }
        request.setAttribute("articulo", articulo);
        request.setAttribute("imagen64", new String(articulo.getImagen(), StandardCharsets.UTF_8));
        request.setAttribute("comentarios", getComentarios(articulo, request));

        List<String> visibilidades = Arrays.stream(VisibilidadComentario.values()).map(VisibilidadComentario::getNombre).collect(Collectors.toList());
        request.setAttribute("visibilidades", visibilidades);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/articulo.jsp");
        rd.forward(request, response);
    }

    private List<Comentario> getComentarios(Articulo articulo, HttpServletRequest request) {
        Long usuarioId = (Long) request.getSession().getAttribute("id");

        TypedQuery<Comentario> q;
        if (usuarioId == null) {
            q = em.createNamedQuery("Comentarios.findByArticuloPublic", Comentario.class);
        } else {
            q = em.createNamedQuery("Comentarios.findByArticuloForUsuario", Comentario.class);
            q.setParameter("usuarioId", usuarioId);
            q.setParameter("visibiliadVendedor", VisibilidadComentario.VENDEDOR);
        }
        q.setParameter("visibilidadPublico", VisibilidadComentario.PUBLICO);
        q.setParameter("articuloId", articulo.getId());

        return q.getResultList();
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
