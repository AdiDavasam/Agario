import processing.core.PApplet;
import java.util.ArrayList;

public class AllFoods {
    private ArrayList<Food> allFoods;
    private float x,y,speed,acceleration;
    public AllFoods() {
        allFoods = new ArrayList<>();
    }
    public void draw(PApplet window) {
        for(int i = 0; i < allFoods.size(); i++){
            window.ellipse(allFoods.get(i).getX(),allFoods.get(i).getY(),allFoods.get(i).getRadius()*2,allFoods.get(i).getRadius()*2);
        }
    }

    public void update(float angle) {
        for(int i = 0; i < allFoods.size(); i++){
            allFoods.get(i).update(angle);
        }
    }
}
