package util;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * peiyan lin
 * 20201287
 */
public class AudioPlay {
    private AudioClip audioClip = null;

    private File file;

    private AudioPlay(String path) {

        this.file = new File(path);
        try {
            audioClip = Applet.newAudioClip(file.toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, AudioPlay> audioClips = new LinkedHashMap<>();

    static {
        audioClips.put("boom", new AudioPlay("res/music/boom.wav"));
        audioClips.put("filed", new AudioPlay("res/music/filed.wav"));
        audioClips.put("success", new AudioPlay("res/music/success.wav"));

    }

    public static void stopOne(String name) {
        AudioPlay audioPlay = audioClips.get(name);
        if (audioPlay != null) {
            audioPlay.getAudioClip().stop();
        }
    }

    public static void play(String name) {
        play(name, false);
    }

    public static void playNew(String name, Boolean newThread) {
        AudioPlay audioPlay = audioClips.get(name);
        if (audioPlay != null) {
            if (newThread) {
                File file = audioPlay.getFile();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Applet.newAudioClip(file.toURI().toURL()).play();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } else {
                play(name);
            }
        }
    }

    public static void play(String name, boolean loop) {
        AudioPlay audioPlay = audioClips.get(name);
        if (audioPlay != null) {
            AudioClip clip = audioPlay.getAudioClip();
            clip.play();
            if (loop) {
                clip.loop();
            }
        }
    }

    public AudioClip getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(AudioClip audioClip) {
        this.audioClip = audioClip;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

