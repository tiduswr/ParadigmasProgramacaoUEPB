package com.tiduswr.model;

import java.util.HashMap;
import java.util.Map;

public class SoundServices {
    
    private Map<String, SoundService> services;
    private float vol;

    public SoundServices(){
        this.services = new HashMap<>();
        this.vol = 0.8f;
    }

    public void createSoundService(String serviceName, String path){
        this.services.put(serviceName, new SoundService(path, vol));
    }

    public void createSoundService(String serviceName, String path, float customVol){
        this.services.put(serviceName, new SoundService(path, customVol));
    }

    public SoundService getSoundService(String serviceName){
        return this.services.get(serviceName);
    }

}
