/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.controller;

import com.b0ve.daw.proyecto.helpers.EstadoArticulo;
import com.b0ve.daw.proyecto.helpers.VisibilidadComentario;
import com.b0ve.daw.proyecto.model.Articulo;
import com.b0ve.daw.proyecto.model.CodigoActivacion;
import com.b0ve.daw.proyecto.model.Usuario;
import com.b0ve.daw.proyecto.model.Categoria;
import com.b0ve.daw.proyecto.model.Comentario;
import com.b0ve.daw.proyecto.service.helpers.B0vEMailingApiServicio;
import com.b0ve.daw.proyecto.service.helpers.CaptchaServicio;
import com.b0ve.daw.proyecto.service.helpers.EncriptacionServicio;
import com.b0ve.daw.proyecto.service.helpers.MailingServicio;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author borja
 */
@WebServlet(name = "ApiController", urlPatterns = {"/api/*"})
public class ApiController extends HttpServlet {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private final EncriptacionServicio encriptacionServicio = EncriptacionServicio.instance();
    private final B0vEMailingApiServicio mailingService = B0vEMailingApiServicio.instance();
    private final CaptchaServicio captchaServicio = CaptchaServicio.instance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = null;
        JSONObject msg = new JSONObject();

        try {
            String path = request.getPathInfo() == null ? "/" : request.getPathInfo();

            switch (path) {
                case "/logout":
                    request.getSession().invalidate();
                    break;
                default:
                    error = "404";
            }
        } catch (Exception e) {
            error = e.getMessage();
        }

        respond(response, error, msg);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = null;
        JSONObject msg = new JSONObject();

        try {
            String path = request.getPathInfo() == null ? "/" : request.getPathInfo();
            JSONObject payload = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
            switch (path) {
                case "/usuario":
                    regisrarUsuario(payload);
                    break;
                case "/activar":
                    msg.put("activado", activarUsuario(payload.getString("codigo")));
                    break;
                case "/login":
                    try {
                    Usuario u = iniciarSesion(payload.getString("email"), payload.getString("pass"));
                    if (u != null) {
                        msg.put("id", u.getId());
                        if (msg.has("id")) {
                            request.getSession().setAttribute("id", u.getId());
                            request.getSession().setAttribute("nombre", u.getNombre());
                            msg.put("activated", true);
                        }
                    }

                } catch (IllegalStateException e) {
                    msg.put("activated", false);
                }
                break;
                case "/publicar":
                    requireLogin(request);
                    msg.put("id", publicarArticulo(payload, loggedId(request)));
                    break;
                case "/is-faved":
                    requireLogin(request);
                    msg.put("faved", isFaved(payload.getLong("articulo"), loggedId(request)));
                    break;
                case "/toggle-faved":
                    requireLogin(request);
                    msg.put("faved", toggleFaved(payload.getLong("articulo"), loggedId(request)));
                    break;
                case "/articulo":
                    List<Articulo> articulos = filtrarArticulos(payload);
                    System.out.println("N:" + articulos.size());
                    JSONArray aLista = new JSONArray();
                    for (Articulo articulo : articulos) {
                        aLista.put(new JSONObject()
                                .put("id", articulo.getId())
                                .put("nombre", articulo.getNombre())
                                .put("precio", articulo.getPrecio())
                                .put("imagen", new String(articulo.getImagen(), StandardCharsets.UTF_8)));
                    }
                    msg.put("articulos", aLista);
                    break;
                case "/intereses":
                    requireLogin(request);
                    List<Articulo> intereses = filtrarIntereses(loggedId(request));
                    JSONArray iLista = new JSONArray();
                    for (Articulo interes : intereses) {
                        iLista.put(new JSONObject()
                                .put("id", interes.getId())
                                .put("nombre", interes.getNombre())
                                .put("precio", interes.getPrecio())
                                .put("imagen", new String(interes.getImagen(), StandardCharsets.UTF_8)));
                    }
                    msg.put("articulos", iLista);
                    break;
                case "/datos-usuario":
                    Usuario usuario = findUsuario(payload.getLong("usuario"));
                    msg.put("id", usuario.getId())
                            .put("nombre", usuario.getNombre())
                            .put("cp", usuario.getCp())
                            .put("direccion", usuario.getDireccion())
                            .put("email", usuario.getEmail())
                            .put("facebook", usuario.getFacebook())
                            .put("twitter", usuario.getTwitter())
                            .put("telefono", usuario.getTelefono());
                    break;
                case "/comentar":
                    requireLogin(request);
                    comentar(payload.getLong("articulo"), payload.getString("texto"), payload.getString("visibilidad"), loggedId(request));
                    break;
                case "/recuperar-pass":
                    enviarEmailRecuperacion(payload.getString("email"), request);
                    break;
                case "/cambiar-pass":
                    cambiarPassRecuperada(payload.getString("secreto"), payload.getString("pass"));
                    break;
                case "/carrito":
                    List<Articulo> carrito = new ArrayList<>();
                    List<String> base64Images = new ArrayList<>();
                    System.out.println(payload.toString());
                    for (Object object : payload.getJSONArray("carrito")) {
                        Long id = Long.valueOf((int) object);
                        Articulo articulo = em.find(Articulo.class, id);
                        if (articulo != null) {
                            carrito.add(articulo);
                            base64Images.add(new String(articulo.getImagen(), StandardCharsets.UTF_8));
                        }
                    }
                    request.setAttribute("carrito", carrito);
                    request.setAttribute("base64Images", base64Images);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/carritoContenido.jsp");
                    rd.forward(request, response);
                    return;
                default:
                    error = "404";
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
        }

        respond(response, error, msg);
    }

    private void requireLogin(HttpServletRequest request) {
        Long id = loggedId(request);
        if (id == null) {
            throw new IllegalStateException("Es necesario iniciar sesión");
        }
        Usuario u = em.find(Usuario.class, id);
        if (u == null) {
            throw new IllegalStateException("La sesion no es valida. Me la han intentado colar.");
        }
    }

    private Long loggedId(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute("id");
    }

    private void regisrarUsuario(JSONObject datos) {
        String gRecaptchaResponse = datos.getString("captcha");
        try {
            boolean verificado = captchaServicio.verificar(gRecaptchaResponse);
            if (!verificado) {
                throw new IllegalStateException("La captcha no es correcta.");
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Error al verificar chaptcha: " + ex.getMessage());
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(datos.getString("email"));
        usuario.setNombre(datos.getString("nombre"));
        usuario.setPass(encriptacionServicio.encriptar(datos.getString("pass")));
        usuario.setCp(datos.getInt("cp"));
        usuario.setTelefono(datos.getString("telefono"));
        if (datos.get("direccion") != JSONObject.NULL) {
            usuario.setDireccion(datos.getString("direccion"));
        }
        if (datos.get("facebook") != JSONObject.NULL) {
            usuario.setFacebook(datos.getString("facebook"));
        }
        if (datos.get("twitter") != JSONObject.NULL) {
            usuario.setTwitter(datos.getString("twitter"));
        }
        persist(usuario);
        CodigoActivacion codigo = new CodigoActivacion();
        codigo.setCodigo(encriptacionServicio.codigoAleatorio());
        codigo.setUsuario(usuario);
        persist(codigo);

        mailingService.sendMail(usuario.getEmail(), "Cuenta creada en Proyecto DAW", "Utiliza este código para verificar tu cuenta: " + codigo.getCodigo());
    }

    private boolean activarUsuario(String codigo) {
        TypedQuery<CodigoActivacion> q = em.createNamedQuery("CodigoActivacion.findByCodigo", CodigoActivacion.class);
        q.setParameter("codigo", codigo);
        try {
            CodigoActivacion codigoActivacion = q.getSingleResult();
            remove(codigoActivacion);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    private Usuario iniciarSesion(String email, String pass) {
        TypedQuery<CodigoActivacion> qc = em.createNamedQuery("CodigoActivacion.findByEmailAndPass", CodigoActivacion.class);
        qc.setParameter("email", email);
        qc.setParameter("pass", encriptacionServicio.encriptar(pass));
        try {
            CodigoActivacion c = qc.getSingleResult();
            throw new IllegalStateException("Usuario no activado");
        } catch (NoResultException e) {
        }

        TypedQuery<Usuario> qu = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        qu.setParameter("email", email);
        try {
            Usuario u = qu.getSingleResult();
            if (encriptacionServicio.comparar(u.getPass(), pass)) {
                return u;
            }
        } catch (NoResultException e) {
        }
        return null;
    }

    private Long publicarArticulo(JSONObject datos, Long usuarioId) {
        Categoria c = em.find(Categoria.class, datos.getLong("categoria"));
        if (c == null) {
            throw new IllegalStateException("Categoria no valida");
        }

        Usuario u = em.find(Usuario.class, usuarioId);
        if (u == null) {
            throw new IllegalStateException("Usuario no valido. ¿Como?");
        }

        Articulo articulo = new Articulo();
        articulo.setNombre(datos.getString("nombre"));
        if (datos.get("descripcion") != JSONObject.NULL) {
            articulo.setDescripcion(datos.getString("descripcion"));
        }
        if (datos.get("ano") != JSONObject.NULL) {
            articulo.setAno(datos.getInt("ano"));
        }
        if (datos.get("estado") != JSONObject.NULL) {
            articulo.setEstado(EstadoArticulo.valueOf(datos.getString("estado").toUpperCase()));
        }
        articulo.setPrecio(datos.getFloat("precio"));
        articulo.setCategoria(c);
        articulo.setImagen(limitarImagen(datos.getString("imagen")).getBytes(StandardCharsets.UTF_8));
        articulo.setUsuario(u);
        articulo.setFecha(new Date());

        persist(articulo);
        return articulo.getId();
    }

    private String limitarImagen(String sourceData) {
        try {
            String[] parts = sourceData.split(",");
            String imageString = parts[1];

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] imageByte = decoder.decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            BufferedImage image = ImageIO.read(bis);
            bis.close();

            BufferedImage outImg = new BufferedImage(512, 512, image.getType());
            float scale;
            if (image.getWidth() / (float) image.getHeight() > outImg.getWidth() / (float) outImg.getHeight()) {
                scale = outImg.getHeight() / (float) image.getHeight();
            } else {
                scale = outImg.getWidth() / (float) image.getWidth();
            }
            outImg.getGraphics().drawImage(image, (int) ((outImg.getWidth() - image.getWidth() * scale) / 2), (int) ((outImg.getHeight() - image.getHeight() * scale) / 2), (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), null);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(outImg, "jpg", os);
            return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
            return sourceData;
        }
    }

    private boolean isFaved(Long articuloId, Long usuarioId) {
        Articulo a = em.find(Articulo.class, articuloId);
        if (a == null) {
            throw new IllegalStateException("Articulo no valida");
        }

        Usuario u = em.find(Usuario.class, usuarioId);
        if (u == null) {
            throw new IllegalStateException("Usuario no valido. ¿Como?");
        }

        return u.getIntereses().contains(a);
    }

    private boolean toggleFaved(Long articuloId, Long usuarioId) {
        Articulo a = em.find(Articulo.class, articuloId);
        if (a == null) {
            throw new IllegalStateException("Articulo no valida");
        }

        Usuario u = em.find(Usuario.class, usuarioId);
        if (u == null) {
            throw new IllegalStateException("Usuario no valido. ¿Como?");
        }

        boolean isFaved = u.getIntereses().contains(a);
        if (isFaved) {
            u.getIntereses().remove(a);
        } else {
            u.getIntereses().add(a);
        }
        merge(u);

        return !isFaved;
    }

    private List<Articulo> filtrarArticulos(JSONObject filtro) {
        List<String> conditions = new ArrayList<>();

        if (filtro.get("categoria") != JSONObject.NULL) {
            conditions.add(":categoria = a.categoria.id");
        }
        if (filtro.get("cp") != JSONObject.NULL) {
            conditions.add(":cp = u.cp");
        }
        if (filtro.get("precioMin") != JSONObject.NULL) {
            conditions.add(":precioMin <= a.precio");
        }
        if (filtro.get("precioMax") != JSONObject.NULL) {
            conditions.add(":precioMax >= a.precio");
        }

        TypedQuery<Articulo> q = em.createQuery("SELECT a FROM Articulo a INNER JOIN Usuario u ON(a.usuario = u) " + buildWhere(conditions) + " ORDER BY a.fecha DESC", Articulo.class);

        if (filtro.get("categoria") != JSONObject.NULL) {
            q.setParameter("categoria", filtro.getLong("categoria"));
        }
        if (filtro.get("cp") != JSONObject.NULL) {
            q.setParameter("cp", filtro.getInt("cp"));
        }
        if (filtro.get("precioMin") != JSONObject.NULL) {
            q.setParameter("precioMin", filtro.getFloat("precioMin"));
        }
        if (filtro.get("precioMax") != JSONObject.NULL) {
            q.setParameter("precioMax", filtro.getFloat("precioMax"));
        }

        int pagina = filtro.getInt("pagina");
        int cantidad = filtro.getInt("cantidad");
        q.setFirstResult(pagina * cantidad);
        q.setMaxResults(cantidad);

        return q.getResultList();
    }

    private List<Articulo> filtrarIntereses(Long usuario) {
        TypedQuery<Articulo> q = em.createQuery("SELECT u.intereses FROM Usuario u WHERE u.id = :usuario", Articulo.class);
        q.setParameter("usuario", usuario);

        return q.getResultList();
    }

    private String buildWhere(List<String> conditions) {
        if (conditions.isEmpty()) {
            return " ";
        }
        StringBuilder sb = new StringBuilder(" WHERE ");
        for (Iterator<String> iterator = conditions.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            sb.append(next);
            if (iterator.hasNext()) {
                sb.append(" AND ");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private Usuario findUsuario(Long id) {
        return em.find(Usuario.class, id);
    }

    private void comentar(long articuloId, String texto, String visibilidad, Long usuarioId) {
        Articulo a = em.find(Articulo.class, articuloId);
        if (a == null) {
            throw new IllegalStateException("Articulo no valida");
        }

        Usuario u = em.find(Usuario.class, usuarioId);
        if (u == null) {
            throw new IllegalStateException("Usuario no valido.");
        }

        Comentario c = new Comentario();
        c.setTexto(texto);
        c.setUsuario(u);
        c.setVisibilidad(VisibilidadComentario.valueOf(visibilidad.toUpperCase()));
        c.setArticulo(a);
        persist(c);
        a.getComentarios().add(c);
        merge(a);
    }

    private void enviarEmailRecuperacion(String email, HttpServletRequest request) {
        TypedQuery<Usuario> qu = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        qu.setParameter("email", email);
        try {
            Usuario u = qu.getSingleResult();
            String codigo = encriptacionServicio.coficarSecreto(u.getId().toString());
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/Proyecto/recuperar/" + codigo;
            mailingService.sendMail(email, "Recuperación de contraseña", "Se ha solicitado la recuperación de su contraseña. Utilice el siguiente enlace para cambiar la contraseña: <a href='" + url + "'>" + url + "</a>");
        } catch (NoResultException e) {
            throw new IllegalStateException("No existe ningun usuario con el correo: " + email);
        }
    }

    private void cambiarPassRecuperada(String secreto, String pass) {
        String idString = encriptacionServicio.decodificarSecreto(secreto);
        try {
            Long id = Long.parseLong(idString);
            Usuario u = em.find(Usuario.class, id);
            if (u == null) {
                throw new IllegalStateException("No se ha encontrado al usuario asociado al codigo de recuperacion");
            }
            u.setPass(encriptacionServicio.encriptar(pass));
            merge(u);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("El enlace de recuperacion no es correcto");
        }
    }

    private void respond(HttpServletResponse response, String error, JSONObject msg) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (error == null) {
                out.print(new JSONObject()
                        .put("status", "success")
                        .put("msg", msg)
                        .toString());
            } else {
                out.print(new JSONObject()
                        .put("status", "error")
                        .put("msg", new JSONObject().put("error", error))
                        .toString());
            }
            out.flush();
        }
    }

    private void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private void remove(Object object) {
        try {
            utx.begin();
            object = em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
