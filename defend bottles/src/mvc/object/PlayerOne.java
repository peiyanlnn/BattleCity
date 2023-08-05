package mvc.object;

import util.Point3f;

/**
 * peiyan lin
 * 20201287
 */
public class PlayerOne extends Role {

    private boolean alive = false;

    public PlayerOne(String textureLocation, int width, int height, Point3f centre, String upImg, String leftImg,
                     String rightImg, String downImg) {
        super(textureLocation, width, height, centre, upImg, leftImg, rightImg, downImg);
        // TODO Auto-generated constructor stub
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


}
