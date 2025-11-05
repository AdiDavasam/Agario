import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    Player player;
    SpikeBall spikeBall;
    ArrayList<Food> allFoods;
    double worldCoordinateX, worldCoordinateY, screenX, screenY;
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

        player.update(this, this);
        //player.draw(this, player.getX() - SCREEN_WIDTH /2, player.getY() - SCREEN_HEIGHT /2);
        player.draw(this);
        spikeBall.draw(this);
        if(spikeBall.collide(player)) System.out.println("Hit!");

        screenX = player.getX() - SCREEN_WIDTH/2;
        screenY = player.getY() - SCREEN_HEIGHT/2;
        displayWorldCoordinates();
    }

    public void displayWorldCoordinates() {
        fill(0);
        text("X: "+ (int)(screenX) + ", Y: " + (int)(screenY), 20,20);
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
