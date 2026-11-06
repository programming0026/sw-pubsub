
package com.juniverse.pubsub.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author Java Universe
 */
@Service
public class PubSubPublisherService implements PublisherService {
    @Value("${app.publish}")
    private String topicName;
    private final PubSubTemplate pubSubTemplate;

    /**
     * Recibe la plantilla de Pub/Sub.
     * @param pubSubTemplate plantilla
     */
    public PubSubPublisherService(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    /**
     * Publica un mensaje.
     * @param message texto a publicar
     * @return identificador del mensaje publicado
     */
    @Override
    public Mono<String> publish(String message) {
        return Mono.fromFuture(pubSubTemplate.publish(topicName, message));
    }
}
