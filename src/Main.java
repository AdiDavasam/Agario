import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    Player player;
    SpikeBall spikeBall;


    public void settings() {
        size(800, 800);
    }


    public void setup() {
        player = new Player();
        spikeBall = new SpikeBall();
    }


    public void draw() {
        background(180);
        player.update(this);
        player.draw(this);
        spikeBall.draw(this);
        if(spikeBall.collide(player)) System.out.println("Hit!");
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
