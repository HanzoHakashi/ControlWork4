import Model.Cat;
import Util.GenerateCat;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenerateCat g = new GenerateCat();
        List<Cat> cats = g.createCat();
        g.printCats(cats);
    }
}