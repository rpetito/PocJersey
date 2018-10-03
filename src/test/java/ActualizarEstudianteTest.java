import model.AssignmentItem;
import model.Grade;
import model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 30/09/18.
 */
public class ActualizarEstudianteTest {

    private Student estudiante;
    private List<AssignmentItem> assignemnts;
    private StudentDAO studentDAO;
    private String token;

    @Before
    public void setUp() {
        this.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMTEyMjIzMzMiLCJybmQiOiJ5SXNmZFIwN2lIR3BRRmVjYU9KT2VRPT0ifQ.9pVJGUXhrJPQ-TptNCt971l0h_1dWqWgMrHAWXJchho";
        this.studentDAO = new StudentDAO();
        this.estudiante = this.studentDAO.login(token);
        this.assignemnts = this.studentDAO.getNotas(token);
    }

    @Test
    public void actualizarNotasCorrectamente() {

        //Creo la materia a agregar
        AssignmentItem materia = new AssignmentItem();
        materia.setId("99");
        materia.setTitle("Test");
        materia.setDescription("Nota agregada");

        //Creo la nota de la materia nueva
        Grade notaAgragada = new Grade();
        notaAgragada.setId("99");
        notaAgragada.setValue("A");

        List<Grade> notasNuevas = new ArrayList<Grade>();
        notasNuevas.add(notaAgragada);

        //Agrego la nota a la materia nueva
        materia.setGrades(notasNuevas);

        //Agrego la materia con su nota
        this.assignemnts.add(materia);

        //Actualizo mediante PUT
        List<AssignmentItem> actualizadoResponse = this.studentDAO.actualizarNotas(token, this.assignemnts);

        //Obtengo nuevamente las materias y sus notas
        List<AssignmentItem> materiasActualizadas = this.studentDAO.getNotas(token);

        List<Grade> notasActualizadas = new ArrayList<Grade>();
        for(AssignmentItem a : materiasActualizadas) {
            notasActualizadas.addAll(a.getGrades());
        }

        //Verifico que entre todas las notas esté la nota agregada
        Assert.assertTrue(notasActualizadas.contains(notaAgragada));
    }



    @Test
    public void actualizarNombreCorrectamente() {

        //Cambio el nombre al estudiante que hay en el sistema
        this.estudiante.setFirstName("Actualizado Actualizado");
        this.estudiante.setLastName("Actualizado Otra Vez");
        this.estudiante.setGithubUser("actualizar@github.com");

        //Actualizo mediante PUT
        Student actualizarResponse = this.studentDAO.actualizarEstudiante(token, this.estudiante);

        //Obtengo nuevamente el estudiante
        Student estudianteActualizado = this.studentDAO.login(token);

        Assert.assertTrue(estudianteActualizado.getFirstName().equals(this.estudiante.getFirstName()));

    }

}
