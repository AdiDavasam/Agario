import processing.core.PApplet;
import processing.core.PImage;

public class SpikeBall {
    private float x, y;
    private float radius;
    private PImage spikeball;
    public SpikeBall(float x, float y, PImage spikeball) {
        this.x = x;
        this.y = y;
        this.radius = 50;
        this.spikeball = spikeball;
        this.spikeball.resize(130,130);

    }
//    public SpikeBall() {
//        this.x = 400;
//        this.y = 400;
//        this.radius = 50;
//    }
    public boolean collidedWithPlayer(Player player) {
        double xDiff = Math.abs(this.x - player.getX());
        double yDiff = Math.abs(this.y - player.getY());
        double totalDistance = Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        double minimumDistance = this.radius + player.getRadius();
        if (totalDistance <= minimumDistance && player.getRadius() > this.radius + 5) return true;

        return false;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }


    public void draw(PApplet window, float screenX, float screenY, float zoom) {
        window.fill(0,255, 0);
        spikeball.resize((int) (130*zoom), (int) (130*zoom));
        window.ellipse((this.x-screenX) * zoom, (this.y-screenY) * zoom, this.radius * 2 * zoom, this.radius * 2 * zoom);
        window.image(this.spikeball, ((this.x- (screenX + 65)) * zoom), ((this.y- (screenY + 65)) * zoom));
    }
}
