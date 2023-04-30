package org.tm.api.core.site.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.tm.api.core.site.entity.SiteSettings;
import reactor.core.publisher.Mono;

public interface SiteSettingsRepository extends R2dbcRepository<SiteSettings, Integer> {
    Mono<SiteSettings> findBySiteId(Integer siteId);
}
