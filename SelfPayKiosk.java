
public class SelfPayKiosk {
    private int numCustomersServed; //var for the amount of customers served
    private double totalSales; //var for the total, amount of money
    private double amountDue; // var for the total amount the customer owes
    private boolean isCheckedOut; //var for whether the customer has checked out
    private double totalWithTax; //var for the total amount of money included with taxe
    private static final double SalesTaxRate = 0.07; //the 7% sales tax rate


    public SelfPayKiosk() {
        this.numCustomersServed = 0; //start at 0 customemrs
        this.totalSales = 0.0; //start at 0.00 dollars
        this.amountDue = 0.0; // start at 0.00 dollars
        this.isCheckedOut = false; //they havent check out yet
        this.totalWithTax = 0.0; //start at 0.00 dollars
    }


    public boolean scanItem(double price) { //method whenever a customer scans their item. Then it will add
        //the items price to their total price
        if (price < 0) { //if the price is negative display error message
            System.out.println("Error! Price is negative. Enter a positive number.");

            return false; //returns false because price was negative and didnt work
        }
        if (this.isCheckedOut == true) { //if the customer has alreadly checked out print error message
            System.out.println("Error! The item is already checked out. Cancel or make payment please.");
            return false; //return false since they checked out already
        }

        this.amountDue += price;//if the price isnt negative and the customer hasnt checked out -> add price to customers amount due
        System.out.println("Current amount due: " + String.format("$%.2f",this.amountDue)); //since amountDue is now price ->
        //display to the customer their current amount that is due
        return true; // return true because they passed both tests
    }

        //method 'checkOut' -> when customer is done scanning items and wants to check out
        //adds sales tax to amount due at the time and makes isCheckOut == true so no more items can be scaned
    public boolean checkOut() { // if amount due is less than 0 and the customer hasnt checked out -> then they cant check out
        if (this.amountDue <= 0.0 && this.isCheckedOut == false) {
            System.out.println("Error! Cant checkout. No items have been scanned yet.");//print error message
            return false; //return false because  amount due < 0 & no item has been checked out
        }
        if (this.isCheckedOut == true) { // if they already checked out then they cant try to check out another item
            System.out.println("Error! The item is already checked out. Please pay for items."); //error message prints if they try
            return false; //return flase because they didnt pass the 'if' test
        }
            //after passing if test -> checkout
        double taxAmount = this.amountDue * SalesTaxRate; //multiply curent amount due by the sales tax rate
        //store in temp tax Amount var

        this.amountDue = this.amountDue + taxAmount; //add the temp var to current total amount due

        this.totalWithTax = this.amountDue; //totalWithTax var now takes the total amount due

        this.isCheckedOut = true; //make isCheckedOut to true to continue

        System.out.println("Checkout complete. Total due with tax: " + String.format("$%.2f",this.totalWithTax));
        //print to the customer that their checkout is complete and show them their total w/ tax

        return true; //return true bc it worked

    }
    //makePayment method -> Accepting the users pay only after they check out
    public boolean makePayment(double payment) {
        if (this.isCheckedOut == false) { // If the customer tries to pay before checking out they get shown this error message
            System.out.println("Error: Please checkout first before making a payment.");
            return false; // Return false because the payment failed because they haven't checked out
        }
        if (payment < 0) { // If they try paying with negative money print this error message
            System.out.println("Error! Payment is negative. Enter a positive number.");
            return false; // Return false because it didn't work because their payment was negative
        }
        if (this.amountDue <= 0.0) { //if the amount due is $0.00 or less then payment is complete + display message
            System.out.println("Payment complete! No amount due. Thank you.");
            return true; //return true because payment worked
        }

        this.amountDue = this.amountDue - payment; // Subtract the amount paid from the amount due.

        //if their amount due is 0 or less than print them $0.00 -> paid at least in fulll
        if (this.amountDue <= 0.0) {
            System.out.println("Remaining amount due: " + String.format("$%.2f", 0.0));

            // this  will show after theyve paid in full
            System.out.println("Payment complete. Thank you!");


            this.totalSales = this.totalSales + this.totalWithTax; // Total sales gets updated and adds customer totalWithTax
            this.numCustomersServed = this.numCustomersServed + 1; // numCustomersServed gets updated + 1 at the end of the transaction

            // these variables will reset for the next transaction
            this.amountDue = 0.0;
            this.isCheckedOut = false;
            this.totalWithTax = 0.0;
        } else {
            // If they havent paid in full show them their remaining amount due
            System.out.println("Remaining amount due: " + String.format("$%.2f", this.amountDue));
        }
        return true; // return true because their payment worked
    }

    public boolean cancelTransaction() { //if they already checked out they cant cancel their payment
        if (this.isCheckedOut == true) {
            System.out.println("Error! Cant cancel payment after you have checked out. Please make payment");
            return false; //return false because it didnt work
        }

        this.amountDue = 0.0; //reset amount due

        System.out.println("Transaction cancelled. Amount due reset to $0.00"); //print to the user that thier transaction was cancelled
        return true; //return true because it worked
    }
    public double getAmountDue() { //call accessor methods
        return this.amountDue; //return amountDue var
    }

    public int getNumCustomersServed() { //call accessor methods
        return this.numCustomersServed; //return numCustomersServed var
    }

    public double getTotalSales() { //call accessor methods
        return this.totalSales; //return totalSales var
    }
}


