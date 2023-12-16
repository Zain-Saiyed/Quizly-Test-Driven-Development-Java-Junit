import org.example.Auth_verification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Auth_verification_test {

    @Test
    public void  execute_select_login_test() {
        Auth_verification auth_verification = new Auth_verification();
        boolean result = auth_verification.login_authenticate("test_user@dal.ca","test_user123");
        assertTrue(result);
        System.out.println("------");
    }
    @Test
    public void  verify_input_test() {
        Auth_verification verification = new Auth_verification();
        boolean result = verification.verify_input("What is the capital of Canada?");
        assertTrue(result);
        System.out.println("------");
    }
    @Test
    public void  verify_input_test_email_condition() {
        Auth_verification verification = new Auth_verification();
        boolean result = verification.verify_input("test_user@dal.ca","email_id");
        assertTrue(result);
        System.out.println("------");
    }
    @Test
    public void  verify_input_dtype_test() {
        Auth_verification verification = new Auth_verification();
        boolean result = verification.verify_input_dtype("25 ".strip(),"int");
        assertTrue(result);
        System.out.println("------");
    }
}
