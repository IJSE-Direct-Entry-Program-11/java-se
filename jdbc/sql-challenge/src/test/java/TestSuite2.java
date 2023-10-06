import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSuite2 {

    @Test
    void testCase1() {
        System.out.println("Test Case1: " + this);
    }

    @Test
    void testCase2() {
        System.out.println("Test Case2: " + this);
    }
}
