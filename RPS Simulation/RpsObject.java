import java.awt.Rectangle;

public class RpsObject {
    protected int x, y, size, speed;
    protected RpsScreen screen;

    public RpsObject(RpsScreen s, int x, int y, int size, int speed) {
        screen = s;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }
}
