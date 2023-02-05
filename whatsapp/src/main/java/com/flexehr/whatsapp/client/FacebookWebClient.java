package com.flexehr.whatsapp.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacebookWebClient {

    @Autowired
    @Qualifier(value = "FacebookWebClient")
    WebClient client;

    String token = "";
    String jsonPayload = "{\"messaging_product\":\"whatsapp\",\"to\":\"917043400140\",\"type\":\"text\",\"text\":{\"preview_url\":false,\"body\":\"Yeh hai InteliJ\"}}";

//    String jsonPayload = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\",\"to\":\"917043400140\",\"type\":\"template\",\"template\":{\"name\":\"TEMPLATE_NAME\",\"language\":{\"code\":\"LANGUAGE_AND_LOCALE_CODE\"},\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"text-string\"},{\"type\":\"currency\",\"currency\":{\"fallback_value\":\"VALUE\",\"code\":\"USD\",\"amount_1000\":\"NUMBER\"}},{\"type\":\"date_time\",\"date_time\":{\"fallback_value\":\"DATE\"}}]}]}}";
    public Mono<String> sendHelloMessage() {
        return client.post().uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token)
            .body(BodyInserters.fromValue(jsonPayload))
            .exchangeToMono(clientResponse -> {
                if (clientResponse.statusCode().isError()) {
                    return clientResponse.bodyToMono(String.class);
                }
                return clientResponse.bodyToMono(new ParameterizedTypeReference<String>() {
                });
            });
    }
}
