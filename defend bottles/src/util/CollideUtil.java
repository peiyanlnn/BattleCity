package util;


/**
 * peiyan lin
 * 20201287
 */

//use for tank, player and wall
public class CollideUtil {
    private CollideUtil() {
    }

    public static final boolean isCollide(float rectX, float rectY, int radius, float pointX, float pointY) {

        float disX = Math.abs(rectX - pointX);
        float disY = Math.abs(rectY - pointY);

        if (disX < radius && disY < radius) {
            return true;
        }
        return false;
    }

}
