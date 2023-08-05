package mvc.object;

import util.Direction;
import util.Point3f;

/**
 * peiyan lin
 * 20201287
 */
public class Bullet extends GameObject {

    Direction direction;

    public Bullet() {
        super();
        // TODO Auto-generated constructor stub

    }

    public Bullet(String textureLocation, int width, int height, Point3f centre, Direction direction) {

        super(textureLocation, width, height, centre);

        // TODO Auto-generated constructor stub
        this.direction = direction;

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


}
