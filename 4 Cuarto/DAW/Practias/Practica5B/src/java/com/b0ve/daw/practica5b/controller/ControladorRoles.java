/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.practica5b.controller;

import com.b0ve.daw.practica5b.model.Permiso;
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
@WebServlet(name = "Roles", urlPatterns = {"/roles/*"})
public class ControladorRoles extends HttpServlet {

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
        
        TypedQuery<Permiso> query;
        List<Permiso> lr;
        Permiso p;
        
        Long id;
        String name;

        switch (accion) {
            case "/show":
                query = em.createNamedQuery("Roles.findAll", Permiso.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr);
                vista = "/showRoles.jsp";
                break;
            case "/altaRol":
                vista = "/altaRoles.jsp";
                break;
            case "/persistRol":
                name = request.getParameter("nombre");
                if (name!=null) {
                    p = new Permiso();
                    p.setNombre(name);
                    try {
                        persist(p);
                        System.out.println("Rol: " + name + "creado");
                    }catch (Exception ex) {
                        System.out.println("Error: Imposible persistir  Rol: " + name);
                    } 
                } else {
                    System.out.println("Error: Nombre de Rol incorrecto");
                }
                query = em.createNamedQuery("Roles.findAll", Permiso.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr);
                vista = "/showRoles.jsp";
                break;
            case "/edit":
                id = Long.parseLong(request.getParameter("id"));
                p = em.find(Permiso.class, id);
                request.setAttribute("rol", p);
                vista = "/editarRoles.jsp";
                break;
            case "/mergeRol":
                id = Long.parseLong(request.getParameter("id"));
                name = request.getParameter("nombre");
                p = em.find(Permiso.class, id);
                p.setNombre(name);
                merge(p);
                query = em.createNamedQuery("Roles.findAll", Permiso.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr);
                vista = "/showRoles.jsp";
                break;
            case "/delete":
                id = Long.parseLong(request.getParameter("id"));
                p = em.find(Permiso.class, id);
                delete(p);
                query = em.createNamedQuery("Roles.findAll", Permiso.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr);
                vista = "/showRoles.jsp";
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
