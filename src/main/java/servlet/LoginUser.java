package servlet;

import com.google.gson.Gson;
import dao.MedicoJpaController;
import dto.Medico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.HashUtil;

@WebServlet(name = "LoginUser", urlPatterns = {"/loginuser"})
public class LoginUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("ndniMedi");
        String password = request.getParameter("passMedi");

        if (dni == null || password == null || dni.isEmpty() || password.isEmpty()) {
            response.sendRedirect("login.html?error=1");
            return;
        }

        MedicoJpaController controller = new MedicoJpaController();
        Medico medico = null;

        try {
            medico = controller.getEntityManager()
                    .createQuery("SELECT m FROM Medico m WHERE m.ndniMedi = :ndniMedi", Medico.class)
                    .setParameter("ndniMedi", dni)
                    .getSingleResult();
        } catch (Exception e) {
            medico = null;
        }

        if (medico != null) {
            boolean passCorrecta = false;
            try {
                passCorrecta = HashUtil.checkPassword(password, medico.getPassMedi());
            } catch (IllegalArgumentException ex) {
                response.sendRedirect("login.html?error=hash");
                return;
            }

            if (passCorrecta) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuarioLogueado", medico);
                response.sendRedirect("tables.html");
            } else {
                response.sendRedirect("login.html?error=pass");
            }
        } else {
            response.sendRedirect("login.html?error=usuario");
        }
    }

    // Nuevo: manejar GET para devolver usuario logueado en JSON
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Medico medico = null;

        if (session != null) {
            medico = (Medico) session.getAttribute("usuarioLogueado");
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        if (medico != null) {
            String json = new Gson().toJson(new UsuarioDTO(medico.getNombMedi())); // ajusta este getter si es necesario
            response.getWriter().write(json);
        } else {
            response.getWriter().write("{\"nombre\":\"Invitado\"}");
        }
    }

    private static class UsuarioDTO {
        private String nombre;

        public UsuarioDTO(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
