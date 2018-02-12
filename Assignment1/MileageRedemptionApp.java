/*
Matt Rycraft
Tommy Franczak
2/11/18
Assingment 1
*/

import java.io.File;
import java.util.Scanner;

class MileageRedemptionApp{

	public class Destination{
		public string city;
		public int normMile;
		public int ssMiles;
		public int upgradeMiles;
		public int startMonth;
		public int endMonth;

		//contructors
		public Destination(string City, int normMile, int ssMiles, 
			int upgradeMiles, int startMonth, int endMonth){
			city = startCity;
			normMile = startNormMile;
			ssMiles = startSupMiles;
			upgradeMiles = startUpgradMiles;
			startMonth = startingMonth;
			endMonth = endingMonth;
		}

		public void set city(string newValue){
			city = newValue;
		}
	
		public void set normMile(int newValue){
			normMile = newValue;
		}

		public void set ssMile(int newValue){
			ssMile = newValue;
		}

		public void set upgradeMile(int newValue){
			upgradeMile = newValue;
		}

		public void set startMonth(int newValue){
			startMonth = newValue;
		}

		public void set endMonth(int newValue){
			endMonth = newValue;
		}

	}

    public static void main(String[] args){

    	char choice;
            Scanner reader = new Scanner(System.in);
            System.out.println("Please input your total accumulated miles: ");
            int mileage = reader.nextInt();

            System.out.println("Please input your month of depature (1-12): ");
            int month = reader.nextInt();

            System.out.println("Do you wish to Continue(y/n): ");
            choice = reader.next().charAt(0);

         reader.close();

        }
}

