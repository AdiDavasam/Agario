import processing.core.PApplet;

public class Player {
    private float x,y,speed;
    private int radius;
    public Player() {
        this.x = 400;
        this.y = 400;
        this.speed = 5;
        this.radius = 40;
    }

    public void update(PApplet window) {
        float deltaX = window.mouseX - this.x;
        float deltaY = window.mouseY - this.y;
        float angle = (float) Math.atan2(deltaY,deltaX);
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public void draw(PApplet window) {
        window.fill(0,0,255);
        window.ellipse(this.x,this.y,this.radius*2,this.radius*2);
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
