import java.util.Scanner;
import java.io.Console;

// Create ATMInterface class to implement the ATM functionality
public class ATMinterface {
    // Main method starts
    public static void main(String args[]) {
        // Predefined username and password
        final String USERNAME = "user";
        final String PASSWORD = "password";

        // Declare and initialize balance, withdraw, and deposit
        int balance = 100000, withdraw, deposit;

        // Create scanner class object to get the choice of the user
        Scanner sc = new Scanner(System.in);
        Console console = System.console();

        // Prompt for username
        System.out.print("Enter username: ");
        String inputUsername = sc.nextLine();

        String inputPassword;
        if (console != null) {
            // Prompt for password using Console if available
            char[] passwordArray = console.readPassword("Enter password: ");
            inputPassword = new String(passwordArray);
        } else {
            // Fallback to using Scanner if Console is not available
            System.out.print("Enter password: ");
            inputPassword = sc.nextLine();
        }

        // Check credentials
        if (!inputUsername.equals(USERNAME) || !inputPassword.equals(PASSWORD)) {
            System.out.println("Invalid credentials. Access denied.");
            sc.close(); // Close the scanner before exiting
            return; // Exit the program if credentials are incorrect
        }

        // User authenticated
        while (true) {
            System.out.println("Automated Teller Machine");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Check Balance");
            System.out.println("Choose 4 for EXIT");
            System.out.print("Choose the operation you want to perform: ");

            // Get choice from user
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter money to be withdrawn: ");

                    // Get the withdrawal money from user
                    withdraw = sc.nextInt();

                    // Check whether the balance is greater than or equal to the withdrawal amount
                    if (balance >= withdraw) {
                        // Remove the withdrawal amount from the total balance
                        balance = balance - withdraw;
                        System.out.println("Please collect your money");
                    } else {
                        // Show custom error message
                        System.out.println("Insufficient Balance");
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.print("Enter money to be deposited: ");

                    // Get deposit amount from the user
                    deposit = sc.nextInt();

                    // Add the deposit amount to the total balance
                    balance = balance + deposit;
                    System.out.println("Your money has been successfully deposited");
                    System.out.println("");
                    break;

                case 3:
                    // Displaying the total balance of the user
                    System.out.println("Balance: " + balance);
                    System.out.println("");
                    break;

                case 4:
                    // Exit from the menu
                    System.out.println("Exiting...");
                    sc.close(); // Close the scanner before exiting
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("");
            }
        }
    }
}
