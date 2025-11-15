import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Player {
    private float x,y,speed;
    private float radius;
    Food food;
    private PImage playerface;

    public Player(PImage playerface) {
        this.x = 5000;
        this.y = 5000;
        this.radius = 40;
        setSpeed(radius);
        this.playerface = playerface;
        this.playerface.resize(100,100);
    }

    public void update(PApplet window, float zoom) {
        setSpeed(radius);

        float deltaX = window.mouseX - (Main.SCREEN_WIDTH/2);
        float deltaY = window.mouseY - (Main.SCREEN_HEIGHT/2); //where it is from middle point of screen b/c player always in middle
        deltaX /= zoom;
        deltaY /= zoom;
        float angle = (float) Math.atan2(deltaY,deltaX);

        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        if (x < radius) x = radius + 5;//5 = margin b/c we'd get stuck in the wall
        if (x > Main.WORLD_WIDTH - radius) x = Main.WORLD_WIDTH - (radius + 5);
        if (y < radius) y = (radius+5);
        if (y > Main.WORLD_HEIGHT - radius) y = Main.WORLD_HEIGHT - (radius+5);

    }

    public void draw(PApplet window, float zoom) {
        window.fill(0,0,255);
        window.ellipse(Main.SCREEN_WIDTH/2,Main.SCREEN_HEIGHT/2,this.radius*2 * zoom,this.radius*2*zoom);//middle :)
        playerface.resize((int) (100*zoom), (int) (100*zoom));
        window.image(this.playerface, Main.SCREEN_WIDTH/2 - 50*zoom, Main.SCREEN_HEIGHT/2 - 50*zoom);
    }

    public void shootFood(PApplet window, ArrayList<Food> allFoods, float zoom, PImage face) {
//        System.out.println("Shot food");
        if (this.radius >= 20) {
            float deltaX = window.mouseX - Main.SCREEN_WIDTH/2;
            float deltaY = window.mouseY - Main.SCREEN_HEIGHT/2;
            deltaX /= zoom;
            deltaY /= zoom;
            float angle = (float) Math.atan2(deltaY,deltaX);
            float foodX = (float) (this.x + (radius/2 + 40) * (Math.cos(angle))); //20 is food size
            float foodY = (float) (this.y + (radius/2 + 40) * (Math.sin(angle))); //20 is food size

            Food shotFood = new Food(foodX, foodY, 25, 0.9f,angle, face);
            allFoods.add(shotFood);
            radius -= 3;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setSpeed(float radius) {
        speed = (27/radius) * 5;
    }
}
