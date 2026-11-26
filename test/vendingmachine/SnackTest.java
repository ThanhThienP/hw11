package vendingmachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {

    @Test
    public void testSnackInfo() {
        Snack snack = new Snack("Tra Sua", 4.50, 3);
        assertEquals("Tra Sua", snack.getName());
        assertEquals(4.50, snack.getPrice(), 0.01);
        assertEquals(3, snack.getQuantity());
    }

    @Test
    public void testDecreaseQuantity() {
        Snack snack = new Snack("Banh Mi", 6.25, 2);
        snack.decreaseQuantity();
        assertEquals(1, snack.getQuantity());
    }

    @Test
    public void testQuantityStayAtZero() {
        Snack snack = new Snack("Snickers", 2.00, 0);
        snack.decreaseQuantity();
        assertEquals(0, snack.getQuantity());
    }
}
