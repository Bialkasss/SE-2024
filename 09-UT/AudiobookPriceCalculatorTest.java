package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {

    private Audiobook audiobook;
    private Customer customer;
    private AudiobookPriceCalculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("title", 50);
    }

    @Test
    public void testCalculateSubscribers(){
        customer = new Customer("name1", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testCalculateSilver(){
        customer = new Customer("name2", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(0.9 * 50, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testCalculateGold(){
        customer = new Customer("name3", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(0.8 * 50, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testCalculateStandard(){
        customer = new Customer("name4", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(50, calculator.calculate(customer, audiobook));

    }
}