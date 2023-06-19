package io.github.liangyuanpeng;

import io.github.liangyuanpeng.oras.Downloader;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class DownloaderTest {

    ZotContainer zotContainer = new ZotContainer();

    @Test
    public void download(){
        new ZotContainer().start();
        Downloader.getInstalce().download();
    }

    @BeforeAll
    public void setup(){
        zotContainer.start();
    }

    @AfterAll
    public void close(){
        zotContainer.stop();
    }

}
