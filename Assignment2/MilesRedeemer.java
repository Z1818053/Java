/**********************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 * 3/19/18
 *  Assingment 2
 *  (Also used in Assignment 1)
 *
 *  MilesRedeemer class allows us to handle transactions
 *  with miles along with getting the data and using it
*************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// MilesRedeemer Class
// Handles file reading and miles transactions.
public class MilesRedeemer {
    private static int miles;
    private static int mtemp;
    private static int month;
    public static int size;
    public static ArrayList<Destination> dlist;

    /* readDestinations
       Takes Scanner object with open file.
       Populates ArrayList<Destination> dlist
       Reads the file in arg[0]
    */
    public static void readDestinations(Scanner fileScanner) {
        String  string;
        String[] parts;
        int lines = 0;

        // Count lines in file
        while(fileScanner.hasNextLine()) {
            lines++;
            fileScanner.nextLine();
        }
        dlist = new ArrayList<Destination>(lines);

        // Reopen file and scanner.
        File file = new File("miles.txt");
        try {
            fileScanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Loops through file and creates Destination object in dlist
        for (int i = 0; fileScanner.hasNextLine(); i++) {
            dlist.add(new Destination());

            string = new String(fileScanner.nextLine());
            // Split string using - and ; for delimiters
            parts  = string.split("-|;");

            // Set values for each variable in Destination object.
            dlist.get(i).setCity(parts[0]);
            dlist.get(i).setNormMile(Integer.valueOf(parts[1]));
            dlist.get(i).setSsMiles(Integer.valueOf(parts[2]));
            dlist.get(i).setUpgradeMiles(Integer.valueOf(parts[3]));
            dlist.get(i).setStartMonth(Integer.valueOf(parts[4]));
            dlist.get(i).setEndMonth(Integer.valueOf(parts[5]));
        }

        size = dlist.size();
    }

    /* readDestinations
       Returns String[] with organized destination names
    */
    public static String[] getCityNames() {
        // Loops through all destinations in dlist
        String[] cities = new String[dlist.size()];
        for (int i = 0; i < dlist.size(); i++) {
            // Assigns cities[i] to city name
            cities[i] = dlist.get(i).getCity();
        }
        // Sorts cities
        Arrays.sort(cities);

        return cities;
    }

    /* getRemainingMiles
       Returns amount of miles left
    */
    public static int getRemainingMiles(){ return miles; }

    /* redeemMiles
       Main drive of mile redemption.
       Calls multiple subfunctions to create arrays and then print them.
    */
    public ArrayList<String> redeemMiles() {
        // Backup original values
        mtemp = miles;

        // ArrayLists to hold dlist indexes.
        ArrayList<Integer> candidates = new ArrayList<Integer>(dlist.size());
        ArrayList<Integer> selected   = new ArrayList<Integer>(dlist.size());
        ArrayList<Integer> upgraded   = new ArrayList<Integer>(dlist.size());


        // Create list of destination indexes.
        for (Destination d: dlist) {
            candidates.add(dlist.indexOf(d));
        }

        findDestinations(candidates, selected);
        findUpgrades(selected, upgraded);
        return prepareString(selected, upgraded);
    }

    /* findDestinations
       Takes 2 ArrayLists:
           candidates: possible distances to look at
           selected: fills with destinations chosen to be bought with normal miles
       This algorithm will buy destinations by the farthest distances
       It will prioritize purchases with supersaver miles.
    */
    private static void findDestinations(ArrayList<Integer> candidates,
                                         ArrayList<Integer> selected)
    {
        int size = candidates.size();
        // Loops through all candidates to order flights.
        for (int k = 0; k < size; k++) {
            int distance = 0;
            int high = 0;
            int indx = 0;

            // Find farthest candidate
            int j = 0;
            for (int i : candidates) {
                if (dlist.get(i).getNormMile() > distance) {
                    distance = dlist.get(i).getNormMile();
                    high = i;
                    indx = j;
                }
                j++;
            }

            // Check if ssmiles work
            if (dlist.get(high).getEndMonth() >= month && dlist.get(high).getStartMonth() <= month && dlist.get(high).getSsMiles() <= miles) {
                selected.add(high);
                miles -= dlist.get(high).getSsMiles();
            }
            // Buy with normal miles
            else if (dlist.get(high).getNormMile() <= miles) {
                selected.add(high);
                miles -= distance;
            }

            // Remove highest from candidates
            candidates.remove(indx);
        }
    }

    /* findUpgrades
       Takes 3 ArrayLists:
           selected: destinations to check if upgradeable
           premium: premium destinations to check if upgradeable
           upgraded: Array to be populated with upgradeable
       This function will only purchase upgrades with miles and not ssmiles
    */
    private static void findUpgrades(ArrayList<Integer> selected,
                                     ArrayList<Integer> upgraded)
    {
        // Creates copy
        ArrayList<Integer> stemp = new ArrayList<Integer>(selected);

        int size = stemp.size();
        // Loops through all selected and premium to find largest distance.
        for (int k = 0; k < size; k++) {
            int distance = 0;
            int high = 0;
            int indx = 0;

            // Find farthest in stemp
            int j = 0;
            for (int i : stemp) {
                if (dlist.get(i).getNormMile() > distance) {
                    distance = dlist.get(i).getNormMile();
                    high = i;
                    indx = j;
                }
                j++;
            }

            // Buy upgrade with normal miles
            if (dlist.get(high).getUpgradeMiles() <= miles) {
                upgraded.add(high);
                miles -= dlist.get(high).getUpgradeMiles();
            }

            // Remove highest stemp
            stemp.remove(indx);

        }
    }

    /* prepareString
       Takes 2 ArrayLists to be printed.
       Returns formatted ArrayList<String> ready for printing
    */
    private static ArrayList<String> prepareString(ArrayList<Integer> selected,
                                                   ArrayList<Integer> upgraded)
    {
        ArrayList<String> stringOut = new ArrayList<String>(selected.size());

        // Prepare string for all selected
        boolean upgrade = false;
        for (int i: selected) {
            for (int j: upgraded) {
                // Check if upgraded
                if (j == i) {
                    upgrade = true;
                    break;
                }
            }

            if (upgrade) {
                stringOut.add("A trip to " + dlist.get(i).getCity() + ", first class.");
            }
            else {
                stringOut.add("A trip to " + dlist.get(i).getCity() + ", business class.");
            }
        }
        return stringOut;
    }

    /* refund
       Simply resets miles and ssmiles to prepurchase values
    */
    public static void refund() {
        miles = mtemp;
    }

    /* setMiles
       Simply sets the miles to the given value
    */
    public static void setMiles( int imiles ) {
        miles = imiles;
        mtemp = imiles;
    }

    /* setMiles
    Simply sets the month to the given value
    */
    public static void setMonth( int Month ) {
        month = Month;
    }

    // Constructor
    // Assigns local variables to those given.
    public MilesRedeemer(int miles, int month) {
        this.miles = miles;
        this.month = month;
    }

    // Default Constructor
    public MilesRedeemer() {
        this.miles = 0;
        this.month = 0;
    }


}
