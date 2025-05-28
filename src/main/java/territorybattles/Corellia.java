package territorybattles;

public class Corellia extends Planet {

    public Corellia() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 111718750;
        super.star2 = 67031250;
        super.star3 = 59583333;
        super.platoonValue = 60000000;
        super.preload = false;
        super.starred = false; 
        super.setZone("Mixed");     
    }
}