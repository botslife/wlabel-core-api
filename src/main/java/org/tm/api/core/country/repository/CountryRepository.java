package org.tm.api.core.country.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.country.entity.Country;
import reactor.core.publisher.Mono;

public interface CountryRepository extends R2dbcRepository<Country, Integer> {
    Mono<Country> findByIso3(String iso3);
    Mono<Country> findByIso2(String iso2);
}
