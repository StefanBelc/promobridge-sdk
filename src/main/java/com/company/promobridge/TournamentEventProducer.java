package com.company.promobridge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TournamentEventProducer {

    private final KafkaTemplate<String,TournamentEvent> kafkaTemplate;
    @Value("${myapp.kafka.topic.tournament-events}")
    private String tournamentEventsTopic;

    public TournamentEventProducer(KafkaTemplate<String, TournamentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceTournamentEvent (TournamentEvent tournamentEvent) {
        kafkaTemplate.send(tournamentEventsTopic, tournamentEvent.tournamentId(), tournamentEvent);

    }
}
