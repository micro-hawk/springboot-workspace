package com.flexehr.whatsapp.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ClientBean {

    private String whatsappVersion = "v15.0";
    private final String myNumberId = "";
    private final String testNumberId = "";

    String fbClientUrl = "https://graph.facebook.com/" + whatsappVersion + "/" + testNumberId + "/messages";
//    String fbClientUrl = "https://graph.facebook.com/v15.0/104164899262351/messages";

    @Bean(name = "FacebookWebClient")
    public WebClient facebookWebClient() {
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
            .responseTimeout(Duration.ofMillis(2000))
            .doOnConnected(conn ->
                conn.addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
            .baseUrl(fbClientUrl)
            .clientConnector(connector)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
