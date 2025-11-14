import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends PApplet {
    Player player;
    ArrayList<Player> playerObjects;
    WorldObjectManager manager;

    float worldCoordinateX, worldCoordinateY, screenX, screenY;
    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 800;
    public final static int WORLD_WIDTH = 10000;
    public final static int WORLD_HEIGHT = 10000; //from top left corner
    float screenZoom, startingPlayerRadius;
    PImage img, img2, img3, img4;

    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    public void setup() {
        img = loadImage("images/spikeball.png");
        img2 = loadImage("images/sarvesh.png");
        img3 = loadImage("images/namar.png");
        img4 = loadImage("images/tejas.png");
        playerObjects = new ArrayList<>();
        player = new Player(img4);
        playerObjects.add(player);
        manager = new WorldObjectManager();
        worldCoordinateX = player.getX();
        worldCoordinateY = player.getY();
        screenX = 0;
        screenY = 0;
        manager.makeStartingFoods();
        manager.makeStartingSpikeBalls(img);
        manager.makeStartingEnemies(img2);

        screenZoom = 1;
        startingPlayerRadius = player.getRadius();
    }


    public void draw() {
        background(180);

        screenZoom = (startingPlayerRadius/player.getRadius());
        if (screenZoom < 0.1f) screenZoom = 0.1f;//max zoom out
        if (screenZoom > 1.3f) screenZoom = 1.3f; //max zoom in


        for (int i = 0; i < playerObjects.size(); i++) {
            Player currPlayerObj = playerObjects.get(i);
            currPlayerObj.update(this, screenZoom);
            currPlayerObj.draw(this, screenZoom);
        }

        setScreenCoordinates();

        manager.updateAllWorldObjects(player);
        manager.collisionDetection(player,img2);
        manager.draw(this, screenX, screenY, screenZoom, player);

        displayWorldCoordinates();
    }

    public void displayWorldCoordinates() {
        fill(0);
        text("X: "+ (int)(player.getX()) + ", Y: " + (int)(player.getY()), 20,20);
    }

    public void keyReleased() {
        if (key == 'w') {
            player.shootFood(this,manager.getAllFoods(), screenZoom, img3);
        }
    }

    public void setScreenCoordinates() {
        float viewWidth = SCREEN_WIDTH/screenZoom;
        float viewHeight = SCREEN_HEIGHT/screenZoom;

        screenX = player.getX() - viewWidth/2;
        screenY = player.getY() - viewHeight/2;

    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
