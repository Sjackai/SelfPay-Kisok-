public class Main {
    public static void main(String[] args) {
        SelfPayKiosk kiosk = new SelfPayKiosk(); //create selfpayKiosk object which kiosk will hold
        Scanner scanner = new Scanner(System.in); //create scanner object as scanner to read input

        boolean running = true; //create a boolean var running to control while loop -> start with true so that the loop is on

        //displaying menu options to customer
        System.out.println("Welcome to SelfPayKiosk!\n");
        System.out.println("\nChoose an option:");
        System.out.println("1 - Scan Item");
        System.out.println("2 - Checkout");
        System.out.println("3 - Make Payment");
        System.out.println("4 - Cancel Transaction");
        System.out.println("5 - Show Current Amount Due");
        System.out.println("6 - Exit");
        System.out.println("7 - Show Summary");

        while (running) { //start the while loop -> continues until running is false and its false when the user selects exit

            System.out.print("Enter choice: "); //ask the user to enter their choice from the menu

            int choice = 0; //choice var will hold the users choice and started at 0

            if (scanner.hasNextInt()) { //if the users input is a number than the choice var will now == that number
                choice = scanner.nextInt();
            } else { //if the users input isnt a number than display to them its invalid
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); //reads wrong input then gets rid of wrong input
            }
            scanner.nextLine(); //reads the next number from the user

            if (choice == 1) { //if the user types 1 display this:
                System.out.print("Enter item price to scan: ");
                if (scanner.hasNextDouble()) { //if their price is a decimal value then the price var holds that number
                    double price = scanner.nextDouble();
                    kiosk.scanItem(price); //use scanItem method with the price as argument
                } else { //if the user doesnt input a decimal print this method
                    System.out.println("Invalid input. Please enter a valid number.");
                }
                scanner.nextLine(); //reads the next number from the user
            } else if (choice == 2) { //if customer types 2 call checkOut method
                kiosk.checkOut();
            } else if (choice == 3) { //if customer types 3 prompt them to enter their payment amount
                System.out.print("Enter payment amount: ");
                if (scanner.hasNextDouble()) { //if they enter valid amount the payment var holds it and calls
                    //makePayment method with payment var as argument
                    double payment = scanner.nextDouble();
                    kiosk.makePayment(payment);
                } else { //if they dont enter a valid amount print this message
                    System.out.println("Invalid input. Please enter a valid number for payment amount.");
                }
                scanner.nextLine(); //reads the next number from the user
            } else if (choice == 4) { //if they type 4 call the cancelTransaction method
                kiosk.cancelTransaction();
            } else if (choice == 5) {// if they type 5 print the curent amount that they owe

                System.out.println("Current amount due: " + String.format("$%.2f", kiosk.getAmountDue()));
            } else if (choice == 6) { //if they type 6 display the exit message to them then change the running var to
                //false to stop the loop
                System.out.println("Exiting SelfPayKiosk. Goodbye!");
                running = false;
            } else if (choice == 7) { //if they type 7 show them the amount of customers served and the total sales that
                //have happened
                System.out.println("Customers served: " + kiosk.getNumCustomersServed());
                System.out.println("Total sales: " + String.format("$%.2f", kiosk.getTotalSales()));
            } else { //if they type a number less than 1 or greater than 7 display this message
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}
