package com.juniverse.pubsub.controller;

import com.juniverse.pubsub.service.PublisherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
/** Endpoint de prueba para publicar mensajes. */
@RestController
@RequestMapping("/test")
public class TestController {

    private final PublisherService publisherService;

    /**
     * Recibe el servicio de publicación.
     * @param publisherService servicio que publica en el topic
     */
    public TestController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * Publica un mensaje.
     * @param message texto a publicar
     * @return identificador del mensaje en Pub/Sub
     */
    @PostMapping
    public Mono<String> send(@RequestParam String message) {
        return publisherService.publish(message);
    }
}
