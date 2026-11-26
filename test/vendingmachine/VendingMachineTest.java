package vendingmachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine makeMachine() {
        VendingMachine vm = new VendingMachine();
        vm.addSnack(new Snack("Coke", 1.75, 3));
        vm.addSnack(new Snack("Pepsi", 1.50, 2));
        vm.addSnack(new Snack("Cheetos", 2.25, 4));
        vm.addSnack(new Snack("Doritos", 2.50, 3));
        vm.addSnack(new Snack("KitKat", 1.25, 2));
        vm.addSnack(new Snack("Snickers", 2.00, 2));

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
        vm.setDispenserChain(coke);

        return vm;
    }

    @Test
    public void testStartIdle() {
        VendingMachine vm = makeMachine();
        assertTrue(vm.getCurrentState() instanceof IdleState);
    }

    @Test
    public void testPickSnackMoveToWaiting() {
        VendingMachine vm = makeMachine();
        vm.selectSnack("Coke");

        assertNotNull(vm.getChosenSnack());
        assertEquals("Coke", vm.getChosenSnack().getName());
        assertTrue(vm.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void testSnackDoesNotExists() {
        VendingMachine vm = makeMachine();
        vm.selectSnack("Ca Phe Sua Da");

        assertNull(vm.getChosenSnack());
        assertTrue(vm.getCurrentState() instanceof IdleState);
    }

    @Test
    public void testInsertBeforePick() {
        VendingMachine vm = makeMachine();
        vm.insertMoney(2.00);

        assertEquals(0.0, vm.getCurrentBalance(), 0.01);
        assertTrue(vm.getCurrentState() instanceof IdleState);
    }

    @Test
    public void testNotEnoughtMoney() {
        VendingMachine vm = makeMachine();
        vm.selectSnack("Doritos");
        vm.insertMoney(1.00);

        assertEquals(1.00, vm.getCurrentBalance(), 0.01);
        assertEquals(3, vm.getSnackByName("Doritos").getQuantity());
        assertTrue(vm.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void testBuyWithMultipleInserts() {
        VendingMachine vm = makeMachine();
        vm.selectSnack("Doritos");
        vm.insertMoney(1.00);
        vm.insertMoney(1.50);

        assertEquals(2, vm.getSnackByName("Doritos").getQuantity());
        assertNull(vm.getChosenSnack());
        assertEquals(0.0, vm.getCurrentBalance(), 0.01);
        assertTrue(vm.getCurrentState() instanceof IdleState);
    }

    @Test
    public void testSnickersHitZeroAndRefund() {
        VendingMachine vm = makeMachine();

        vm.selectSnack("Snickers");
        vm.insertMoney(2.00);
        assertEquals(1, vm.getSnackByName("Snickers").getQuantity());

        vm.selectSnack("Snickers");
        vm.insertMoney(2.00);
        assertEquals(0, vm.getSnackByName("Snickers").getQuantity());

        // try buying again when qty is 0
        vm.selectSnack("Snickers");
        vm.insertMoney(2.00);

        assertEquals(0, vm.getSnackByName("Snickers").getQuantity());
        assertNull(vm.getChosenSnack());
        assertEquals(0.0, vm.getCurrentBalance(), 0.01);
        assertTrue(vm.getCurrentState() instanceof IdleState);
    }

    @Test
    public void testDriverMain() {
        assertDoesNotThrow(() -> Driver.main(new String[] {}));
    }
}
