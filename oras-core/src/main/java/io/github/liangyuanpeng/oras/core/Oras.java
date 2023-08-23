package io.github.liangyuanpeng.oras.core;

import io.github.liangyuanpeng.oras.core.oci.GithubToken;
import io.github.liangyuanpeng.oras.core.oci.Manifests;
import io.github.liangyuanpeng.oras.core.util.JSONUtils;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Oras {
    public static String manifests(String url, Headers headers) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            if(response.code()==200){
                String resp = response.body().string();
                return resp;
            }
        }
        return "";
    }

    public static void pull(String registry,String repo,String tag,boolean needToken) throws IOException {
        Map map = new HashMap();
        if(needToken) {
            GithubToken token = Oras.getToken(registry + "/token?service=ghcr.io&scope=repository:" + repo + ":pull");
            System.out.println(token.getToken());
            map.put("Authorization", "Bearer " + token.getToken());
        }
        map.put("accept","application/vnd.oci.image.manifest.v1+json,application/vnd.unknown.config.v1+json");
        Headers headers = Headers.of(map);
        String pull = Oras.manifests(registry+"/v2/"+repo+"/manifests/"+tag, headers);
        Manifests manifests = (Manifests) JSONUtils.jsonToBean(pull, Manifests.class);
        String blob = manifests.getLayers().get(0).getDigest().replaceAll("sha256:","");
        System.out.println("blob:"+blob);
        map.put("accept","application/vnd.oci.image.manifest.v1+json,application/vnd.unknown.config.v1+json,application/vnd.oci.image.layer.v1.tar+gzip");
        map.put("Content-Type","application/octet-stream");
        map.put("Docker-Content-Digest",blob);
        headers = Headers.of(map);
        Oras.pull(registry+"/v2/"+repo+"/blobs/"+blob.replaceAll("sha256:",""),headers);
    }


    public static void pull(String url, Headers headers) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            if(response.code()==200){
            }
        }
    }

    public static GithubToken getToken(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 200) {
                return (GithubToken) JSONUtils.jsonToBean(response.body().string(), GithubToken.class);
            }
        }
        return null;
    }

}
