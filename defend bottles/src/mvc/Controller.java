package mvc;

import util.GameConstants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * peiyan lin
 * 20201287
 */

//Singeton pattern
public class Controller implements KeyListener {

    private static boolean KeyUpPressed = false;
    private static boolean KeyDownPressed = false;
    private static boolean KeyLeftPressed = false;
    private static boolean KeyRightPressed = false;
    private static boolean KeyKPressed = false;

    private static boolean KeyAPressed = false;
    private static boolean KeySPressed = false;
    private static boolean KeyDPressed = false;
    private static boolean KeyWPressed = false;
    private static boolean KeySpacePressed = false;


    private static final Controller instance = new Controller();

    public Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    @Override
    // Key pressed , will keep triggering
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (MainWindow.state == GameConstants.GAME_START) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    setKeyAPressed(true);
                    break;
                case KeyEvent.VK_S:
                    setKeySPressed(true);
                    break;
                case KeyEvent.VK_W:
                    setKeyWPressed(true);
                    break;
                case KeyEvent.VK_D:
                    setKeyDPressed(true);
                    break;
                case KeyEvent.VK_SPACE:
                    setKeySpacePressed(true);
                    break;
                default:
                    //System.out.println("Controller test:  Unknown key pressed");
                    break;
            }
            if (MainWindow.gameModel == GameConstants.GAME_MODEL_DOUBLE_PLAYER) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        setKeyUpPressed(true);
                        break;
                    case KeyEvent.VK_DOWN:
                        setKeyDownPressed(true);
                        break;
                    case KeyEvent.VK_LEFT:
                        setKeyLeftPressed(true);
                        break;
                    case KeyEvent.VK_RIGHT:
                        setKeyRightPressed(true);
                        break;
                    case KeyEvent.VK_K:
                        setKeyKPressed(true);
                        break;
                    default:
                        //System.out.println("Controller test:  Unknown key pressed");
                        break;
                }
            }
        } else if ((MainWindow.state == GameConstants.GAME_OVER
                || MainWindow.state == GameConstants.GAME_SUCCESS) && e.getKeyCode()==KeyEvent.VK_0) {

            MainWindow.resetGame();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (MainWindow.state == GameConstants.GAME_START) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    setKeyAPressed(false);
                    break;
                case KeyEvent.VK_S:
                    setKeySPressed(false);
                    break;
                case KeyEvent.VK_W:
                    setKeyWPressed(false);
                    break;
                case KeyEvent.VK_D:
                    setKeyDPressed(false);
                    break;
                case KeyEvent.VK_SPACE:
                    setKeySpacePressed(false);
                    break;
                default:
                    //System.out.println("Controller test:  Unknown key pressed");
                    break;
            }
            if (MainWindow.gameModel == GameConstants.GAME_MODEL_DOUBLE_PLAYER) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        setKeyUpPressed(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        setKeyDownPressed(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        setKeyLeftPressed(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        setKeyRightPressed(false);
                        break;
                    case KeyEvent.VK_K:
                        setKeyKPressed(false);
                        break;
                    default:
                        //System.out.println("Controller test:  Unknown key pressed");
                        break;
                }
            }
        }

        //upper case

    }

    public static void resetContrller() {

        setKeyAPressed(false);
        setKeySPressed(false);
        setKeyWPressed(false);
        setKeyDPressed(false);
        setKeySpacePressed(false);

        setKeyUpPressed(false);
        setKeyLeftPressed(false);
        setKeyRightPressed(false);
        setKeyDownPressed(false);
        setKeyKPressed(false);

    }

    public boolean isKeyAPressed() {
        return KeyAPressed;
    }


    public static void setKeyAPressed(boolean keyAPressed) {
        KeyAPressed = keyAPressed;
    }


    public boolean isKeySPressed() {
        return KeySPressed;
    }


    public static void setKeySPressed(boolean keySPressed) {
        KeySPressed = keySPressed;
    }


    public boolean isKeyDPressed() {
        return KeyDPressed;
    }


    public static void setKeyDPressed(boolean keyDPressed) {
        KeyDPressed = keyDPressed;
    }


    public boolean isKeyWPressed() {
        return KeyWPressed;
    }


    public static void setKeyWPressed(boolean keyWPressed) {
        KeyWPressed = keyWPressed;
    }


    public boolean isKeySpacePressed() {
        return KeySpacePressed;
    }


    public static void setKeySpacePressed(boolean keySpacePressed) {
        KeySpacePressed = keySpacePressed;
    }

    public static boolean isKeyUpPressed() {
        return KeyUpPressed;
    }

    public static void setKeyUpPressed(boolean keyUpPressed) {
        KeyUpPressed = keyUpPressed;
    }

    public static boolean isKeyDownPressed() {
        return KeyDownPressed;
    }

    public static void setKeyDownPressed(boolean keyDownPressed) {
        KeyDownPressed = keyDownPressed;
    }

    public static boolean isKeyLeftPressed() {
        return KeyLeftPressed;
    }

    public static void setKeyLeftPressed(boolean keyLeftPressed) {
        KeyLeftPressed = keyLeftPressed;
    }

    public static boolean isKeyRightPressed() {
        return KeyRightPressed;
    }

    public static void setKeyRightPressed(boolean keyRightPressed) {
        KeyRightPressed = keyRightPressed;
    }

    public static boolean isKeyKPressed() {
        return KeyKPressed;
    }

    public static void setKeyKPressed(boolean keyKPressed) {
        KeyKPressed = keyKPressed;
    }

    public void resetPlayerOne() {
        setKeyAPressed(false);
        setKeySPressed(false);
        setKeyWPressed(false);
        setKeyDPressed(false);
    }
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
