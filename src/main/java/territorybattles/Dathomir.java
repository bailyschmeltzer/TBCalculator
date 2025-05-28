package territorybattles;

public class Dathomir extends Planet {

    public Dathomir() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 158960938;
        super.star2 = 95376562;
        super.star3 = 84779167;
        super.platoonValue = 79200000;
        super.preload = false;
        super.starred = false;     
        super.setZone("dark");
    }
}