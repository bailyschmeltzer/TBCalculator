package territorybattles;

public class Kashyyyk extends Planet {

    public Kashyyyk() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 190953125;
        super.star2 = 114571875;
        super.star3 = 101841667;
        super.platoonValue = 79200000;
        super.preload = false;
        super.starred = false;     
        super.setZone("Light");
    }
}