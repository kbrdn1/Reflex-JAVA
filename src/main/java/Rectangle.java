import java.awt.*;

abstract class Rectangle extends Sprite {
    protected int width;
    protected int height;

    public void create(Graphics2D draw) {
        draw.setColor(color);
        draw.fillRect(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
