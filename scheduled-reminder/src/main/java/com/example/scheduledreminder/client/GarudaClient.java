package com.example.scheduledreminder.client;

import com.example.scheduledreminder.model.enums.ApiResponseStatus;
import com.example.scheduledreminder.model.exception.BusinessException;
import com.example.scheduledreminder.model.request.WhatsappAppointmentBookingTemplateRequest;
import com.example.scheduledreminder.model.response.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GarudaClient {


    @Autowired
    @Qualifier(value = "GarudaWebClient")
    WebClient garudaWebClient;

    public Mono<GlobalResponse<Boolean>> sendAppointmentDetailsWhatsappMessage(
        WhatsappAppointmentBookingTemplateRequest request) {
        return garudaWebClient.post()
            .uri("/whatsapp/send/appointment")
            .body(BodyInserters.fromValue(request))
            .exchangeToMono(clientResponse -> {
                if (clientResponse.statusCode().isError()) {
                    return clientResponse.bodyToMono(GlobalResponse.class).flatMap(error -> {
                        log.error(
                            "Error in #sendAppointmentDetailsWhatsappMessage response with code: {} and message: {}",
                            error.getResponseCode(), error.getMessage());
                        return Mono.error(new BusinessException(
                            ApiResponseStatus.getInstance(String.valueOf(error.getResponseCode()))));
                    });
                }
                return clientResponse.bodyToMono(new ParameterizedTypeReference<GlobalResponse<Boolean>>() {
                });
            });
    }
}
