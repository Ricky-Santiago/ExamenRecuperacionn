package servlet;

import dao.MedicoJpaController;
import dto.Medico;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "EditarMedico", urlPatterns = {"/editarMedico"})
public class EditarMedico extends HttpServlet {

    private MedicoJpaController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");
        controller = new MedicoJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros
        String idStr = request.getParameter("codiMedi");
        String dni = request.getParameter("ndniMedi");
        String appa = request.getParameter("appaMedi");
        String apma = request.getParameter("apmaMedi");
        String nombre = request.getParameter("nombMedi");
        String fechaStr = request.getParameter("fechNaciMedi");
        String login = request.getParameter("logiMedi");
        // Para editar la contraseña, si quieres manejarla, podrías agregar aquí

        if (idStr == null || dni == null || appa == null || apma == null || nombre == null
                || fechaStr == null || login == null
                || idStr.isEmpty() || dni.isEmpty() || appa.isEmpty() || apma.isEmpty()
                || nombre.isEmpty() || fechaStr.isEmpty() || login.isEmpty()) {
            response.sendRedirect("tables.html?error=missing");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaStr);

            Medico medico = controller.findMedico(id);
            if (medico == null) {
                response.sendRedirect("tables.html?error=notfound");
                return;
            }

            medico.setNdniMedi(dni);
            medico.setAppaMedi(appa);
            medico.setApmaMedi(apma);
            medico.setNombMedi(nombre);
            medico.setFechNaciMedi(fechaNacimiento);
            medico.setLogiMedi(login);

            // Aquí podrías actualizar la contraseña si la recibes y quieres hacerlo

            controller.edit(medico);

            response.sendRedirect("tables.html?msg=editado");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("tables.html?error=exception");
        }
    }
}
