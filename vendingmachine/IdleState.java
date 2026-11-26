package vendingmachine;

public class IdleState implements StateOfVendingMachine {
    private VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectSnack(String snackName) {
        Snack snack = machine.getSnackByName(snackName);
        if (snack == null) {
            System.out.println("Cant find snack: " + snackName);
        } else {
            machine.setChosenSnack(snack);
            machine.setCurrentState(new WaitingForMoneyState(machine));
            System.out.println("You picked: " + snack.getName() + " || price: $" + snack.getPrice() + ", pls insert money");
        }
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Pls pick a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Pls pick a snack first.");
    }
}
