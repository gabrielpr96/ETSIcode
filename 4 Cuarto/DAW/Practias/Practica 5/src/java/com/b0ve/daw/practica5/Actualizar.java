/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author borja
 */
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class Actualizar extends HttpServlet {

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
        // Guardar mensaje sobre estado del resultado
        String msg;

        // Crear un Objeto usuario
        Usuario user;

        // Creamos las variables para la conexión, la sentencia y el resultado y asignar sus campos con los valores leídos
        Connection conn;
        PreparedStatement ps;
        int filasAfectadas = 0;

        try {
            // Leer los parámetros enviados desde el formulario
            String str_id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String str_edad = request.getParameter("edad");

            // Conversión a entero, lanza una excepción NumberFormatException
            int id = Integer.parseInt(str_id);
            int edad = Integer.parseInt(str_edad);

            // establecer la conexión
            Context c = new InitialContext();
            DataSource javaWebApp1Pool = (DataSource) c.lookup("jdbc/practica5a");
            conn = javaWebApp1Pool.getConnection();

            // Preparar la sentencia SQL a realizar
            ps = conn.prepareStatement("UPDATE USUARIOS SET Nombre = ?, Edad = ? WHERE ID = ?");
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setInt(3, id);

            // Ejecutar instrucción SQL y guardar resultado en msg
            filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                msg = "<p>OK: Actualizacion realizada correctamente</p>";
            } else {
                msg = "<p>ERROR: Ha fallado la actualizacion</p>";
            }

            ps.close();
            conn.close();
        } catch (NamingException ex) {
            msg = "<p>ERROR: Recurso no disponible</p>";
            System.out.println(ex);
        } catch (SQLException ex) {
            msg = "<p>ERROR: Base de Datos no disponible</p>";
            System.out.println(ex);
        } catch (NumberFormatException ex) {
            msg = "<p>ERROR: Parámetros no Válidos</p>";
            System.out.println(ex);
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>App Web Práctica 5.a)</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>DAW - Práctica 5.a) Servlets y Acceso a Datos mediante un Pool de conexiones</h1>");
            out.println("<h2>Estado de la actualizacion</h2>");
            out.println(msg);
            out.println("<p><a href=\"/Practica5/Listado\">Volver</a>");
            out.println("<script>location.href=\"/Practica5/Listado\"</script>");
            out.println("</body>");
            out.println("</html>");
        }
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
