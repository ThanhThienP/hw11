package vendingmachine;

public class DispensingSnackState implements StateOfVendingMachine {
    private VendingMachine machine;

    public DispensingSnackState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Wait a sec, dispensing snack rn.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Wait a sec, dispensing snack rn.");
    }

    @Override
    public void dispenseSnack() {
        Snack snack = machine.getChosenSnack();
        SnackDispenseHandler handler = machine.getDispenserChain();

        if (snack != null && handler != null && handler.dispense(snack)) {
            double change = machine.getCurrentBalance() - snack.getPrice();
            if (change > 0) {
                System.out.printf("Change returned: $%.2f%n", change);
            }
        } else {
            System.out.printf("Returning your money: $%.2f%n", machine.getCurrentBalance());
        }

        machine.clearTransaction();
        machine.setCurrentState(new IdleState(machine));
    }
}
