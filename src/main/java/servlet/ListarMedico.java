package servlet;

import com.google.gson.Gson;
import dao.MedicoJpaController;
import dto.Medico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ListarMedico", urlPatterns = {"/listarMedico"})
public class ListarMedico extends HttpServlet {

    private MedicoJpaController controller;

    @Override
    public void init() throws ServletException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");
            controller = new MedicoJpaController(emf);
        } catch (Exception e) {
            throw new ServletException("Error al inicializar el EntityManagerFactory", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Medico> medicos = controller.findMedicoEntities();
        List<Medico> medicosSinPass = new ArrayList<Medico>();

        for (Medico m : medicos) {
            Medico copia = new Medico();
            copia.setCodiMedi(m.getCodiMedi());
            copia.setNdniMedi(m.getNdniMedi());
            copia.setAppaMedi(m.getAppaMedi());
            copia.setApmaMedi(m.getApmaMedi());
            copia.setNombMedi(m.getNombMedi());
            copia.setFechNaciMedi(m.getFechNaciMedi());
            copia.setLogiMedi(m.getLogiMedi());
            // No copiamos la contraseña
            medicosSinPass.add(copia);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        String json = new Gson().toJson(medicosSinPass);
        response.getWriter().write(json);
    }
}
