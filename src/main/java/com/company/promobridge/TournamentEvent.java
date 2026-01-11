package com.company.promobridge;

import lombok.Builder;

import java.time.Duration;
import java.util.List;
@Builder
record TournamentEvent(String tournamentId,
                       TournamentStatus tournamentStatus,
                       int matches,
                       int totalPlayers,
                       Duration totalDuration,
                       Duration avgMatchDuration,
                       List<GameEvent> gameResults) {
}
