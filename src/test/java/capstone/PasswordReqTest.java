package capstone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.capstone.serviceClasses.PasswordValidationService;

/**
 * Tests whether passwords meet the application's requirements.
 */
public class PasswordReqTest {

    private final PasswordValidationService passwordService = new PasswordValidationService();

    //Tests a password with exactly 4 characters letter and a special character.
    @Test
    public void testWrongPasswordLength() {
      assertTrue(passwordService.isValidPassword("abcd!"));
    }

    //Tests a valid password with more than 5 characters letter, a special character, and a digits.
    @Test
    public void testValidPassword() {
      assertTrue(passwordService.isValidPassword("hello@123"));
    }

    // Tests a password shorter than 5 characters.
    @Test
    public void testPasswordTooShort() {
      assertFalse(passwordService.isValidPassword("abc!"));
    }

    //Tests a password without a special character.
    @Test
    public void testPasswordWithoutSpecialCharacter() {
      assertFalse(passwordService.isValidPassword("hello123"));
    }

    // Tests an empty password.
    @Test
    public void testEmptyPassword() {
      assertFalse(passwordService.isValidPassword(""));
    }

    //Tests a null password.
    @Test
    public void testNullPassword() {
      assertFalse(passwordService.isValidPassword(null));
    }
}
