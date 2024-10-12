package com.tiduswr.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pelo gerenciamento de múltiplos serviços de som.
 */
public class SoundServices {
    
    private Map<String, SoundService> services; // Mapa que armazena serviços de som
    private float vol; // Volume padrão para os serviços de som

    /**
     * Construtor que inicializa o mapa de serviços de som e define o volume padrão.
     */
    public SoundServices(){
        this.services = new HashMap<>();
        this.vol = 0.8f; // Volume padrão inicial
    }

    /**
     * Cria um novo serviço de som e o adiciona ao mapa com um volume padrão.
     *
     * @param serviceName Nome do serviço de som
     * @param path Caminho do arquivo de áudio
     */
    public void createSoundService(String serviceName, String path){
        this.services.put(serviceName, new SoundService(path, vol));
    }

    /**
     * Cria um novo serviço de som e o adiciona ao mapa com um volume customizado.
     *
     * @param serviceName Nome do serviço de som
     * @param path Caminho do arquivo de áudio
     * @param customVol Volume customizado para o serviço de som
     */
    public void createSoundService(String serviceName, String path, float customVol){
        this.services.put(serviceName, new SoundService(path, customVol));
    }

    /**
     * Retorna o serviço de som associado ao nome fornecido.
     *
     * @param serviceName Nome do serviço de som
     * @return Serviço de som correspondente, ou null se não existir
     */
    public SoundService getSoundService(String serviceName){
        return this.services.get(serviceName);
    }
}