package territorybattles;

public class Felucia extends Planet {

    public Felucia() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 148125000;
        super.star2 = 88875000;
        super.star3 = 79000000;
        super.platoonValue = 66000000;
        super.preload = false;
        super.starred = false;       
        super.setZone("Mixed");
    }
}