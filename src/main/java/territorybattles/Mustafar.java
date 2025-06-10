package territorybattles;

public class Mustafar extends Planet {

    public Mustafar() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 116406250;
        super.star2 = 69843750;
        super.star3 = 62083333;
        super.platoonValue = 60000000;
        super.preload = false;
        super.starred = false;     
        super.setZone("dark");
    }
}