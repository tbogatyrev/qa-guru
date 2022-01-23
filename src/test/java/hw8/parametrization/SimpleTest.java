package hw8.parametrization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Класс с тестами проверяющими 3 и 2")
public class SimpleTest {

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
}
