package io.github.liangyuanpeng.oras.core;

import io.github.liangyuanpeng.oras.core.oci.GithubToken;
import io.github.liangyuanpeng.oras.core.oci.Manifests;
import io.github.liangyuanpeng.oras.core.util.JSONUtils;
import okhttp3.Headers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrasTest {

    @Test
    public void tesatPull() throws IOException {

        String repo = "liangyuanpeng/files";
        String tag = "lan";

        GithubToken token = Oras.getToken("https://ghcr.io/token?service=ghcr.io&scope=repository:"+repo+":pull");
        Assertions.assertNotNull(token);
        System.out.println(token.getToken());

        Map map = new HashMap();
        map.put("Authorization","Bearer "+token.getToken());
        map.put("accept","application/vnd.oci.image.manifest.v1+json,application/vnd.unknown.config.v1+json");

        Headers headers = Headers.of(map);
        String pull = Oras.manifests("https://ghcr.io/v2/"+repo+"/manifests/"+tag, headers);
        Assertions.assertNotNull(pull);
        Assertions.assertNotEquals("",pull);
        Manifests manifests = (Manifests) JSONUtils.jsonToBean(pull, Manifests.class);

        String blob = manifests.getLayers().get(0).getDigest().replaceAll("sha256:","");
        Assertions.assertNotNull(blob);
        Assertions.assertNotEquals("",blob);
        System.out.println("blob:"+blob);

        map.put("accept","application/vnd.oci.image.manifest.v1+json,application/vnd.unknown.config.v1+json,application/vnd.oci.image.layer.v1.tar+gzip");
        map.put("Content-Type","application/octet-stream");
        map.put("Docker-Content-Digest",blob);
        headers = Headers.of(map);

        Oras.pull("https://ghcr.io/v2/"+repo+"/blobs/"+blob.replaceAll("sha256:",""),headers);

    }

    @Test
    public  void testPull2(){
//        Oras.pull("http://192.168.3.103:5004","");
    }
}
