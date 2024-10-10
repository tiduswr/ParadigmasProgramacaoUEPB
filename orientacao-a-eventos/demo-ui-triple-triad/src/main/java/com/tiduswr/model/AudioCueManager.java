package com.tiduswr.model;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.adonax.audiocue.AudioCue;

public class AudioCueManager {

    private static AudioCueManager instance;
    private Map<String, AudioCue> audioCueMap = new HashMap<>();
    private final int POOL_SIZE = 10;

    private AudioCueManager() {}

    public static AudioCueManager getInstance() {
        if (instance == null) {
            instance = new AudioCueManager();
        }
        return instance;
    }

    public AudioCue getAudioCue(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (!audioCueMap.containsKey(path)) {
            URL url = this.getClass().getResource("/" + path);
            if (url == null) {
                throw new RuntimeException("Arquivo de áudio não encontrado: " + path);
            }
            AudioCue audioCue = AudioCue.makeStereoCue(url, POOL_SIZE);
            audioCue.open(2048);
            audioCueMap.put(path, audioCue);
        }
        return audioCueMap.get(path);
    }

    public void closeAll() {
        for (AudioCue cue : audioCueMap.values()) {
            cue.close();
        }
        audioCueMap.clear();
    }

    public void closeInstace(AudioCue audioCue, String pathToRemove) {
        audioCue.close();
        audioCueMap.remove(pathToRemove);
    }
}
