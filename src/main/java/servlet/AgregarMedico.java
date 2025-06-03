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
import util.HashUtil;

@WebServlet(name = "AgregarMedico", urlPatterns = {"/agregarMedico"})
public class AgregarMedico extends HttpServlet {

    private MedicoJpaController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_PractCripto_war_1.0-SNAPSHOTPU");
        controller = new MedicoJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros
        String dni = request.getParameter("ndniMedi");
        String appa = request.getParameter("appaMedi");
        String apma = request.getParameter("apmaMedi");
        String nombre = request.getParameter("nombMedi");
        String fechaStr = request.getParameter("fechNaciMedi");
        String login = request.getParameter("logiMedi");
        String password = request.getParameter("passMedi");

        if (dni == null || appa == null || apma == null || nombre == null
                || fechaStr == null || login == null || password == null
                || dni.isEmpty() || appa.isEmpty() || apma.isEmpty()
                || nombre.isEmpty() || fechaStr.isEmpty() || login.isEmpty() || password.isEmpty()) {
            response.sendRedirect("medicos.html?error=missing");
            return;
        }

        try {
            // Parsear fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaStr);

            // Hashear contraseña
            String hashedPass = HashUtil.hashPassword(password);

            // Crear objeto Medico
            Medico medico = new Medico();
            medico.setNdniMedi(dni);
            medico.setAppaMedi(appa);
            medico.setApmaMedi(apma);
            medico.setNombMedi(nombre);
            medico.setFechNaciMedi(fechaNacimiento);
            medico.setLogiMedi(login);
            medico.setPassMedi(hashedPass);

            // Guardar en BD
            controller.create(medico);

            // Redirigir a la página con mensaje de éxito
            response.sendRedirect("medicos.html?msg=agregado");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("medicos.html?error=exception");
        }
    }
}
