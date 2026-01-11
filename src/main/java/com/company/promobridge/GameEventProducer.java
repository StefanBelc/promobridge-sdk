package com.company.promobridge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameEventProducer {

    private final KafkaTemplate<String,GameEvent> kafkaTemplate;
    @Value("${myapp.kafka.topic.game-events}")
    private String gameEventsTopic;

    public GameEventProducer(KafkaTemplate<String, GameEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceTournamentEvent(GameEvent gameEvent) {
        kafkaTemplate.send(gameEventsTopic, gameEvent.gameId(), gameEvent);
    }
}
