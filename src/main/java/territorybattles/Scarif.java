package territorybattles;

public class Scarif extends Planet {

    public Scarif() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 555710999;
        super.star2 = 454672636;
        super.star3 = 178302994;
        super.platoonValue = 518918400;
        super.preload = false;
        super.starred = false;     
        super.setZone("Light");
    }
}