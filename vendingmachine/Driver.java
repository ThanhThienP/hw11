package vendingmachine;

public class Driver {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // add the 6 snacks
        Snack s1 = new Snack("Coke", 1.75, 3);
        Snack s2 = new Snack("Pepsi", 1.50, 2);
        Snack s3 = new Snack("Cheetos", 2.25, 4);
        Snack s4 = new Snack("Doritos", 2.50, 3);
        Snack s5 = new Snack("KitKat", 1.25, 2);
        Snack s6 = new Snack("Snickers", 2.00, 2);

        machine.addSnack(s1);
        machine.addSnack(s2);
        machine.addSnack(s3);
        machine.addSnack(s4);
        machine.addSnack(s5);
        machine.addSnack(s6);

        // chain order: Coke -> Pepsi -> Cheetos -> Doritos -> KitKat -> Snickers
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
        machine.setDispenserChain(coke);

        // try putting money before picking anything
        machine.insertMoney(1.00);
        System.out.println();

        machine.selectSnack("Boba Tea");
        System.out.println();

        // buy Doritos with 2 payments
        machine.selectSnack("Doritos");
        machine.insertMoney(1.00);
        machine.insertMoney(1.50);
        System.out.println();

        // buy Coke with extra cash
        machine.selectSnack("Coke");
        machine.insertMoney(2.00);
        System.out.println();

        // buy KitKat exact price
        machine.selectSnack("KitKat");
        machine.insertMoney(1.25);
        System.out.println();

        // buy Pepsi with $5 bill
        machine.selectSnack("Pepsi");
        machine.insertMoney(5.00);
        System.out.println();

        // buy Cheetos
        machine.selectSnack("Cheetos");
        machine.insertMoney(2.25);
        System.out.println();

        // buy Snickers (qty 2 -> 1)
        machine.selectSnack("Snickers");
        machine.insertMoney(2.00);
        System.out.println();

        // buy Snickers again so quantity hits 0
        machine.selectSnack("Snickers");
        machine.insertMoney(2.00);
        System.out.println();

        // try buying Snickers when qty is 0
        machine.selectSnack("Snickers");
        machine.insertMoney(2.00);
    }
}
