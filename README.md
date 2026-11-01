# Ejemplo de Google Cloud Pub/Sub con Spring Boot

Publica y recibe mensajes con Google Cloud Pub/Sub desde una aplicación Spring Boot reactiva (WebFlux). Se puede probar sin cuenta de Google usando el emulador local.

## Características

- `POST /test?message=...` publica un mensaje en el topic.
- Un suscriptor se inicia con la aplicación y registra los mensajes recibidos.
- Funciona con Pub/Sub real o con el emulador de Google.
- Topic y suscripción configurables (`app.publish`, `app.subscribe`).

## Requisitos

- JDK 21 y Maven 3.9 o superior.
- Docker (para el emulador de Pub/Sub) o un proyecto de Google Cloud.

## Compilar y ejecutar

```bash
mvn spring-boot:run
```

## Probar con el emulador local

```bash
# 1) Arrancar el emulador
docker run -d -p 8085:8085 --name pubsub-emulator \
  gcr.io/google.com/cloudsdktool/cloud-sdk:latest \
  gcloud beta emulators pubsub start --host-port=0.0.0.0:8085

# 2) Crear el topic y la suscripción (dentro del contenedor)
docker exec -it pubsub-emulator bash
export PUBSUB_EMULATOR_HOST=localhost:8085
gcloud config set project test-project
gcloud pubsub topics create test-topic
gcloud pubsub subscriptions create test-subscribe --topic=test-topic
```

Después, en `application.yaml` descomenta `emulator-host` y, en `PubSubConfig`, los beans del canal y las credenciales. Publica un mensaje:

```bash
curl -X POST "http://localhost:8080/test?message=hola"
```

## Con Google Cloud real

```bash
gcloud auth application-default login
export PROJECT_NAME=mi-proyecto-gcp
mvn spring-boot:run
```

## Notas

- Si no se define `PROJECT_NAME`, se usa `test-project`.

## Estructura

El servicio de publicación tiene un contrato (`PublisherService`) y una implementación con Google Cloud Pub/Sub (`PubSubPublisherService`); el controlador depende solo del contrato.

## Licencia

Código de ejemplo de uso libre con fines educativos.
