/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.practica5b.controller;

import com.b0ve.daw.practica5b.model.Permiso;
import com.b0ve.daw.practica5b.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Usuarios", urlPatterns = {"/usuarios/*"})
public class ControladorUsuarios extends HttpServlet {

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

        TypedQuery<Usuario> qu;
        TypedQuery<Permiso> qp;
        List<Usuario> lu;
        List<Permiso> lp;
        Usuario u;
        Permiso p;
        Long id;
        String name;
        String email;
        String[] roles;

        switch (accion) {
            case "/show":
                qu = em.createNamedQuery("Usuarios.findAll", Usuario.class);
                lu = qu.getResultList();
                request.setAttribute("usuarios", lu);
                vista = "/showUsuarios.jsp";
                break;
            case "/alta":
                qp = em.createNamedQuery("Roles.findAll", Permiso.class);
                lp = qp.getResultList();
                request.setAttribute("roles", lp);
                vista = "/altaUsuarios.jsp";
                break;
            case "/delete":
                id = Long.parseLong(request.getParameter("id"));
                u = em.find(Usuario.class, id);
                delete(u);
                qu = em.createNamedQuery("Usuarios.findAll", Usuario.class);
                lu = qu.getResultList();
                request.setAttribute("usuarios", lu);
                vista = "/showUsuarios.jsp";
                break;
            case "/edit":
                qp = em.createNamedQuery("Roles.findAll", Permiso.class);
                lp = qp.getResultList();
                request.setAttribute("roles", lp);
                id = Long.parseLong(request.getParameter("id"));
                u = em.find(Usuario.class, id);
                request.setAttribute("usuario", u);
                vista = "/editarUsuarios.jsp";
                break;
            case "/merge":
                id = Long.parseLong(request.getParameter("id"));
                u = em.find(Usuario.class, id);
                name = request.getParameter("nombre");
                email = request.getParameter("correo");
                roles = request.getParameterValues("roles");
                u.setNombre(name);
                u.setCorreo(email);
                lp = new ArrayList<>();
                if (roles != null) {
                    for (String idRol : roles) {
                        id = Long.parseLong(idRol);
                        p = em.find(Permiso.class, id);
                        if (p != null) {
                            lp.add(p);
                        }
                    }
                }
                u.setPermisos(lp);
                merge(u);
                qu = em.createNamedQuery("Usuarios.findAll", Usuario.class);
                lu = qu.getResultList();
                request.setAttribute("usuarios", lu);
                vista = "/showUsuarios.jsp";
                break;

            case "/persistUser":
                String msg;
                name = request.getParameter("nombre");
                email = request.getParameter("correo");
                roles = request.getParameterValues("roles");
                if (name != null && email != null) {
                    u = new Usuario();
                    u.setNombre(name);
                    u.setCorreo(email);
                    lp = new ArrayList<>();
                    if (roles != null) {
                        for (String idRol : roles) {
                            id = Long.parseLong(idRol);
                            p = em.find(Permiso.class, id);
                            if (p != null) {
                                lp.add(p);
                            }
                        }
                    }
                    u.setPermisos(lp);
                    try {
                        persist(u);
                        msg = "<p style='background:green;color:white'>Usuario: " + name + " insertado con éxito</p>";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        msg = "<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                    }
                } else {
                    msg = "<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                }
                request.setAttribute("msg", msg);
                vista = "/altaUsuarios.jsp";
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
