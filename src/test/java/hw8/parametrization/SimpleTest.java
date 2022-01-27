package hw8.parametrization;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Класс с тестами проверяющими 3 и 2")
public class SimpleTest {

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
    }

    @AfterEach
    public void afterEach() {
    }

    @Test
    @DisplayName("Тест проверяет, что 3 < 2")
    void test() {
        assertTrue(3 < 2);
    }

    @Test
    @DisplayName("Тест проверяет, что 3 > 2")
    void test1() {
        assertTrue(3 > 2);
    }

    @AfterAll
    public static void afterAll() {
    }
}
