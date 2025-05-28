package territorybattles;

public class Planet {
    public int platoonValue;
    public Boolean preload;
    public Boolean starred;
    public int star1;
    public int star2;
    public int star3;
    public String zone;

    public Planet() {
        this.star1 = 0;
        this.star2 = 0;
        this.star3 = 0;
        this.platoonValue = 0;
        this.preload = false;
        this.starred = false;
        this.zone = "";
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getStar1() {
        return star1;
    }

    public void setStar1(int star1) {
        this.star1 = star1;
    }

    public int getStar2() {
        return star2;
    }

    public void setStar2(int star2) {
        this.star2 = star2;
    }

    public int getStar3() {
        return star3;
    }

    public void setStar3(int star3) {
        this.star3 = star3;
    }

    public int getPlatoonValue() {
        return platoonValue;
    }

    public void setPlatoonValue(int platoonValue) {
        this.platoonValue = platoonValue;
    }

    public Boolean getPreload() {
        return preload;
    }

    public void setPreload(Boolean preload) {
        this.preload = preload;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public void toggleStarred() {
        this.starred = !this.starred;
    }

    public void togglePreload() {
        this.preload = !this.preload;
    }

}
