package com.juniverse.pubsub.config;

//import com.google.api.gax.core.NoCredentialsProvider;
//import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.PublisherFactory;
import com.google.cloud.spring.pubsub.support.SubscriberFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuración de Pub/Sub. Para usar el emulador local descomenta los beans de canal y credenciales. */
@Configuration
public class PubSubConfig {
    @Value("${app.publish}")
    private String topicName;

    @Value("${app.subscribe}")
    private String subscriptionName;

    /**
     * Plantilla para publicar y suscribirse.
     * @param publisherFactory fábrica de publicadores
     * @param subscriberFactory fábrica de suscriptores
     * @return la plantilla
     */
    @Bean
    public PubSubTemplate pubSubTemplate(
            PublisherFactory publisherFactory,
            SubscriberFactory subscriberFactory) {
        return new PubSubTemplate(publisherFactory, subscriberFactory);
    }
/*
    @Bean
    public InstantiatingGrpcChannelProvider channelProvider() {
        return InstantiatingGrpcChannelProvider.newBuilder()
                .setEndpoint("localhost:8085")
                .build();
    }

    @Bean
    public NoCredentialsProvider credentialsProvider() {
        return NoCredentialsProvider.create();
    }*/
}
