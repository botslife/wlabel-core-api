package org.tm.api.core.site.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.site.entity.SiteSettings;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteSettingsCreateRequest {

    @Positive(message = "SiteID is a number and cannot be null or non zero number")
    @NotNull
    private Integer siteId;

    public static SiteSettings from(SiteSettingsCreateRequest request){
        return SiteSettings.builder().siteId(request.getSiteId()).build();
    }

    public Mono<SiteSettings> from(SiteSettings siteSettings){
        return Mono.just(siteSettings);
    }
}
