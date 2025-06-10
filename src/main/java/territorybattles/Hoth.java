package territorybattles;

public class Hoth extends Planet {

    public Hoth() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 582632425;
        super.star2 = 476699257;
        super.star3 = 186940885;
        super.platoonValue = 518918400;
        super.preload = false;
        super.starred = false;     
        super.setZone("Mixed");
    }
}