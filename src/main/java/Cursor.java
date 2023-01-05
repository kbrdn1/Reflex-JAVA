import java.awt.*;

public class Cursor extends Rectangle {
    public static final int defaultWidth = 150;
    public static final int defaultHeight = 15;
    public static final Color defaultColor = Color.blue;

    public Cursor() {
        int randomX = (int) (Math.random() * (Fenetre.WIDTH - defaultWidth));
        int randomY = (int) (Math.random() * Fenetre.HEIGHT - defaultHeight);
        width = defaultWidth;
        height = defaultHeight;
        x = Fenetre.WIDTH / 2 - width / 2;
        y = Fenetre.HEIGHT - height * 2;
        color = defaultColor;
    }

    public Cursor(int width, int height) {
        this.width = width;
        this.height = height;
        x = Fenetre.WIDTH / 2 - width / 2;
        y = 50;
        color = defaultColor;
    }

    public void moveCursor(int x) {
        int xCalc = x - width / 2;

        if (xCalc < 0) {
            xCalc = 0;
        } else if (xCalc > Fenetre.WIDTH - width) {
            xCalc = Fenetre.WIDTH - width;
        }

        this.x = xCalc;
        this.y = Fenetre.HEIGHT - height * 3;
    }
}
