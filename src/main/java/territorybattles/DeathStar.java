package territorybattles;

public class DeathStar extends Planet {

    public DeathStar() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 582632425;
        super.star2 = 476699257;
        super.star3 = 186940885;
        super.platoonValue = 518918400;
        super.preload = false;
        super.starred = false;     
        super.setZone("dark");
    }
}