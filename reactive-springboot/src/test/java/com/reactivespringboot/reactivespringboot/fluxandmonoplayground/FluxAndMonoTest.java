package com.reactivespringboot.reactivespringboot.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {
    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
//                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .map(String::toUpperCase)
                .log();

        stringFlux
                .subscribe(System.out::println,
                        (e) -> System.err.println("Exception is: "+e),
                        () -> System.out.println("Completed"));
    }

//    public void fluxTestElements_WithoutError() {
//        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();
//    }
}
