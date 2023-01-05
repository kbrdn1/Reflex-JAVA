import java.util.ArrayList;

public class Level {
    private int name;
    private int speed;
    private int nbBalls;
    private int score;
    private int scorePerBalls;
    private int ballSize;
    private int ballCount;
    private final ArrayList<Ball> balls = new ArrayList<>();

    public Level(int name, int speed, int nbBalls, int scorePerBalls, int ballSize) {
        this.name = name;
        this.speed = speed;
        this.nbBalls = nbBalls;
        this.scorePerBalls = scorePerBalls;
        this.ballSize = ballSize;

        spawnBalls();
    }

    public void spawnBalls() {
        for (int i = 0; i < nbBalls; i++) {
            balls.add(new Ball(speed, ballSize));
        }
    }

    public void ballCollision() {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).getY() + balls.get(i).getSize() >= Fenetre.cursor.getY() && balls.get(i).getY() <= Fenetre.cursor.getY() + Fenetre.cursor.getHeight()) {
                if (balls.get(i).getX() + balls.get(i).getSize() >= Fenetre.cursor.getX() && balls.get(i).getX() <= Fenetre.cursor.getX() + Fenetre.cursor.getWidth()) {
                    balls.remove(i);
                    score += scorePerBalls;

                    ballCount += 1;
                }
            }
        }
    }

    public void ballCollisionOnBottomWindow() {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).getY() + balls.get(i).getSize() >= Fenetre.HEIGHT) {
                balls.remove(i);

                ballCount += 1;
            }
        }
    }

    public boolean ballCounter() {
        return ballCount <= nbBalls - 1;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNbBalls() {
        return nbBalls;
    }

    public void setNbBalls(int nbBalls) {
        this.nbBalls = nbBalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScorePerBalls() {
        return scorePerBalls;
    }

    public void setScorePerBalls(int scorePerBalls) {
        this.scorePerBalls = scorePerBalls;
    }

    public int getBallSize() {
        return ballSize;
    }

    public void setBallSize(int ballSize) {
        this.ballSize = ballSize;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }
}
