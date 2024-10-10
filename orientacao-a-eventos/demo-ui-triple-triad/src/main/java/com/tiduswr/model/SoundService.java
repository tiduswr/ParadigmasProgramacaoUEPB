package com.tiduswr.model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.adonax.audiocue.AudioCue;
import com.adonax.audiocue.AudioCueInstanceEvent;
import com.adonax.audiocue.AudioCueListener;

public class SoundService {

    private AudioCue audioCue;
    private float vol;
    private String path;

    public SoundService(String path) {
        this.path = path;
        initializeAudioCue(path);
    }

    public SoundService(String path, float volume) {
        this(path);
        this.vol = volume;
    }

    private void initializeAudioCue(String path) {
        try {
            audioCue = AudioCueManager.getInstance().getAudioCue(path);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        play(false);
    }

    public void play(boolean loop) {
        if (audioCue != null) {
            audioCue.play(vol, 0.0, 1.0, loop ? -1 : 0);
        }
    }

    public void playThenLoop(String wavToLoopPath) {
        play();
        audioCue.addAudioCueListener(new AudioCueListener() {

            @Override
            public void audioCueOpened(long now, int threadPriority, int bufferSize, AudioCue source) {}

            @Override
            public void audioCueClosed(long now, AudioCue source) {}

            @Override
            public void instanceEventOccurred(AudioCueInstanceEvent event) {
                switch (event.type) {
                    case STOP_INSTANCE:
                        try {                            
                            var manager = AudioCueManager.getInstance();
                            manager.closeInstace(audioCue, path);
                            path = wavToLoopPath;

                            audioCue = manager.getAudioCue(path);
                            play(true);
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        break;
                
                    default:
                        break;
                }
            }
            
        });
    }

    public void close() {
        AudioCueManager.getInstance().closeAll();
    }
}