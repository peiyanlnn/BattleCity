package mvc;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mvc.object.*;
import util.AudioPlay;
import mvc.object.Boom;
import util.GameConstants;
import util.ImageUtil;
/**
 * peiyan lin
 * 20201287
 */

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */
public class Viewer extends JPanel {
    private long CurrentAnimationTime = 0;

    Model gameworld = new Model();

    public Viewer(Model World) {
        this.gameworld = World;
        // TODO Auto-generated constructor stub
    }

    public void updateview() {
        this.repaint();
        // TODO Auto-generated method stub
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        CurrentAnimationTime++; // runs animation time step
        PlayerOne playerOne = gameworld.getPlayerOne();
        PlayerTwo playerTwo = gameworld.getPlayerTwo();

        if (MainWindow.state == GameConstants.GAME_START) {
            // Draw background
            drawBackground(g);
            // Draw player
            if (playerOne.isAlive()) {
                drawPlayer((int) playerOne.getCentre().getX(), (int) playerOne.getCentre().getY(), playerOne.getWidth(),
                        playerOne.getHeight(), playerOne.getTexture(), g);
            }

            if (MainWindow.gameModel == GameConstants.GAME_MODEL_DOUBLE_PLAYER && playerTwo.isAlive()) {
                drawPlayer((int) playerTwo.getCentre().getX(), (int) playerTwo.getCentre().getY(), playerTwo.getWidth(),
                        playerTwo.getHeight(), playerTwo.getTexture(), g);
            }

            // Draw Bullets
            // change back
            gameworld.getBullets().forEach((temp) -> {
                drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
                        (int) temp.getHeight(), temp.getTexture(), g);
            });

            // Draw enemy Bullets
            // change back
            gameworld.getEnemyBulletsList().forEach((temp) -> {
                drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
                        (int) temp.getHeight(), temp.getTexture(), g);
            });

            // Draw Enemies
            gameworld.getEnemies().forEach((temp) -> {
                drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
                        (int) temp.getHeight(), temp.getTexture(), g);


            });

            // Draw wall
            gameworld.getWallList().forEach((temp) -> {
                drawWall((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
                        (int) temp.getHeight(), temp.getTexture(), g);

            });

            // Draw bottles
            gameworld.getBottleList().forEach((temp) -> {
                drawBottles((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
                        (int) temp.getHeight(), temp.getTexture(), g);

            });

            //Draw boom
            CopyOnWriteArrayList<Boom> list = gameworld.getBoomList();
            for (int i = 0; i < list.size(); i++) {
                Boom boom = list.get(i);
                boom.draw(g);
                if (!boom.isCanDraw()) {
                    list.remove(boom);
                    i--;
                }
            }

            String Enemies = "current enemies：" + gameworld.getEnemies().size();
            int rest = gameworld.getMaxEnemyCount() - gameworld.getEnemyCount();
            String futureEnemies = "future enemies：" + rest;
            String maxEnemies = "total enemies：" + gameworld.getMaxEnemyCount();
            String bottles = "rest bottles：" + gameworld.getBottleList().size();

            g.setFont(new Font(Font.SERIF,Font.BOLD,20));
            g.drawString(futureEnemies, 50, 100);
            g.drawString(maxEnemies, 50, 150);
            g.drawString(Enemies, 50, 200);
            g.drawString(bottles, 50, 250);

        } else if (MainWindow.state == GameConstants.GAME_OVER) {
            drawGameState(g, "GAME OVER");
            AudioPlay.play("filed");
        } else if (MainWindow.state == GameConstants.GAME_SUCCESS) {
            drawGameState(g, "YOU SUCCESS");
            AudioPlay.play("success");
        }
//        g.drawString(String.valueOf(MainWindow.state), 100, 100);

    }


    private void drawGameState(Graphics g, String value) {
        g.setFont(new Font(Font.SERIF,Font.BOLD,50));
        g.drawString(value, 220, 200);
        g.drawString("press 0 to main menu", 220, 500);

    }

    private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
        // g.drawString(texture, x, y);

        File TextureToLoad = new File(texture); // should work okay on OSX and Linux but check if you have issues
        // depending your eclipse install or if your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            g.drawImage(myImage, x, y, 50, 50, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void drawBackground(Graphics g) {
        File TextureToLoad = new File(ImageUtil.background); // should work okay on OSX and Linux but check if you
        // have issues depending your eclipse install or if
        // your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            g.drawImage(myImage, 0, 0, MainWindow.width, MainWindow.height, 0, 0, MainWindow.width, MainWindow.height, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawBullet(int x, int y, int width, int height, String texture, Graphics g) {
        File TextureToLoad = new File(texture); // should work okay on OSX and Linux but check if you have issues
        // depending your eclipse install or if your running this without an IDE
  //      int currentPositionInAnimation = ((int) ((CurrentAnimationTime % 40) / 10)) * 32;
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            // 64 by 128
          //  g.drawImage(myImage, x, y, x + width, y + height, currentPositionInAnimation, 0, currentPositionInAnimation + 31, 32, null);
            g.drawImage(myImage, x, y, 20, 20, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void drawWall(int x, int y, int width, int height, String texture, Graphics g) {
        File TextureToLoad = new File(texture); // should work okay on OSX and Linux but check if you have issues
        // depending your eclipse install or if your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            // 64 by 128
            g.drawImage(myImage, x, y, 50, 50, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawBottles(int x, int y, int width, int height, String texture, Graphics g) {
        File TextureToLoad = new File(texture); // should work okay on OSX and Linux but check if you have issues
        // depending your eclipse install or if your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            // 64 by 128
            g.drawImage(myImage, x, y, 50, 50, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawPlayer(int x, int y, int width, int height, String texture, Graphics g) {
        File TextureToLoad = new File(texture); // should work okay on OSX and Linux but check if you have issues
        // depending your eclipse install or if your running this without an IDE
        try {
            Image myImage = ImageIO.read(TextureToLoad);
            g.drawImage(myImage, x, y, 50, 50, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

/*
 *
 *
 * VIEWER HMD into the world
 *
 * . . . .. .........~++++.. . . . . ....,++??+++?+??+++?++?7ZZ7.. . . . .
 * .+?+???++++???D7I????Z8Z8N8MD7I?=+O$.. ..
 * ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O . . ..
 * ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.
 * ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...
 * ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......
 * ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ...... ..
 * ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........
 * ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+....... .
 * 7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,...... .
 * ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....
 * .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....
 * $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:.....
 * ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
 * .......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8...
 * .. .
 * ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8.....
 * .
 * ........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8...
 * ...
 * ......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O...
 * ...
 * ....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,...
 * . .
 * ...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8.....
 * ...
 * ..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,......
 * ...
 * ..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,........
 * ..
 * ..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,.........
 * ...
 * ..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........
 * ..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,...... .
 * ..
 * ...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........ .
 * ....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..
 * .....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........
 * ......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......
 * .......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........
 * .........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............
 * .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............
 * ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................
 * .................,,,,,,,,,,,,,,,,.......................
 * .................................................
 * .................................... .................... .
 *
 *
 * GlassGiant.com
 */
