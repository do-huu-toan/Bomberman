package sample.models;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class playSound {

    private final static String anItemURl = new File("").getAbsolutePath() + "\\src\\Sound\\anItem.wav";
    private final static String datBomURL = new File("").getAbsolutePath() + "\\src\\Sound\\datBom.wav";
    private final static String anPortalURL = new File("").getAbsolutePath() + "\\src\\Sound\\anPortal.wav";
    public static void play_anItem()
    {
        //System.out.println(anItem1);
        Media media = new Media(new File(anItemURl).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    public static void play_datBom()
    {
        Media media = new Media(new File(datBomURL).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    public static void play_anPortal()
    {
        Media media = new Media(new File(anPortalURL).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

}
