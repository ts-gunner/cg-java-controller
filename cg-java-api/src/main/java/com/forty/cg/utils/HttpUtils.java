package com.forty.cg.utils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {

    public static String get(String url, Map<String, String> headers) {
        String result = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                result = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;

        }

    }

    public static String post(String url, Map<String, String> headers, String body) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost request = new HttpPost(url);
        String result = "";
        try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
            request.setEntity(new StringEntity(body));
            request.setHeader("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                result = EntityUtils.toString(response.getEntity());
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }
}
