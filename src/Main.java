import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    Player player;
    SpikeBall spikeBall;
    ArrayList<Food> allFoods;
    float worldCoordinateX, worldCoordinateY, screenX, screenY;
    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 800;


    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    public void setup() {
        player = new Player();
        spikeBall = new SpikeBall();
        allFoods = new ArrayList<>();

        worldCoordinateX = player.getX();
        worldCoordinateY = player.getY();
        screenX = 0;
        screenY = 0;
    }


    public void draw() {
        background(180);

        player.update(this);
        player.draw(this);

        setScreenCoordinates();

        for (int i = 0; i < allFoods.size(); i++) {
            //allFoods.get(i).update();
            allFoods.get(i).draw(this,screenX,screenY);
            allFoods.get(i).foodHitPlayer(player);
        }

        spikeBall.draw(this, screenX,screenY);
        if(spikeBall.collide(player)) System.out.println("Hit!");

        displayWorldCoordinates();
    }

    public void displayWorldCoordinates() {
        fill(0);
        text("X: "+ (int)(screenX) + ", Y: " + (int)(screenY), 20,20);
    }

    public void keyReleased() {
        if (key == 'W') player.shootFood(this,allFoods);
    }

    public void setScreenCoordinates() {
        screenX = player.getX() - SCREEN_WIDTH/2;
        screenY = player.getY() - SCREEN_HEIGHT/2;

    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
