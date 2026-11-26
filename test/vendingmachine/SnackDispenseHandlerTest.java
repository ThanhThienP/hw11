package vendingmachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackDispenseHandlerTest {

    private SnackDispenseHandler setupChain() {
        SnackDispenseHandler coke = new SnackDispenseHandler("Coke");
        SnackDispenseHandler pepsi = new SnackDispenseHandler("Pepsi");
        SnackDispenseHandler cheetos = new SnackDispenseHandler("Cheetos");
        SnackDispenseHandler doritos = new SnackDispenseHandler("Doritos");
        SnackDispenseHandler kitkat = new SnackDispenseHandler("KitKat");
        SnackDispenseHandler snickers = new SnackDispenseHandler("Snickers");

        coke.setNext(pepsi);
        pepsi.setNext(cheetos);
        cheetos.setNext(doritos);
        doritos.setNext(kitkat);
        kitkat.setNext(snickers);
        return coke;
    }

    @Test
    public void testFirstHandlerCoke() {
        SnackDispenseHandler chain = setupChain();
        Snack coke = new Snack("Coke", 1.75, 3);

        boolean ok = chain.dispense(coke);
        assertEquals(ok, true);
        assertEquals(2, coke.getQuantity());
    }

    @Test
    public void testMiddleHandlerKitKat() {
        SnackDispenseHandler chain = setupChain();
        Snack kitkat = new Snack("KitKat", 1.25, 2);

        assertTrue(chain.dispense(kitkat));
        assertEquals(1, kitkat.getQuantity());
    }

    @Test
    public void testLastHandlerSnickers() {
        SnackDispenseHandler chain = setupChain();
        Snack snickers = new Snack("Snickers", 2.00, 2);

        assertTrue(chain.dispense(snickers));
        assertEquals(1, snickers.getQuantity());
    }

    @Test
    public void testOutOfStockReturnFalse() {
        SnackDispenseHandler chain = setupChain();
        Snack pepsi = new Snack("Pepsi", 1.50, 0);

        assertFalse(chain.dispense(pepsi));
        assertEquals(0, pepsi.getQuantity());
    }

    @Test
    public void testUnknownVietnameseSnack() {
        SnackDispenseHandler chain = setupChain();
        Snack banhTrang = new Snack("Banh Trang", 3.00, 5);

        assertFalse(chain.dispense(banhTrang));
        assertEquals(5, banhTrang.getQuantity());
    }
}
