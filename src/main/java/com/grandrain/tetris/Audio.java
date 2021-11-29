package com.grandrain.tetris;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public final class Audio {

    private static MediaPlayer player;
    private static double effectsVolume;

    public static void playBrickHit() { playEffect("brick-hit"); }
    public static void  playBrickRotate() { playEffect("brick-rotate"); }
    public static void  playForceHit() { playEffect("force-hit"); }
    public static void playRowClear() { playEffect("row-clear"); }

    public static void playGameOver() {
        player.stop();
        playEffect("game-over");
    }

    public static void playMusic() {
        player = createPlayer("soundtrack");
        player.setVolume(Data.getMusicVolume());

        player.setOnEndOfMedia(() -> {
            player.seek(Duration.ZERO);
            player.play();
        });

        player.play();
    }

    public static void stopMusic() { player.stop(); }

    public static void setMusicVolume(double value) { player.setVolume(value); }

    public static void setEffectsVolume(double value) { effectsVolume = value; }

    private static void playEffect(String sound) {
        MediaPlayer effectPlayer = createPlayer("effects/" + sound);
        effectPlayer.setVolume(effectsVolume);
        effectPlayer.play();
    }

    private static MediaPlayer createPlayer(String file) {
        effectsVolume = Data.getEffectsVolume();

        Media media = new Media(Objects.requireNonNull(
                Audio.class.getResource("/com/grandrain/tetris/audio/" + file + ".mp3")).toExternalForm()
        );

        return new MediaPlayer(media);
    }

}
