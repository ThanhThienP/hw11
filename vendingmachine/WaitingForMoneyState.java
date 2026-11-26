package vendingmachine;

public class WaitingForMoneyState implements StateOfVendingMachine {
    private VendingMachine machine;

    public WaitingForMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Already picked a snack, pls insert money.");
    }

    @Override
    public void insertMoney(double amount) {
        if (amount < 0 || amount == 0) {
            System.out.println("Invalid money amount.");
        } else {
            machine.addBalance(amount);
            Snack snack = machine.getChosenSnack();
            System.out.println("Inserted $" + amount + " | total: $" + machine.getCurrentBalance());

            if (machine.getCurrentBalance() >= snack.getPrice()) {
                machine.setCurrentState(new DispensingSnackState(machine));
                machine.dispenseSnack();
            } else {
                double needMore = snack.getPrice() - machine.getCurrentBalance();
                System.out.printf("Not enought money, need $%.2f more.%n", needMore);
            }
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Not enought money inserted yet.");
    }
}
