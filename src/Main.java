import processing.core.PApplet;

public class Main extends PApplet {
    Player player;
    WorldObjectManager manager;

    float worldCoordinateX, worldCoordinateY, screenX, screenY;
    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 800;
    public final static int WORLD_WIDTH = 10000;
    public final static int WORLD_HEIGHT = 10000; //from top left corner



    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    public void setup() {
        player = new Player();
        manager = new WorldObjectManager();

        worldCoordinateX = player.getX();
        worldCoordinateY = player.getY();
        screenX = 0;
        screenY = 0;

        manager.makeStartingFoods();
        manager.makeStartingSpikeBalls();
    }


    public void draw() {
        background(180);

        player.update(this);
        player.draw(this);

        setScreenCoordinates();

        manager.updateAllFood();
        manager.collisionDetection(player);
        manager.draw(this, screenX, screenY);

        displayWorldCoordinates();
    }

    public void displayWorldCoordinates() {
        fill(0);
        text("X: "+ (int)(player.getX()) + ", Y: " + (int)(player.getY()), 20,20);
    }

    public void keyReleased() {
        if (key == 'W') player.shootFood(this,manager.getAllFoods());
    }

    public void setScreenCoordinates() {
        screenX = player.getX() - SCREEN_WIDTH/2;
        screenY = player.getY() - SCREEN_HEIGHT/2;

    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
