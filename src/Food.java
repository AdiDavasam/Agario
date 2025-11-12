import processing.core.PApplet;

import java.util.ArrayList;

public class Food {
    float x, y, radius;
    private int time;
    private float speed;
    private float acceleration;
    private float angle;
    int redFoodColor, greenFoodColor, blueFoodColor;
//    public static ArrayList<Food> allfoods = new ArrayList<>();
    public Food(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.time = 0;
        this.speed = 0;
        this.acceleration = 0;
        this.angle = 0;
        redFoodColor = (int)(Math.random()*170);
        greenFoodColor = (int)(Math.random()*170);
        blueFoodColor = (int)(Math.random()*170);
    }
    public Food(float x, float y, float speed, float acceleration, float angle) {
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.time = 0;
        this.speed = speed;
        this.acceleration = acceleration;
        this.angle = angle;
        redFoodColor = (int)(Math.random()*170);
        greenFoodColor = (int)(Math.random()*170);
        blueFoodColor = (int)(Math.random()*170);
    }
    public boolean foodHitPlayer(Player player) {
        float xDiff = Math.abs(this.x - player.getX());
        float yDiff = Math.abs(this.y - player.getY());
        float totalDistance = (float) Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        float minimumDistance = this.radius + player.getRadius();
        if (totalDistance <= minimumDistance) {
            if (player.getRadius() <= 60) {
                player.setRadius(player.getRadius()+1);
            }
            return true;
        }
        return false;
    }

    public void update() {
        if(speed>0) speed-=acceleration;
        if (speed<0) speed = 0;
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public void draw(PApplet window, float screenX, float screenY) {
        window.fill(redFoodColor,greenFoodColor,blueFoodColor);//i think this is pink, at least that is what google said
        window.ellipse(this.x-screenX,this.y-screenY,this.radius*2,this.radius*2);
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getRadius() {
        return radius;
    }

}