/*
Matt Rycraft
Tommy Franczak
2/11/18
Assingment 1
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MileageRedemptionApp {
    public static void main(String[] args){
        // Create Scanner to read terminal input
        Scanner reader = new Scanner(System.in);
        int miles, month;
        MilesRedeemer redeemer = new MilesRedeemer();
        boolean correct = false;
        // Verify correct terminal input
        while (!correct) {
            try {
                System.out.printf("Please input your miles: ");
                miles = reader.nextInt();
                System.out.printf("Please input the month of departure (1-12): ");
                month = reader.nextInt();
                System.out.printf("\n");

                redeemer = new MilesRedeemer(miles, month);
                correct = true;
            } catch (InputMismatchException e) {
                System.out.printf("Improper input. Would you like to try again? (Y/n): ");
                // Eatings anything lingering in sys.in
                reader.next();
                char answer = reader.next().charAt(0);
                if (answer != 'y' && answer != 'Y')
                    System.exit(2);
            }
        }


        // Construct MilesRedeemer object.
        File file;
        Scanner fileScanner;
        try { // Open file and read it
            file = new File(args[0]);
            fileScanner = new Scanner(file);
            redeemer.readDestinations(fileScanner);
        }
        catch (FileNotFoundException e) {
            System.out.printf("File not found. Program exiting...\n");
            System.exit(2);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.printf("No command lines arguments. Program exiting...\n");
            System.exit(3);
        }

        // Get City Names and print them.
        String[] names = redeemer.getCityNames();
        printDestinations(names);

        // Generate Outputs and commence point transaction
        ArrayList<String> output = redeemer.redeemMiles();

        // Print the destination strings for those to be traveled.
        System.out.printf("Your accumulated miles can be redeemed for the following tickets:\n");
        for(String s: output) {
            System.out.printf("%s\n",s);
        }
        System.out.printf("You will have %d miles left.\n\n", redeemer.getRemainingMiles());

        // Verify the purchase
        System.out.printf("Would you like to purchase these flights? (Y/n): ");
        char choice = reader.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.printf("\nYou have successfully purchased these flights.\n");
        }
        else {
            redeemer.refund();
            System.out.printf("\nYou have not purchased these flights.");
        }

        reader.close();
    }

    // printDestinations
    // Prints every destination read from file.
    private static void printDestinations(String[] names) {
        System.out.printf("---------------------------------------------\n");
        System.out.printf("List of destination cities you can travel to:\n");

        for (int i = 0; i < names.length; i++)
            System.out.printf("%s\n", names[i]);

        System.out.printf("---------------------------------------------\n\n");
    }
}

