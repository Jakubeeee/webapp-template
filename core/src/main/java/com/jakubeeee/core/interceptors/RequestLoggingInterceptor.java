package com.jakubeeee.core.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            traceRequest(request, body);
        } catch (UnknownHostException ignored) {
        }
        ClientHttpResponse response = execution.execute(request, body);
        try {
            traceResponse(response);
        } catch (UnknownHostException ignored) {
        }
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        LOG.trace("===========================request begin================================================");
        LOG.trace("URI         : {}", request.getURI());
        LOG.trace("Method      : {}", request.getMethod());
        LOG.trace("Headers     : {}", request.getHeaders());
        LOG.trace("Request body: {}", new String(body, UTF_8));
        LOG.trace("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        var inputStringBuilder = new StringBuilder();
        var bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        LOG.trace("============================response begin==========================================");
        LOG.trace("Status code  : {}", response.getStatusCode());
        LOG.trace("Status text  : {}", response.getStatusText());
        LOG.trace("Headers      : {}", response.getHeaders());
        LOG.trace("Response body: {}", inputStringBuilder.toString());
        LOG.trace("=======================response end=================================================");
    }

}