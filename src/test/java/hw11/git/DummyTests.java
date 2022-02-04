package hw11.git;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTests {

    @Test
    public void dummyTest1() {
        assertEquals("10", String.valueOf(Math.max(5, 10)));
    }

    @Test
    public void dummyTest2() {
        assertEquals("15", String.valueOf(Math.max(5, 15)));
    }

    @Test
    public void dummyTest3() {
        assertEquals("30", String.valueOf(Math.max(8, 30)));
    }
}
