/*
Matt Rycraft
Tommy Franczak
2/11/18
Assingment 1
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MileageRedemptionApp {

    public static void main(String[] args) {
        MilesRedeemer m = new MilesRedeemer();
        Scanner reader = new Scanner(System.in);
        System.out.println("Please input your total accumulated miles: ");
        int mileage = reader.nextInt();

        System.out.println("Please input your month of departure (1-12): ");
        int month = reader.nextInt();

        System.out.println("Do you wish to Continue(y/n): ");
        char choice = reader.next().charAt(0);

        reader.close();


        while (choice == 'y')
        {

            File file = new File(args[0]);

            Scanner scan = new Scanner(System.in);


            try {
                Scanner input = new Scanner(file);
                m.readDestinations(input);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }
    }


}


