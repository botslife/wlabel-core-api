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
public class SiteSettingsUpdateRequest {

    @Positive(message = "ID is a number and cannot be null or non zero number")
    @NotNull
    private Integer id;

    @Positive(message = "SiteID is a number and cannot be null or non zero number")
    @NotNull
    private Integer siteId;


    public static SiteSettings from(SiteSettingsUpdateRequest request){
        return SiteSettings.builder().siteId(request.getSiteId()).id(request.getId()).build();
    }

    public Mono<SiteSettings> from(SiteSettings siteSettings){
        return Mono.just(siteSettings);
    }
}
