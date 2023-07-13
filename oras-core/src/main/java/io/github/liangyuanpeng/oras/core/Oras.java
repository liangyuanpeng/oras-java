package io.github.liangyuanpeng.oras.core;

import io.github.liangyuanpeng.oras.core.oci.GithubToken;
import io.github.liangyuanpeng.oras.core.util.JSONUtils;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

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
