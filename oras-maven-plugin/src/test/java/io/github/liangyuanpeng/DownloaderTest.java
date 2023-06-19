package io.github.liangyuanpeng;

import io.github.liangyuanpeng.oras.Downloader;
import org.junit.Test;

public class DownloaderTest {

    @Test
    public void download(){
        Downloader.getInstalce().download();
    }
}
