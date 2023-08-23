package io.github.liangyuanpeng.oras.launcher;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class ZotContainer extends GenericContainer<ZotContainer>{

    public ZotContainer(String image){
        super("ghcr.io/project-zot/zot-linux-amd64:latest");
    }

    @Override
    public void close(){
        super.close();
    }

}
