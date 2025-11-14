import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {
    private float x,y,radius,speed;
    private int movementTimer;
    private float angle;
    private boolean aggressive;
    private PImage enemyface;

    public Enemy(float x, float y, float radius, boolean aggressive, PImage enemyface) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        setSpeed(radius);
        this.movementTimer = 0;
        this.angle = 0;
        this.aggressive = aggressive;
        this.enemyface = enemyface;
        this.enemyface.resize(100,100);
    }
    public void setSpeed(float radius) {
        this.speed = (27/radius) * 4.5f;
    }
    public boolean biggerThanPlayer(Player player) {
        if (this.radius >= player.getRadius()) return true;
        return false;
    }
    public void update(Player player) {
        setSpeed(radius);

        float deltaX = player.getX() - this.x;
        float deltaY = player.getY() - this.y;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        float angle = (float) Math.atan2(deltaY,deltaX);
        if (distance < 1000 && aggressive) {
            if (this.biggerThanPlayer(player)) {
                x += speed/2 * Math.cos(angle);
                y += speed/2 * Math.sin(angle);
            } else {
                x -= speed/3 * Math.cos(angle);
                y -= speed/3 * Math.sin(angle);
            }
        } else {
            movementTimer--;
            if (movementTimer <= 0) {
                movementTimer = (int) (Math.random() * 180 + 180); //3-6 secs
                angle = (float) (Math.random() * 360);
            }
            x+= speed * Math.cos(angle);
            y+= speed * Math.sin(angle);
        }
        if (x < radius) x = radius;
        if (x > Main.WORLD_WIDTH - radius) x = Main.WORLD_WIDTH - radius;
        if (y < radius) y = radius;
        if (y > Main.WORLD_HEIGHT - radius) y = Main.WORLD_HEIGHT - radius;
    }
    public void draw(PApplet window, float screenX, float screenY, float zoom, Player player) {
        if (biggerThanPlayer(player)) window.fill(255,100,20); //orange :D
        else window.fill(255,255,0);

        window.ellipse((this.x-screenX)*zoom, (this.y - screenY) * zoom, this.radius*2 * zoom,this.radius*2 * zoom);
        window.image(this.enemyface, (this.x-screenX)*zoom - 50, (this.y - screenY) * zoom - 50);
    }

    public boolean atePlayer(Player player) {
        float xDiff = Math.abs(this.x - player.getX());
        float yDiff = Math.abs(this.y - player.getY());
        float totalDistance = (float) Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        float minimumDistance = this.radius + player.getRadius();
        if (totalDistance <= minimumDistance) {
            return true;
        }
        return false;
    }

    public float getRadius() {
        return radius;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void growEnemy(float amt) {
        this.radius += amt;
    }
}
