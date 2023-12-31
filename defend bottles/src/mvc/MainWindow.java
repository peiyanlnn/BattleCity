package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import util.GameConstants;
import util.ImageUtil;
import util.UnitTests;

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
 */

public class MainWindow {
    private static JFrame frame = new JFrame("Defend Bottles"); // Change to the name of your game
    private static Model gameworld = new Model();
    private static Viewer canvas = new Viewer(gameworld);
    private Controller controller = new Controller();

    private static int TargetFPS = 100;


    //游戏状态：查看相关常量
    public static int state = 0;
    public static int gameModel = GameConstants.GAME_MODEL_NONE;

    static int width = 1200;
    static int height = 850;


    private void startGame(int model) {
        this.state = GameConstants.GAME_START;
        gameModel = model;

        canvas.setVisible(true);

        canvas.removeKeyListener(controller);
        canvas.addKeyListener(controller); // adding the controller to the Canvas

        canvas.requestFocusInWindow(); // making sure that the Canvas is in focus so keyboard input will be
        // taking in .

    }

    public static void resetGame() {

        state = GameConstants.GAME_INITIALIZATION;
        gameModel = GameConstants.GAME_MODEL_NONE;

        canvas.setVisible(false);

        gameworld.resetWord();

        Controller.getInstance().resetContrller();
    }

    /**
     * 播放背景音乐
     */


    public MainWindow() {
        frame.setSize(width, height); // you can customise this later and adapt it to change on size.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If exit // you can modify with your way of quitting ,
        // just is a template.
        frame.setLayout(null);
        frame.add(canvas);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        canvas.setBounds(0, 0, width, height);
        canvas.setBackground(new Color(255, 255, 255)); // white background replaced by Space background but if you
        // remove the background method this will draw a white screen
        canvas.setVisible(false); // this will become visible after you press the key.


        //title
        JLabel title = new JLabel("Defend Bottles");
        title.setBounds(450, 0, 500, 200);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 50));

        JButton single = new JButton("single player"); // start button
        JButton multi = new JButton("double player");

        //button
        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(GameConstants.GAME_MODEL_SIGN_PLAYER);
            }
        });
        single.setBounds(500, 400, 200, 50);

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(GameConstants.GAME_MODEL_DOUBLE_PLAYER);
            }
        });
        multi.setBounds(500, 500, 200, 50);

        //image
        JLabel image=new JLabel();
        image.setIcon(new ImageIcon(ImageUtil.keyboard));
        image.setBounds(100,100,400,300);

        JLabel rule1=new JLabel();
        rule1.setIcon(new ImageIcon(ImageUtil.rule1));
        rule1.setBounds(100,500,500,300);

        JLabel rule2=new JLabel();
        rule2.setIcon(new ImageIcon(ImageUtil.rule2));
        rule2.setBounds(800,300,500,300);

        frame.add(image);
        frame.add(rule1);
        frame.add(rule2);
        frame.add(title);
        frame.add(single);
        frame.add(multi);
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        MainWindow hello = new MainWindow(); // sets up environment

        while (true) // not nice but remember we do just want to keep looping till the end. // this
        // could be replaced by a thread but again we want to keep things simple
        {

            // swing has timer class to help us time this but I'm writing my own, you can of
            // course use the timer, but I want to set FPS and display it

            int TimeBetweenFrames = 1000 / TargetFPS;
            long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;

            // wait till next time step
            while (FrameCheck > System.currentTimeMillis()) {
            }

            if (state == GameConstants.GAME_START
                    || state == GameConstants.GAME_SUCCESS
                    || state == GameConstants.GAME_OVER) {
                gameloop();
            }

            // UNIT test to see if framerate matches
            UnitTests.CheckFrameRate(System.currentTimeMillis(), FrameCheck, TargetFPS);

        }

    }

    // Basic Model-View-controller pattern
    private static void gameloop() {
        // GAMELOOP

        // controller input will happen on its own thread
        // So no need to call it explicitly

        // model update
        gameworld.gamelogic();
        // view update

        canvas.updateview();



    }




}

/*
 *
 *
 *
 * Hand shake agreement
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,=+++
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::,,,,,,:::::,=+++????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,:++++????+??
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::,:,:,,:,:,,,,,,,,,,,,,,,,,,,,++++++?+++++????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=++?+++++++++++??????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++?+++?++?++++++++++?????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:
 * ::,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++++++++++++++????+++++++???????
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,
 * ,,,,,,,,,,,,,,,,,:===+=++++++++++++++++++++?+++????????????????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,
 * ,,,,,,,,,,~=~~~======++++++++++++++++++++++++++????????????????
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,::::,,,,,,=~.,
 * ,,,,,,+===~~~~~~====++++++++++++++++++++++++++++???????????????
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,~~.~??++~.,
 * ~~~~~======~=======++++++++++++++++++++++++++????????????????II
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,:=+++??=====~~~~
 * ~~====================+++++++++++++++++++++?????????????????III
 * :::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,++~~~=+=~~~~~~==~~~::
 * ::~~==+++++++==++++++++++++++++++++++++++?????????????????IIIII
 * ::::::::::::::::::::::::::::::::::::::::::::::::,:,,,:++++==+??+=======~~~~=~
 * ::~~===++=+??++++++++++++++++++++++++?????????????????I?IIIIIII
 * ::::::::::::::::::::::::::::::::::::::::::::::::,,:+????+==??+++++?++====~~~~
 * ~:~~~++??+=+++++++++?++++++++++??+???????????????I?IIIIIIII7I77
 * ::::::::::::::::::::::::::::::::::::::::::::,,,,+???????++?+?+++???7?++======
 * ~~+=====??+???++++++??+?+++???????????????????IIIIIIIIIIIIIII77
 * :::::::::::::::::::::::::::::::::::::::,,,,,,=??????IIII7???+?+II$Z77??+++?+=
 * +++++=~==?++?+?++?????????????III?II?IIIIIIIIIIIIIIIIIIIIIIIIII
 * ::::::::::::::::::::::::::::::,,,,,,~=======++++???III7$???+++++Z77ZDZI?????I
 * ?777I+~~+=7+?II??????????????IIIIIIIIIIIIIIIIIIIIII??=:,,,,,,,,
 * ::::::::,:,:,,,,,,,:::~==+=++++++++++++=+=+++++++???I7$7I?+~~~I$I??++??
 * I78DDDO$7?++==~I+7I7IIIIIIIIIIIIIIIIII777I?=:,,,,,,,,,,,,,,,,,,,,,,,,
 * ++=++=++++++++++++++?+????+??????????+===+++++????I7$$ZZ$I+=~$7I???++++++===~
 * ~==7??++==7II?~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * +++++++++++++?+++?++????????????IIIII?I+??I???????I7$ZOOZ7+=~7II?+++?II?I?+++
 * =+=~~~7?++:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * +?+++++????????????????I?I??I??IIIIIIII???II7II??I77$ZO8ZZ?~~7I?+==++?O7II??+
 * ??+=====.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * ?????????????III?II?????I?????IIIII???????II777IIII7$ZOO7?+~+7I?+=~~+???
 * 7NNN7II?+=+=++,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * ????????????IIIIIIIIII?IIIIIIIIIIII????II?III7I7777$ZZOO7++=$77I???==+++????
 * 7ZDN87I??=~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * IIII?II??IIIIIIIIIIIIIIIIIIIIIIIIIII???+??II7777II7$$OZZI?+$$$$77IIII????????
 * ?++=+.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?+++?IIIII7777$$$$$$7$$$$7IIII7I$IIIIII?
 * ??I+=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII???????IIIIII77I7777$7$$$II????I??
 * I7Z87IIII?=,,,,,,,,,,,:,,::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * 777777777777777777777I7I777777777~,,,,,,,+77IIIIIIIIIII7II7$$$Z$?I????III???
 * II?,,,,,,,,,,::,::::::::,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * 777777777777$77777777777+::::::::::::::,,,,,,,=7IIIII78ZI?II78$7++D7?7O777II?
 * ?:,,,:,,,::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * $$$$$$$$$$$$$77=:,:::::::::::::::::::::::::::,,7II$,,8ZZI++$8ZZ?+=ZI==IIII,+7
 * :,,,,:::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * $$$I~::::::::::::::::::::::::::::::::::::::::::II+,,,OOO7?$DOZII$I$I7=77?,,,,
 * ,,:::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::+ZZ?,$ZZ$77ZZ$?,,,,,:::
 * :::::::::::::::::::::::,::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::I$::::::::::::::
 * :::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
 * GlassGiant.com
 *
 *
 */
