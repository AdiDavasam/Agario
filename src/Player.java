import processing.core.PApplet;
import sun.applet.resources.MsgAppletViewer;

import java.util.ArrayList;

public class Player {
    private float x,y,speed;
    private int radius;
    Food food;

    public Player() {
        this.x = 5000;
        this.y = 5000;
        this.speed = 5;
        this.radius = 40;
    }

    public void update(PApplet window) {
//        float deltaX = window.mouseX - this.x;
//        float deltaY = window.mouseY - this.y;
        float deltaX = window.mouseX - (Main.SCREEN_WIDTH/2);
        float deltaY = window.mouseY - (Main.SCREEN_HEIGHT/2); //where it is from middle point of screen b/c player always in middle
        float angle = (float) Math.atan2(deltaY,deltaX);
        if (Main.WORLD_WIDTH - radius > x &&  radius < x) {
            x += speed * Math.cos(angle);
        } else {
            System.out.println("Ahh");
            x = Main.WORLD_WIDTH/2;
            y = Main.WORLD_HEIGHT/2;
        }

        if (Main.WORLD_HEIGHT - radius > y && radius < y) {
            y += speed * Math.sin(angle);
        } else {
            System.out.println("Ahh");
            x = Main.WORLD_WIDTH/2;
            y = Main.WORLD_HEIGHT/2;
        }

    }

    public void draw(PApplet window) {
        window.fill(0,0,255);
        window.ellipse(Main.SCREEN_WIDTH/2,Main.SCREEN_HEIGHT/2,this.radius*2,this.radius*2); //middle :)
    }

    public void shootFood(PApplet window, ArrayList<Food> allFoods) {
        if (this.radius >= 20) {
            float deltaX = window.mouseX - Main.SCREEN_WIDTH/2;
            float deltaY = window.mouseY - Main.SCREEN_HEIGHT/2;
//        float deltaX = window.mouseY - this.y;
//        float deltaY = window.mouseY - this.y;
            float angle = (float) Math.atan2(deltaY,deltaX);
            Food shotFood = new Food(this.x, this.y, 10, 0.9f,angle);
            allFoods.add(shotFood);
            radius -= 3;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
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
}
