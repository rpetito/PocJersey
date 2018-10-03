import model.AssignmentItem;
import model.Grade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by rodrigo on 30/09/18.
 */
public class AssignmentTest {

    private String token;

    @Before
    public void setUp(){
        this.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMTEyMjIzMzMiLCJybmQiOiJ5SXNmZFIwN2lIR3BRRmVjYU9KT2VRPT0ifQ.9pVJGUXhrJPQ-TptNCt971l0h_1dWqWgMrHAWXJchho";
    }

    @Test
    public void obtenerNotasCorrectamente() {
        List<AssignmentItem> assignments = new StudentDAO().getNotas(token);
        for(AssignmentItem a : assignments) {
            System.out.println(a.getId());
            System.out.println(a.getTitle());
            System.out.println(a.getDescription());
            for(Grade g : a.getGrades()) {
                System.out.println(g.getId());
                System.out.println(g.getCreatedDate());
                System.out.println(g.getUpdatedDate());
            }
        }
    }


    @Test(expected = ServiceErrorException.class)
    public void obtenerNotasUsuarioInexistente() {
        List<AssignmentItem> assignments = new StudentDAO().getNotas("token inexistente");
        for(AssignmentItem a : assignments) {
            System.out.println(a.getId());
            System.out.println(a.getTitle());
            System.out.println(a.getDescription());
            for(Grade g : a.getGrades()) {
                System.out.println(g.getId());
                System.out.println(g.getCreatedDate());
                System.out.println(g.getUpdatedDate());
            }
        }
    }


}
