package io.github.liangyuanpeng;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class ZotContainer {
    public GenericContainer zot = new GenericContainer(DockerImageName.parse("ghcr.io/project-zot/zot-linux-amd64:latest"))
            .withExposedPorts(5000);

    public void start(){
        zot.start();
    }

    public void stop(){
        zot.stop();
    }
}
