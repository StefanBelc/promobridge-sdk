package com.company.promobridge;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapp.kafka.topic")
@Data
public class KafkaTopicsProperties {

    private String tournamentEventsTopic = "tournament.events";
}