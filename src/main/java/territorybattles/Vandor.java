package territorybattles;

public class Vandor extends Planet {

    public Vandor() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 341250768;
        super.star2 = 279205174;
        super.star3 = 109492225;
        super.platoonValue = 199584000;
        super.preload = false;
        super.starred = false;     
        super.setZone("Mixed");
    }
}