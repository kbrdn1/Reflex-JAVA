import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Fenetre extends Canvas {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static Cursor cursor = new Cursor();

    Fenetre() throws InterruptedException {

        JFrame window = new JFrame("Reflex");

        JPanel pane = (JPanel) window.getContentPane();

        pane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBounds(0, 0, WIDTH, HEIGHT);

        pane.add(this);

        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.requestFocus();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        };


        addMouseMotionListener(mouseAdapter);

        createBufferStrategy(2);
        setIgnoreRepaint(true);
        setFocusable(false);

        start();
    }

    public void start() throws InterruptedException {
        // create Level
        Level level = new Level(1, 3, 10, 1, 30);

        ArrayList<Ball> balls = level.getBalls();

        int totalScore = 0;

        int time = 0;

        int index = 0;

        while (true) {

            Graphics2D draw = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------

            // Background
            draw.setColor(Color.black);
            draw.fillRect(0, 0, WIDTH, HEIGHT);

            //move balls by order of creation
            for (int i = 0; i < balls.size(); i++) {
                if (i <= index) {
                    balls.get(i).move();
                    balls.get(i).create(draw);
                }
            }

            //move cursor
            if (getMousePosition() != null) {
                cursor.moveCursor(getMousePosition().x);
            }
            cursor.create(draw);

            //balls Collision
            level.ballCollision();
            level.ballCollisionOnBottomWindow();

            //increment index
            if (time >= 60 * level.getSpeed()) {
                time = 0;
                index += 1;
            }

            //increment time
            time += 1;

            //Set display
            draw.setColor(Color.white);
            draw.drawString("Level: " + level.getName(), 10, 20);
            draw.drawString("Score: " + level.getScore(), 10, 40);

            //Level End
            if (!level.ballCounter()) {

                draw.setColor(Color.orange);
                draw.fillRect(0, HEIGHT / 2 - 40, WIDTH, 110);
                draw.setColor(Color.darkGray);
                draw.fillRect(0, HEIGHT / 2 - 35, WIDTH, 100);

                totalScore += level.getScore();

                draw.setColor(Color.white);
                draw.drawString("Level " + level.getName() + " completed", WIDTH / 2 - 50, HEIGHT / 2 - 10);
                draw.drawString("Score: " + level.getScore() + "/" + level.getNbBalls() * level.getScorePerBalls(), WIDTH / 2 - 30, HEIGHT / 2 + 20);
                draw.drawString("Total Score: " + totalScore, WIDTH / 2 - 40, HEIGHT / 2 + 50);


                level = new Level(level.getName() + 1, level.getSpeed() + 1, level.getNbBalls() + 3, level.getScorePerBalls() + 1, level.getBallSize() - 1);
                balls = level.getBalls();
                index = 0;
                time = 0;

                draw.dispose();
                getBufferStrategy().show();

                Thread.sleep(1000 * 3);
            }

            //-----------------------------

            draw.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Fenetre();
    }

}