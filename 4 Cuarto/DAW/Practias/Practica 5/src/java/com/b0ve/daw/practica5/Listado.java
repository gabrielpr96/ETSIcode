package com.b0ve.daw.practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "Listado", urlPatterns = {"/Listado"})
public class Listado extends HttpServlet {

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
        List<Usuario> usuarios = new ArrayList<>();

        String msg = "Usuarios listados correctamente";
        try {
            // establecer la conexión
            Context c = new InitialContext();
            DataSource javaWebApp1Pool = (DataSource) c.lookup("jdbc/practica5a");
            Connection conn = javaWebApp1Pool.getConnection();

            // Preparar la sentencia SQL a realizar
            PreparedStatement ps = conn.prepareStatement("SELECT Id, Nombre, Edad FROM USUARIOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("Id"), rs.getString("Nombre"), rs.getInt("Edad")));
            }

            ps.close();
            conn.close();
        } catch (NamingException ex) {
            msg = "<p>ERROR: Recurso no disponible</p>";
            System.out.println(ex);
        } catch (SQLException ex) {
            msg = "<p>ERROR: Base de Datos no disponible</p>";
            System.out.println(ex);
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html>\n"
                    + "<html lang=\"es\">\n"
                    + "  <head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "    <!-- Bootstrap CSS -->\n"
                    + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                    + "    <title>Practica 5 A</title>\n"
                    + "  </head>\n"
                    + "  <body>\n"
                    + "<div class=\"container\">"
                    + "    <h1>Listado de usuarios!</h1>\n"
                    + "<small>" + msg + "</small>"
                    + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n"
                    + "<br><button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#modalCrear\">Crear usuario</button>"
                            + "<table class=\"table\">\n"
                    + "  <thead>\n"
                    + "    <tr>\n"
                    + "      <th scope=\"col\">Nombre</th>\n"
                    + "      <th scope=\"col\">Edad</th>\n"
                    + "      <th scope=\"col\"></th>\n"
                    + "    </tr>\n"
                    + "  </thead>\n"
                    + "  <tbody>\n"
            );
            for (Usuario usuario : usuarios) {
                out.println("    <tr>\n"
                        + "      <th scope=\"row\">" + usuario.getNombre() + "</th>\n"
                        + "      <td>" + usuario.getEdad() + "</td>\n"
                        + "      <td>"
                        + "<div class=\"dropdown\">\n"
                        + "  <button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton1\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n"
                        + "    Acciones\n"
                        + "  </button>\n"
                        + "  <ul class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton1\">\n"
                        + "    <li><a class=\"dropdown-item\" href=\"#\" onclick=\"actualizar(" + usuario.getId() + ", '" + usuario.getNombre() + "', " + usuario.getEdad()+ ")\" data-bs-toggle=\"modal\" data-bs-target=\"#modalActualizar\">Actualizar</a></li>\n"
                        + "    <li><a class=\"dropdown-item\" href=\"#\" onclick=\"eliminar(" + usuario.getId() + ", '" + usuario.getNombre() + "')\" data-bs-toggle=\"modal\" data-bs-target=\"#modalBorrar\">Eliminar</a></li>\n"
                        + "  </ul>\n"
                        + "</div>"
                        + "</td>\n"
                        + "    </tr>\n");
            }
            out.println("  </tbody>\n"
                    + "</table>"
                    + "<div class=\"modal fade\" id=\"modalBorrar\" tabindex=\"-1\">\n"
                    + "  <div class=\"modal-dialog\">\n"
                    + "    <div class=\"modal-content\">\n"
                    + "      <div class=\"modal-header\">\n"
                    + "        <h5 class=\"modal-title\">¿Seguro que desea eliminar al usuario?</h5>\n"
                    + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                    + "      </div>\n"
                    + "      <div class=\"modal-body\">\n"
                    + "        <p id=\"modalBorrarTexto\"></p>\n"
                    + "      </div>\n"
                    + "      <div class=\"modal-footer\">\n"
                    + "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancelar</button>\n"
                    + "        <button type=\"button\" class=\"btn btn-danger\" id=\"modalBorrarBtn\">ELIMINAR</button>\n"
                    + "      </div>\n"
                    + "    </div>\n"
                    + "  </div>\n"
                    + "</div>"
                    + "<div class=\"modal fade\" id=\"modalActualizar\" tabindex=\"-1\">\n"
                    + "  <div class=\"modal-dialog\">\n"
                    + "    <div class=\"modal-content\">\n"
                    + "      <div class=\"modal-header\">\n"
                    + "        <h5 class=\"modal-title\">Actualizar usuario</h5>\n"
                    + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                    + "      </div>\n"
                    + "      <div class=\"modal-body\">\n"
                    + " <form action=\"Actualizar\" method=\"post\">\n"
                    + "  <div class=\"mb-3\">\n"
                    + "    <label for=\"modalActualizarNombre\" class=\"form-label\">Nombre</label>\n"
                    + "    <input type=\"text\" class=\"form-control\" name=\"nombre\" id=\"modalActualizarNombre\">\n"
                    + "  </div>\n"
                    + "  <div class=\"mb-3\">\n"
                    + "    <label for=\"modalActualizarEdad\" class=\"form-label\">Edad</label>\n"
                    + "    <input type=\"number\" class=\"form-control\" name=\"edad\" id=\"modalActualizarEdad\">\n"
                    + "  </div>\n"
                    + "<input type=\"hidden\" class=\"form-control\" name=\"id\" id=\"modalActualizarId\">"
                    + "  <button type=\"submit\" class=\"btn btn-primary\">Actualizar</button>\n"
                    + "</form>"
                    + "      </div>\n"
                    + "      <div class=\"modal-footer\">\n"
                    + "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancelar</button>\n"
                    + "      </div>\n"
                    + "    </div>\n"
                    + "  </div>\n"
                    + "</div>"
                    + "<div class=\"modal fade\" id=\"modalCrear\" tabindex=\"-1\">\n"
                    + "  <div class=\"modal-dialog\">\n"
                    + "    <div class=\"modal-content\">\n"
                    + "      <div class=\"modal-header\">\n"
                    + "        <h5 class=\"modal-title\">Crear usuario</h5>\n"
                    + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                    + "      </div>\n"
                    + "      <div class=\"modal-body\">\n"
                    + " <form action=\"Insertar\" method=\"post\">\n"
                    + "  <div class=\"mb-3\">\n"
                    + "    <label for=\"modalActualizarNombre\" class=\"form-label\">Nombre</label>\n"
                    + "    <input type=\"text\" class=\"form-control\" name=\"nombre\" id=\"modalActualizarNombre\">\n"
                    + "  </div>\n"
                    + "  <div class=\"mb-3\">\n"
                    + "    <label for=\"modalActualizarEdad\" class=\"form-label\">Edad</label>\n"
                    + "    <input type=\"number\" class=\"form-control\" name=\"edad\" id=\"modalActualizarEdad\">\n"
                    + "  </div>\n"
                    + "  <button type=\"submit\" class=\"btn btn-primary\">Crear</button>\n"
                    + "</form>"
                    + "      </div>\n"
                    + "      <div class=\"modal-footer\">\n"
                    + "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancelar</button>\n"
                    + "      </div>\n"
                    + "    </div>\n"
                    + "  </div>\n"
                    + "</div>"
                    + ""
                    + "</div>"
                    + "<script>"
                    + "function eliminar(id, nombre){"
                    + "document.getElementById('modalBorrarTexto').innerHTML = `El usuario <b>${nombre}</b> se eliminara del todo para siempre. ¿Continuar?`;"
                    + "document.getElementById('modalBorrarBtn').onclick = () => {location.href = 'Eliminar?id='+id};"
                    + "}"
                    + "function actualizar(id, nombre, edad){"
                    + "document.getElementById('modalActualizarNombre').value = nombre;"
                    + "document.getElementById('modalActualizarEdad').value = edad;"
                    + "document.getElementById('modalActualizarId').value = id;"
                    + "}"
                    + "</script>"
                    + " </body>\n"
                    + "</html>"
            );
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
