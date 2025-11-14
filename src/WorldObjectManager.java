import processing.core.PApplet;
import java.util.ArrayList;

public class WorldObjectManager {

    private ArrayList<Food> allFoods;
    private ArrayList<SpikeBall> allSpikeBalls;
    private ArrayList<Enemy> allEnemies;

    private int foodTimer;
    public WorldObjectManager() {
        allFoods = new ArrayList<>();
        allSpikeBalls = new ArrayList<>();
        allEnemies = new ArrayList<>();
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

    public void makeStartingEnemies() {
        for (int i = 0; i < 20; i++) {
            float x = (float) (Math.random()*Main.WORLD_WIDTH);
            float y = (float) (Math.random()*Main.WORLD_HEIGHT);
            float radius = (float) (Math.random()*60+20);
            boolean aggressiveEnemy = Math.random() > 0.5f;
            allEnemies.add(new Enemy(x,y,radius,aggressiveEnemy));
        }
    }

    public void updateAllWorldObjects(Player player) {
        //Food
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
        
        //Enemies
        for (int i = 0; i < allEnemies.size(); i++) {
            allEnemies.get(i).update(player);
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
        //player vs enemy
        for (int i = allEnemies.size()-1; i >=0; i--) {
            Enemy enemy = allEnemies.get(i);
            if (enemy.atePlayer(player)) {
                if (enemy.biggerThanPlayer(player)) {
                    System.out.println("gg you lost :/");

                    player.setX(Main.WORLD_WIDTH/2);
                    player.setX(Main.WORLD_HEIGHT/2);
                    player.setRadius(40);
                    allEnemies.clear();
                    makeStartingEnemies();
                } else {
                    System.out.println("Ate enemy :P");
                    player.setRadius(player.getRadius() + enemy.getRadius() * 1/4);
                    allEnemies.remove(i);
                }
            }
        }

        for (int i = allEnemies.size() - 1; i >= 0; i--) { //backwards b/c we don't wanna mess up order things after removing index
            for (int j = allFoods.size()-1; j >= 0 ; j--) {
                if(allFoods.get(j).foodHitEnemy(allEnemies.get(i))) allFoods.remove(j);
            }
        }
    }

    public void draw(PApplet window, float screenX, float screenY, float zoom, Player player) {
        for(int i = 0; i < allFoods.size(); i++){
            allFoods.get(i).draw(window, screenX, screenY, zoom);
        }
        for(int i = 0; i < allSpikeBalls.size(); i++){
            allSpikeBalls.get(i).draw(window, screenX, screenY, zoom);
        }

        for (int i = 0; i < allEnemies.size(); i++) {
            allEnemies.get(i).draw(window, screenX, screenY, zoom, player);
        }
    }

    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    public ArrayList<SpikeBall> getAllSpikeBalls() {
        return allSpikeBalls;
    }
}
