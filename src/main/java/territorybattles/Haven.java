package territorybattles;

public class Haven extends Planet {

    public Haven() {
        // Directly set the value instead of calling an overridable method
        super.star1 = 235143105;
        super.star2 = 165243583;
        super.star3 = 100060896;
        super.platoonValue = 110880000;
        super.preload = false;
        super.starred = false;     
        super.setZone("dark");
    }
}