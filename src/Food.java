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
            if (player.getRadius() <= 100) {
                player.setRadius(player.getRadius()+1);
            }
            return true;
        }
        return false;
    }

    public boolean foodHitEnemy(Enemy enemy) {
        float xDiff = Math.abs(this.x - enemy.getX());
        float yDiff = Math.abs(this.y - enemy.getY());
        float totalDistance = (float) Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        float minimumDistance = this.radius + enemy.getRadius();
        if (totalDistance <= minimumDistance) {
            if (enemy.getRadius() <= 100) {
                enemy.growEnemy(enemy.getRadius()+1);
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

        if (x < radius) {
            x = radius;
            speed = 0;
        }
        if (x > Main.WORLD_WIDTH - radius) {
            x = Main.WORLD_WIDTH - radius;
            speed = 0;
        }
        if (y < radius) {
            y = radius;
            speed = 0;
        }
        if (y > Main.WORLD_HEIGHT - radius) {
            y = Main.WORLD_HEIGHT - radius;
            speed = 0;
        }
    }

    public void draw(PApplet window, float screenX, float screenY, float zoom) {
        window.fill(redFoodColor,greenFoodColor,blueFoodColor);
        window.ellipse((this.x-screenX) * zoom,(this.y-screenY) * zoom,this.radius*2 * zoom,this.radius*2 * zoom);
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