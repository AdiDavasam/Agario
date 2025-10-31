import processing.core.PApplet;

public class SpikeBall {
    private float x, y;
    private float radius;
    public SpikeBall(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 50;
    }
    public SpikeBall() {
        this.x = 400;
        this.y = 400;
        this.radius = 50;
    }
    public boolean collide(Player player) {
        double xDiff = Math.abs(this.x - player.getX());
        double yDiff = Math.abs(this.y - player.getY());
        if (xDiff <= this.radius + player.getRadius() && yDiff <= this.radius + player.getRadius()) {
            return true;
        }
        return false;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void draw(PApplet window) {
        window.fill(0,255, 0);
        window.ellipse(this.x, this.y, this.radius * 2, this.radius * 2);
    }
}
