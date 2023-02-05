package com.flexehr.whatsapp.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacebookWebClient {

    @Autowired
    @Qualifier(value = "FacebookWebClient")
    WebClient client;

    public Mono<String> sendWhatsappMessage() {
        UriSpec<RequestBodySpec> uriSpec = client.post();
        RequestBodySpec bodySpec = uriSpec.uri(
            uriBuilder -> uriBuilder.pathSegment("/").build());

        RequestHeadersSpec<?> headersSpec = bodySpec.body(
            BodyInserters.fromPublisher(Mono.just("data"), String.class)
        );
        ResponseSpec responseSpec = headersSpec.header(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION,
                "EAAbeC7Y9lHsBAMaFjtk2214PnylVC4eXbDvouLhMz2wzVS7T4a0GpFAJFlePIc6GRD2ZAQ9QwrwMBAecaugCKhJSdiUvGa"
                    + "PyqstN1gVCFFdn1Pk7RTyaRbM5FtGc9jwNHfHKD5mfd1rJssJSOHs8b4PjDVHd0yoQjMLDZAhoyWpjizfHq4qtbbE3OtYlndrE2Yp3v9xwZDZD")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve();

        return headersSpec.exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode().is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                    .flatMap(Mono::error);
            }
        });
    }
}
