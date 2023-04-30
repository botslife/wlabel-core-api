package org.tm.api.core.site.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.site.entity.Site;
import org.tm.api.core.site.entity.SiteSettings;
import reactor.core.publisher.Mono;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteSettingsResponse {

    private Integer id;
    private Integer siteId;
    private String name;
    private Integer countryId;
    private String status;
    private String domain;

    public static SiteSettingsResponse from(SiteSettings siteSettings, Site site){
        return SiteSettingsResponse.builder().id(siteSettings.getId()).siteId(siteSettings.getSiteId()).name(site.getName()).status(site.getStatus()).domain(site.getDomain()).countryId(site.getCountryId()).build();
    }

    public static Mono<SiteSettingsResponse> from(Mono<Site> siteMono, Mono<SiteSettings> siteSettingsMono){
        return Mono.just(from(siteSettingsMono.share().block(),siteMono.block()));
    }
}
