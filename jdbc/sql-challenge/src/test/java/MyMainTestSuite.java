import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("My Main Test Suite")
public class MyMainTestSuite {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All Test Cases");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All Test Cases");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before Each Test Case");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each Test Case");
    }

    @Order(2)
    @RepeatedTest(2)
    @DisplayName("Add New Customer")
    void testCase1() {
        System.out.println("Test Case 1");
    }

    @Order(3)
    @Test
    void testCase2() {
        System.out.println("Test Case 2");
    }

    @Order(1)
    @Test
    void abc(){
        System.out.println("Test Abc");
    }

    @Nested
    class NestedTestSuite1{
        @BeforeEach
        void setUp() {
            System.out.println("Before Each Test Case in Nested Test Suite 1");
        }

        @AfterEach
        void tearDown() {
            System.out.println("After Each Test Case in Nested Test Suite 1");
        }

        @Test
        void testCase3() {
            System.out.println("Test Case 3");
        }

        @Test
        void testCase4() {
            System.out.println("Test Case 4");
        }
    }
}
