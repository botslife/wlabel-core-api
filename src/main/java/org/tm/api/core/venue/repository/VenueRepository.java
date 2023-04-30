package org.tm.api.core.venue.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.venue.entity.Venue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface VenueRepository extends R2dbcRepository<Venue, Integer> {
    Mono<Venue> findByName(String name);

    Flux<Venue> findByCountryId(Integer countryId);

}
