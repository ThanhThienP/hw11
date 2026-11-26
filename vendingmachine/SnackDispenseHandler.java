package vendingmachine;

public class SnackDispenseHandler {
    private String targetName;
    private SnackDispenseHandler next;

    public SnackDispenseHandler(String targetName) {
        this.targetName = targetName;
    }

    public void setNext(SnackDispenseHandler next) {
        this.next = next;
    }

    public boolean dispense(Snack snack) {
        boolean result = false;

        if (snack.getName().toLowerCase().equals(targetName.toLowerCase())) {
            if (snack.getQuantity() > 0) {
                snack.decreaseQuantity();
                System.out.println("Dispensing " + snack.getName() + " sucessfully! Remaining: " + snack.getQuantity());
                result = true;
            } else {
                System.out.println(snack.getName() + " is out of stock.");
                result = false;
            }
        } else {
            if (next != null) {
                result = next.dispense(snack);
            } else {
                System.out.println("Snack not supported: " + snack.getName());
                result = false;
            }
        }

        return result;
    }
}
