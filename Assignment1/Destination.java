/*
Matt Rycraft
Tommy Franczak
2/11/18
Assingment 1
*/

// Destination Class
// Handles information about a destination and its miles.
public class Destination {

    private String city;
    private int normMile;
    private int ssMiles;
    private int upgradeMiles;
    private int startMonth;
    private int endMonth;


    public Destination(String city, int normMile, int ssMiles, int upgradeMiles, int startMonth, int endMonth) {
        this.city = city;
        this.normMile = normMile;
        this.ssMiles = ssMiles;
        this.upgradeMiles = upgradeMiles;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public Destination(){}

    public String getCity() { return city; }

    public void setCity(String newCity) {
        city = newCity;
    }

    public int getNormMile() {
        return normMile;
    }

    public void setNormMile(int newNormMile) {
        normMile = newNormMile;
    }

    public int getSsMiles() {
        return ssMiles;
    }

    public void setSsMiles(int newSsMiles) {
        ssMiles = newSsMiles;
    }

    public int getUpgradeMiles() {
        return upgradeMiles;
    }

    public void setUpgradeMiles(int newUpgradeMiles) {
        upgradeMiles = newUpgradeMiles;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int newStartMonth) {
        startMonth = newStartMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int newEndMonth) {
        endMonth = newEndMonth;
    }
}
