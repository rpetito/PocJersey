import jdk.nashorn.internal.ir.Assignment;
import model.AssignmentData;
import model.AssignmentItem;
import model.Student;

import java.util.List;

/**
 * Created by rodrigo on 30/09/18.
 */
public class StudentDAO {

    public Student login(String token) {
        ServiceManager<Student> sm = new ServiceManager<Student>(Student.class);
        return sm.get(token, "/student");
    }

    public List<AssignmentItem> getNotas(String token) {
        ServiceManager<AssignmentData> sm = new ServiceManager<AssignmentData>(AssignmentData.class);
        return sm.get(token, "/student/assignments").getAssignments();
    }


    public List<AssignmentItem> actualizarNotas(String token, List<AssignmentItem> notasNuevas) {
        ServiceManager<AssignmentData> sm = new ServiceManager<AssignmentData>(AssignmentData.class);
        AssignmentData dataNueva = new AssignmentData();
        dataNueva.setAssignments(notasNuevas);
        sm.put(token,"/student", dataNueva);
        return dataNueva.getAssignments();
    }

    public Student actualizarEstudiante(String token, Student estudianteActualizado) {
        ServiceManager<Student> sm = new ServiceManager<Student>(Student.class);
        sm.put(token, "/student", estudianteActualizado);
        return estudianteActualizado;
    }

}
