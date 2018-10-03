import model.Student;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rodrigo on 30/09/18.
 */
public class LoginTest {

    private String token;

    @Before
    public void setUp(){
        this.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMTEyMjIzMzMiLCJybmQiOiJ5SXNmZFIwN2lIR3BRRmVjYU9KT2VRPT0ifQ.9pVJGUXhrJPQ-TptNCt971l0h_1dWqWgMrHAWXJchho";
    }

    @Test
    public void loginCorrecto() {
        Student student = new StudentDAO().login(token);
        System.out.println("USUARIO LOGGEADO" + "\n");
        System.out.println("Code: " + student.getCode());
        System.out.println("Nombre: " + student.getFirstName());
        System.out.println("Apellido: " + student.getLastName());
        System.out.println("Usuario github: " + student.getGithubUser());
    }

    @Test(expected = ServiceErrorException.class)
    public void loginTokenIncorrecto() {
        new StudentDAO().login("token inexistente");
    }

}
