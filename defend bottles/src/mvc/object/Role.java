package mvc.object;


import util.Direction;
import util.CollideUtil;
import util.Point3f;

import java.util.List;

/**
 * peiyan lin
 * 20201287
 */
public class Role extends GameObject {


    // 移动方向
    Direction direction = Direction.RIGET;

    // 四个方向图片
    private String upImg;
    private String downImg;
    private String leftImg;
    private String rightImg;

    private boolean canFire = true;
    private long fireTime = 500;
    private long time;

    public Role(String textureLocation, int width, int height, Point3f centre, String upImg, String downImg,
                String leftImg, String rightImg) {
        super(textureLocation, width, height, centre);
        // TODO Auto-generated constructor stub

        this.upImg = upImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.downImg = downImg;
    }

    public String getUpImg() {
        return upImg;
    }

    public String getLeftImg() {
        return leftImg;
    }

    public String getRightImg() {
        return rightImg;
    }

    public String getDownImg() {
        return downImg;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


    public void fire() {
        canFire = false;
        time = System.currentTimeMillis();
    }

    public void checkCooling() {
        if (canFire) {
            return;
        }
        if (System.currentTimeMillis() - time >= fireTime) {
            canFire = true;
        }
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public long getFireTime() {
        return fireTime;
    }

    public void setFireTime(long fireTime) {
        this.fireTime = fireTime;
    }

    public boolean isCollideTile(List<Wall> tiles) {
        int radius = 25;
        for (Wall tile : tiles) {
//            左上角
            float tileX = tile.getCentre().getX();
            float tileY = tile.getCentre().getY();
            radius = tile.getWidth() / 2;


            boolean collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }
//        点-2
            tileX += radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }
//      点3
            tileX += radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }
            //      点4
            tileY += radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }
            //      点5
            tileY += radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }//      点6
            tileX -= radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }//      点7
            tileX -= radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }//      点8
            tileY -= radius;
            collide = CollideUtil.isCollide(getCentre().getX() + getWidth() / 2, getCentre().getY() + getHeight() / 2, radius, tileX, tileY);
            if (collide) {
                return true;
            }
        }

        return false;
    }

    public void back() {
        getCentre().setX(getCentre().getOldX());
        getCentre().setY(getCentre().getOldY());
    }


}
