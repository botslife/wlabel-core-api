package org.tm.api.core.site.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.site.entity.Site;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SiteRepository extends R2dbcRepository<Site, Integer> {

    Mono<Site> findByDomain(String domain);

    Flux<Site> findByCountryId(Integer countryId);
}
