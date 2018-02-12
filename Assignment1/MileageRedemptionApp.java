/*
Matt Rycraft
Tommy Franczak
2/11/18
Assingment 1
*/

import java.io.File;
import java.util.Scanner;

public class MileageRedemptionApp {

    public static void main(String[] args){
        char choice;

        Scanner reader = new Scanner(System.in);
        System.out.println("Please input your total accumulated miles: ");
        int mileage = reader.nextInt();

        System.out.println("Please input your month of departure (1-12): ");
        int month = reader.nextInt();

        System.out.println("Do you wish to Continue(y/n): ");
        choice = reader.next().charAt(0);

        reader.close();

    }
}

