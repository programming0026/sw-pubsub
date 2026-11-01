package com.juniverse.pubsub.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** Se suscribe a {@code app.subscribe} al arrancar y registra los mensajes que llegan. */
@Service
@Slf4j
public class SubscriberService {
    @Value("${app.subscribe}")
    private String subscription;

    private final PubSubTemplate pubSubTemplate;

    /**
     * Recibe la plantilla de Pub/Sub.
     * @param pubSubTemplate plantilla
     */
    public SubscriberService(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    /** Inicia la suscripción: registra cada mensaje y confirma su recepción (ack). */
    @PostConstruct
    public void init() {
        log.info("Subscriber iniciado...");

        pubSubTemplate.subscribe(subscription, message -> {
            String data = message.getPubsubMessage().getData().toStringUtf8();
            log.info("Mensaje recibido: {}", data);
            message.ack();
        });
    }
}
