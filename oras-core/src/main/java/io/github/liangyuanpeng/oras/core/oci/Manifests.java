package io.github.liangyuanpeng.oras.core.oci;

import java.util.Collections;
import java.util.List;

public class Manifests {
    private String mediaType;
    private ManifestsConfig config;
    private List<Layer> layers = Collections.emptyList();


    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public ManifestsConfig getConfig() {
        return config;
    }

    public void setConfig(ManifestsConfig config) {
        this.config = config;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
