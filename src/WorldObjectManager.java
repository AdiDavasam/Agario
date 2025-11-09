import processing.core.PApplet;
import java.util.ArrayList;

public class WorldObjectManager {

    private ArrayList<Food> allFoods;
    private ArrayList<SpikeBall> allSpikeBalls;

    private int foodTimer;
    public WorldObjectManager() {
        allFoods = new ArrayList<>();
        allSpikeBalls = new ArrayList<>();
        foodTimer = 0;
    }

    public void makeStartingFoods() {
        for (int i = 0; i < 150; i++) {
            float x = (float) (Math.random()*Main.WORLD_WIDTH);
            float y = (float) (Math.random()*Main.WORLD_HEIGHT);
            allFoods.add(new Food(x,y));
        }
    }

    public void makeStartingSpikeBalls() {
        for (int i = 0; i < 20; i++) {
            float x = (float) (Math.random()*Main.WORLD_WIDTH);
            float y = (float) (Math.random()*Main.WORLD_HEIGHT);
            allSpikeBalls.add(new SpikeBall(x,y));
        }
    }

    public void updateAllFood() {
        for(int i = 0; i < allFoods.size(); i++){
            allFoods.get(i).update();
        }
        foodTimer++;
        if (foodTimer >= 60) {
            if (allFoods.size() <= 500) {
                float x = (float) (Math.random() * Main.WORLD_WIDTH);
                float y = (float) (Math.random() * Main.WORLD_HEIGHT);
                allFoods.add(new Food(x, y));
            }
        }
    }

    public void collisionDetection(Player player) {
        for (int i = allFoods.size() - 1; i >= 0; i--) { //backwards b/c we don't wanna mess up order things after removing index
            if (allFoods.get(i).foodHitPlayer(player)) {
                allFoods.remove(i);
                System.out.println("Ate :D");
            }
        }

        for (int i = allSpikeBalls.size() - 1; i >= 0; i--) { //backwards b/c we don't wanna mess up order things after removing index
            if (allSpikeBalls.get(i).collidedWithPlayer(player)) {
                allSpikeBalls.remove(i);
                System.out.println("Hit ;)");
            }
        }
    }

    public void draw(PApplet window, float screenX, float screenY) {
        for(int i = 0; i < allFoods.size(); i++){
            allFoods.get(i).draw(window, screenX, screenY);
        }
        for(int i = 0; i < allSpikeBalls.size(); i++){
            allSpikeBalls.get(i).draw(window, screenX, screenY);
        }
    }

    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    public ArrayList<SpikeBall> getAllSpikeBalls() {
        return allSpikeBalls;
    }
}
