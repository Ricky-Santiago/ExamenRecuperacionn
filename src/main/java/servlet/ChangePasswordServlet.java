package servlet;

import dao.MedicoJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.Medico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.HashUtil;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

    private MedicoJpaController controller;

    @Override
    public void init() throws ServletException {
        controller = new MedicoJpaController();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html?error=nosession");
            return;
        }

        Medico usuario = (Medico) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            response.sendRedirect("login.html?error=nosession");
            return;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        if (oldPassword == null || newPassword == null || oldPassword.isEmpty() || newPassword.isEmpty()) {
            response.sendRedirect("change_password.html?error=empty");
            return;
        }

        try {
            // Verificar que la clave actual sea correcta
            if (!HashUtil.checkPassword(oldPassword, usuario.getPassMedi())) {
                response.sendRedirect("change_password.html?error=wrongold");
                return;
            }

            // Hashear la nueva contraseña
            String hashedNewPassword = HashUtil.hashPassword(newPassword);

            // Actualizar la entidad usuario
            usuario.setPassMedi(hashedNewPassword);

            // Guardar cambios en la base de datos
            controller.edit(usuario);

            // Actualizar la sesión con el usuario modificado
            session.setAttribute("usuarioLogueado", usuario);

            response.sendRedirect("change_password.html?success=1");

        } catch (NonexistentEntityException ex) {
            response.sendRedirect("change_password.html?error=nouser");
        } catch (IllegalArgumentException ex) {
            response.sendRedirect("change_password.html?error=hash");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("change_password.html?error=server");
        }
    }
}
