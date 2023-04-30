package org.tm.api.core.centre.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.centre.entity.CentreVenue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CentreVenueRepository extends R2dbcRepository<CentreVenue, Integer> {

    Flux<CentreVenue> findByCentreId(Integer centreId);
    Flux<CentreVenue> findByVenueId(Integer venueId);

    @Query("select * from centre_venue where centre_id = :centreId and venue_id = :venueId")
    Mono<CentreVenue> findByCentreAndVenue(Integer centreId, Integer venueId);
}
