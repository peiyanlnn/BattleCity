package mvc.object;

import util.Point3f;

/**
 * peiyan lin
 * 20201287
 */
public class PlayerTwo extends Role {
    private boolean alive = false;

    public PlayerTwo(String textureLocation, int width, int height, Point3f centre, String upImg, String downImg, String leftImg, String rightImg) {
        super(textureLocation, width, height, centre, upImg, downImg, leftImg, rightImg);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
