package put.io.testing.junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void steUp(){
        calculator = new Calculator();
    }

    @Test
    public void testAdd(){
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-10, calculator.add(-12, 2));
        assertEquals(7, calculator.add(7, 0));
    }

    @Test
    public void testMultiply(){
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(25, calculator.multiply(5, 5));
        assertEquals(56, calculator.multiply(7, 8));
    }

    @Test
    public void testAddPositiveNumbersException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-10, 20);
        });

        //to check if we have specific message thrown
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            calculator.addPositiveNumbers(-2, 3);
//        });
//
//        // Verify the exception message
//        assertEquals("The arguments must be positive", thrown.getMessage());
    }
}