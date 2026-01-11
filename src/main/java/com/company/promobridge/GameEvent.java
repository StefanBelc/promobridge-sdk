package com.company.promobridge;

import lombok.Builder;

import java.time.Duration;

@Builder
public record GameEvent(String gameId,
                        String tournamentId,
                        GameStatus status,
                        String player1,
                        String player2,
                        String winner,
                        String loser,
                        boolean draw,
                        Duration duration) {
}