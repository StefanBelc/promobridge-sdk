# PromoBridge SDK

![CI](https://github.com/StefanBelc/promobridge-sdk/actions/workflows/ci.yml/badge.svg)

A small shared Java library that gives every service in the promo platform **one definition of the event contracts**
and **one way to publish them to Kafka**. Producers (TicTacToe service) and consumers (PromoService, event gateway) depend
on the same versioned artifact, so a change to an event is made once and released as a new version.

## What is inside

| Type | Purpose |
| --- | --- |
| `GameEvent`, `TournamentEvent` | Immutable Java records (with Lombok builders) describing a game or tournament |
| `GameStatus`, `TournamentStatus` | `CREATED`, `STARTED`, `IN_PROGRESS`, `FINISHED` |
| `GameEventProducer`, `TournamentEventProducer` | Spring `@Service` beans wrapping `KafkaTemplate`; they publish with the game or tournament id as the message key, so all events for one id land on the same partition, in order |
| `application-sdk.yml` | Shared producer settings (String keys, JSON values) and topic names, activated with the `sdk` Spring profile |

## Using it

```xml
<dependency>
    <groupId>com.company.promobridge</groupId>
    <artifactId>promobridge-sdk</artifactId>
    <version>4.5</version>
</dependency>
```

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.myservice", "com.company.promobridge"})
public class MyServiceApplication { }
```

```yaml
spring:
  profiles:
    active: sdk   # loads the SDK's Kafka producer settings and topic names
```

```java
tournamentEventProducer.produceTournamentEvent(
        TournamentEvent.builder().tournamentId(id).tournamentStatus(TournamentStatus.STARTED).build());
```

## Building and releasing

Requirements: JDK 21 (`mvn -v` must show Java 21) and Maven.

```bash
mvn install          # installs the current SNAPSHOT into your local Maven repository
```

Releases are tagged in Git (latest: `4.5`). Services build a release with
`git clone --branch 4.5 … && mvn install -DskipTests`, as their CI and Dockerfiles do.

## Next steps

- Turn it into a true Spring Boot starter: an `@AutoConfiguration` class registered in
  `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`, so services no longer need
  `@ComponentScan`
- Publish releases to GitHub Packages instead of building from source
- Add unit tests for the producers
