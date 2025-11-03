import processing.core.PApplet;

public class Main extends PApplet {
    Player player;
    SpikeBall spikeBall;
    double worldCoordinateX, worldCoordinateY;
    final static int WORLD_WIDTH = 800;
    final static int WORLD_HEIGHT = 800;


    public void settings() {
        size(WORLD_WIDTH, WORLD_HEIGHT);
    }


    public void setup() {
        player = new Player();
        spikeBall = new SpikeBall();
        worldCoordinateX = player.getX();
        worldCoordinateY = player.getY();
    }


    public void draw() {
        background(180);
        player.update(this);
        player.draw(this, player.getX() - WORLD_WIDTH/2, player.getY() - WORLD_HEIGHT/2);
        spikeBall.draw(this);
        if(spikeBall.collide(player)) System.out.println("Hit!");

        worldCoordinateX = player.getX();
        worldCoordinateY = player.getY();
        displayWorldCoordinates();
    }

    public void displayWorldCoordinates() {
        fill(0);
        text("X: "+ (int)(worldCoordinateX) + ", Y: " + (int)(worldCoordinateY), 20,20);
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
