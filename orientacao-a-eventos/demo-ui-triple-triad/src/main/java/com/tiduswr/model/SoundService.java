package com.tiduswr.model;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundService {
    private Clip clip;
    private int loopStartFrame;
    private int loopEndFrame;

    public SoundService(String path) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);  // Começa do início
        clip.start();
    }

    public void playWithLoop(int loopStartMillis, int loopEndMillis) {
        loopStartFrame = millisToFrames(loopStartMillis);
        loopEndFrame = loopEndMillis > 0 ? millisToFrames(loopEndMillis) : -1;  // Se -1, repete até o fim

        // Configura os pontos de loop
        clip.setLoopPoints(loopStartFrame, loopEndFrame);
        clip.setFramePosition(0);  // Começa do início
        clip.start();              // Toca o som desde o início

        // Inicia o loop contínuo a partir do ponto definido
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    private int millisToFrames(int millis) {
        float frameRate = clip.getFormat().getFrameRate();
        return Math.round(millis * frameRate / 1000);
    }
}