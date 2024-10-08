package com.tiduswr.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundService {

    private List<Clip> clipPool = new ArrayList<>();
    private int poolSize;
    private String path;
    private int loopStartFrame;
    private int loopEndFrame;

    public SoundService(String path, int poolSize) {
        this.path = path;
        this.poolSize = poolSize;
        initializeClipPool();
    }

    public SoundService(String path, int poolSize, float volume) {
        this(path, poolSize);
        setVolume(volume);
    }

    private void initializeClipPool() {
        try {
            for (int i = 0; i < poolSize; i++) {
                Clip clip = AudioSystem.getClip();
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                clip.open(audioInputStream);
                clipPool.add(clip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Clip clip = getAvailableClip();
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);  // Reinicia do início
            clip.start();
        }
    }

    public SoundService playWithLoop(int loopStartMillis, int loopEndMillis) {
        Clip clip = getAvailableClip();
        if (clip != null) {
            loopStartFrame = millisToFrames(loopStartMillis, clip);
            loopEndFrame = loopEndMillis > 0 ? millisToFrames(loopEndMillis, clip) : -1;  // Se -1, repete até o fim

            clip.setLoopPoints(loopStartFrame, loopEndFrame);
            clip.setFramePosition(0);  // Começa do início
            clip.start();

            // Inicia o loop contínuo a partir do ponto definido
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        return this;
    }

    private int millisToFrames(int millis, Clip clip) {
        float frameRate = clip.getFormat().getFrameRate();
        return Math.round(millis * frameRate / 1000);
    }

    private Clip getAvailableClip() {
        for (Clip clip : clipPool) {
            if (!clip.isRunning()) {
                return clip;
            }
        }
        return null;
    }

    public void stop() {
        for (Clip clip : clipPool) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        for (Clip clip : clipPool) {
            if (clip.isOpen()) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // Converte o volume de 0.0 (silêncio) a 1.0 (máximo) para o valor decibel apropriado
                float dB = (float) (20 * Math.log10(volume));
                gainControl.setValue(dB);
            }
        }
    }
}