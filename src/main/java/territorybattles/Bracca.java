package territorybattles;

public class Bracca extends Planet {

    public Bracca() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 142265625;
        super.star2 = 85359375;
        super.star3 = 75875000;
        super.platoonValue = 66000000;
        super.preload = false;
        super.starred = false;       
        super.setZone("Light");
    }
}