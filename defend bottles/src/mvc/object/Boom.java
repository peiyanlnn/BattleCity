package mvc.object;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * peiyan lin
 * 20201287
 */
public class Boom extends GameObject {

    private static List<Image> fileList = new ArrayList<>();

    private static final long CHANGE_TIME = 10;
    private long time;

    private boolean canDraw = true;
    private int index = 0;

    public Boom() {
        super();
        // TODO Auto-generated constructor stub
        time = System.currentTimeMillis();

    }

    static {
        for (int i = 1; i <= 8; i++) {
            try {
                fileList.add(ImageIO.read(new File("res/boom/explosion" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void change() {
        if (!canDraw) {
            return;
        }
        if (System.currentTimeMillis() - time >= CHANGE_TIME) {
            index++;
            time = System.currentTimeMillis();
            if (index > fileList.size() - 1) {
                canDraw = false;
            }
        }

    }

    public void draw(Graphics g) {
        Image image = fileList.get(index);
        g.drawImage(image, (int) (
                        (getCentre().getX() - image.getWidth(null) / 2)),
                (int) (getCentre().getY() - image.getHeight(null) / 2), null);
        change();
    }

    public boolean isCanDraw() {
        return canDraw;
    }


}
