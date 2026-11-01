package com.juniverse.pubsub.service;

import reactor.core.publisher.Mono;

/**
 * Contrato de PublisherService.
 */
public interface PublisherService {

    /**
     * Publica un mensaje.
     * @param message texto a publicar
     * @return identificador del mensaje publicado
     */
    Mono<String> publish(String message);
}
