package org.tm.api.core.centre.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.centre.entity.CentreSettings;
import reactor.core.publisher.Mono;

public interface CentreSettingsRepository extends R2dbcRepository<CentreSettings, Integer> {

    Mono<CentreSettings> findByCentreId(Integer centreId);
}
