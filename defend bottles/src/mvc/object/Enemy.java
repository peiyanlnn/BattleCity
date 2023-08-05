package mvc.object;

import java.util.Random;

import util.Direction;
import util.Point3f;
import util.Vector3f;

/**
 * peiyan lin
 * 20201287
 */
public class Enemy extends Role {

    int moveCount = 0;

    public Enemy(String textureLocation, int width, int height, Point3f centre, String upImg, String downImg,
                 String leftImg, String rightImg) {
        super(textureLocation, width, height, centre, upImg, downImg, leftImg, rightImg);
        setFireTime(3000);
        // TODO Auto-generated constructor stub
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];

    }

    public void autoMove() {
        if (moveCount >= 200) {
            direction = getRandomDirection();
            moveCount = 0;
        } else {
            moveCount++;
        }
        switch (direction) {
            case UP:
                getCentre().ApplyVector(new Vector3f(0, -1, 0));
                setTexture(getUpImg());
                break;
            case DOWN:
                getCentre().ApplyVector(new Vector3f(0, 1, 0));
                setTexture(getDownImg());
                break;
            case LEFT:
                getCentre().ApplyVector(new Vector3f(-1, 0, 0));
                setTexture(getLeftImg());
                break;
            case RIGET:
                getCentre().ApplyVector(new Vector3f(1, 0, 0));
                setTexture(getRightImg());
                break;
            default:
                break;
        }

    }


}
