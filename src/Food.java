import processing.core.PApplet;

import java.util.ArrayList;

public class Food {
    float x, y, radius;
    private int time;
    private float speed;
    private float acceleration;
//    public static ArrayList<Food> allfoods = new ArrayList<>();
    public Food(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.time = 0;
        this.speed = 0;
        this.acceleration = 0;
    }
    public Food(float x, float y, float speed, float acceleration) {
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.time = 0;
        this.speed = speed;
        this.acceleration = acceleration;
    }
    public void foodHitPlayer(Player player) {
        float xDiff = Math.abs(this.x - player.getX());
        float yDiff = Math.abs(this.y - player.getY());
        float totalDistance = (float) Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        float minimumDistance = this.radius + player.getRadius();
        if (totalDistance <= minimumDistance && player.getRadius() <= 60) player.setRadius(player.getRadius()+1);
    }
    public void draw(PApplet window) {
        time++;
        if(time>=60){
            time=0;
//            allfoods.add(new Food((float) (Math.random()*10000),(float) (Math.random()*10000)));
        }
        window.fill(255,0,0);
//        for (int i = 0; i < allfoods.size(); i++) {
//            window.ellipse(this.x, this.y, this.radius*2, this.radius*2);
//        }

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
    public void update(float angle) {
        if(speed>0) speed-=acceleration;
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }
}