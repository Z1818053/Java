public class Destination {

    public String city;
    public int normMile;
    public int ssMiles;
    public int upgradeMiles;
    public int startMonth;
    public int endMonth;


    public Destination(String city, int normMile, int ssMiles, int upgradeMiles, int startMonth, int endMonth) {
        this.city = city;
        this.normMile = normMile;
        this.ssMiles = ssMiles;
        this.upgradeMiles = upgradeMiles;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

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

