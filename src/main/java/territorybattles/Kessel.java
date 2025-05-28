package territorybattles;

public class Kessel extends Planet {

    public Kessel() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 235143105;
        super.star2 = 165100478;
        super.star3 = 100060896;
        super.platoonValue = 110880000;
        super.preload = false;
        super.starred = false;     
        super.setZone("Mixed");
    }
}