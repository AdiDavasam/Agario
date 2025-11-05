public class Food {
    double x, y, radius;
    public Food(double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = 20;
    }
    public void foodHitPlayer(Player player) {
        double xDiff = Math.abs(this.x - player.getX());
        double yDiff = Math.abs(this.y - player.getY());
        double totalDistance = Math.sqrt((xDiff * xDiff) + (yDiff*yDiff));
        double minimumDistance = this.radius + player.getRadius();
        if (totalDistance <= minimumDistance && player.getRadius() <= 60) player.setRadius(player.getRadius()+1);
    }
}
