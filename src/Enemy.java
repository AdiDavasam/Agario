public class Enemy {
    private float x,y,radius,speed;
    public Enemy(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speed = (radius/ (radius*1.44f)) * 10;
    }
    public boolean biggerThanPlayer(Player player) {
        if (this.radius >= player.getRadius()) return true;
        return false;
    }
    public void update(Player player) {
        float deltaX = player.getX() - this.x;
        float deltaY = player.getY() - this.y;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        float angle = (float) Math.atan2(deltaY,deltaX);
        if (distance < 6700) {
            if (this.biggerThanPlayer(player)) {
                x += speed * Math.cos(angle);
                y += speed * Math.sin(angle);
            } else {
                x -= speed * Math.cos(angle);
                y -= speed * Math.sin(angle);
            }
        } else {
            //random speed
        }
    }
}
