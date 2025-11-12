import processing.core.PApplet;

import java.util.ArrayList;

public class Player {
    private float x,y,speed;
    private int radius;
    Food food;

    public Player() {
        this.x = 5000;
        this.y = 5000;
        this.radius = 40;
        setSpeed(radius);
    }

    public void update(PApplet window) {
        setSpeed(radius);

        System.out.println(speed);
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
        System.out.println("Shot food");
        if (this.radius >= 20) {
            float deltaX = window.mouseX - Main.SCREEN_WIDTH/2;
            float deltaY = window.mouseY - Main.SCREEN_HEIGHT/2;
//        float deltaX = window.mouseY - this.y;
//        float deltaY = window.mouseY - this.y;
            float angle = (float) Math.atan2(deltaY,deltaX);
            float foodX = (float) (this.x + (radius/2 + 40) * (Math.cos(angle))); //20 is food size
            float foodY = (float) (this.y + (radius/2 + 40) * (Math.sin(angle))); //20 is food size

            Food shotFood = new Food(foodX, foodY, 25, 0.9f,angle);
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

    public void setSpeed(float radius) {
        speed = (27/radius) * 5;
    }
}
