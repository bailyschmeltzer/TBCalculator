package territorybattles;

public class Lothal extends Planet {

    public Lothal() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 246742558;
        super.star2 = 173244775;
        super.star3 = 104996834;
        super.platoonValue = 110880000;
        super.preload = false;
        super.starred = false;     
        super.setZone("Light");
    }
}