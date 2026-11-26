package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine currentState;
    private List<Snack> inventory;
    private Snack chosenSnack;
    private double currentBalance;
    private SnackDispenseHandler dispenserChain;

    public VendingMachine() {
        inventory = new ArrayList<>();
        currentState = new IdleState(this);
        currentBalance = 0;
    }

    public void addSnack(Snack snack) {
        inventory.add(snack);
    }

    public Snack getSnackByName(String name) {
        Snack found = null;
        for (int i = 0; i < inventory.size(); i++) {
            Snack item = inventory.get(i);
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                found = item;
            }
        }
        return found;
    }

    public void selectSnack(String snackName) {
        currentState.selectSnack(snackName);
    }

    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseSnack() {
        currentState.dispenseSnack();
    }

    public StateOfVendingMachine getCurrentState() {
        return currentState;
    }

    public void setCurrentState(StateOfVendingMachine currentState) {
        this.currentState = currentState;
    }

    public Snack getChosenSnack() {
        return chosenSnack;
    }

    public void setChosenSnack(Snack chosenSnack) {
        this.chosenSnack = chosenSnack;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void addBalance(double amount) {
        currentBalance = currentBalance + amount;
    }

    public SnackDispenseHandler getDispenserChain() {
        return dispenserChain;
    }

    public void setDispenserChain(SnackDispenseHandler dispenserChain) {
        this.dispenserChain = dispenserChain;
    }

    public void clearTransaction() {
        chosenSnack = null;
        currentBalance = 0;
    }
}
