package io.github.liangyuanpeng.oras;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;

public class Downloader {

    private static final Logger logger = LoggerFactory.getLogger(Downloader.class);

    private CloseableHttpClient httpClient;

    private static Downloader INSTANCE = new Downloader();

    private RequestConfig requestConfig = RequestConfig
            .custom()
            .setSocketTimeout(3000)
            .setConnectTimeout(3000)
            .setConnectionRequestTimeout(3000)
            .build();

    public static Downloader getInstalce(){
        return INSTANCE;
    }

    public Downloader(){
        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SSLConnectionSocketFactory connectionSocketFactory =
                new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        httpClient = httpClientBuilder.build();
    }

    /**
     * download command of oras from github
     */
    public void download(){
        HttpGet httpGet = new HttpGet("https://github.com/oras-project/oras/releases/download/v1.0.0/oras_1.0.0_linux_amd64.tar.gz");
        logger.debug("send http get [{}]", httpGet.getURI());
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);
            DataOutputStream out = new DataOutputStream(new FileOutputStream("/tmp/oras.tar.gz",true));
            out.write(bytes);
            out.close();
            //TODO
        } catch (Exception e) {
            logger.error("send http get error:\n", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("send http get error:\n", e);
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("send http get error:\n", e);
            }
        }
    }

}
