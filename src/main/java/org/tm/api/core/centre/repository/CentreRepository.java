package org.tm.api.core.centre.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.centre.entity.Centre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CentreRepository extends R2dbcRepository<Centre, Integer> {

    Mono<Centre> findByName(String name);
    Flux<Centre> findBySiteId(Integer siteId);
    Flux<Centre> findByStatus(String status);
    Flux<Centre> findByType(String type);

}
