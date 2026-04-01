package com.company.promobridge;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KafkaTopicsProperties.class)
@ConditionalOnMissingBean(TournamentEventProducer.class)
public class PromobridgeAutoConfig {
}
