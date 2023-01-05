import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private int ySpeed;
    private int size;

    public Ball(int speed, int size) {
        int randomX = (int) (Math.random() * Fenetre.WIDTH - size);

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        color = new Color(r, g, b);

        this.x = randomX;
        this.y = size * 2;
        setySpeed(speed);
        this.size = size;
        this.color = color;
    }

    public void move() {
        y += ySpeed;
    }

    public void create(Graphics2D draw) {
        draw.setColor(color);
        draw.fillOval(x, y, size, size);
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        if (ySpeed == 0) {
            ySpeed = -1;
        }
        this.ySpeed = ySpeed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
