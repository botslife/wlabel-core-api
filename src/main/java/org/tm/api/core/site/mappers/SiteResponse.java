package org.tm.api.core.site.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.site.entity.Site;
import reactor.core.publisher.Mono;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteResponse {

    private Integer id;
    private String name;
    private Integer countryId;
    private String countryIso3;
    private String countryName;
    private String status;
    private String domain;

    public static SiteResponse from(Site site, Country country){
        return SiteResponse.builder().id(site.getId()).name(site.getName()).status(site.getStatus()).countryIso3(country.getIso3()).countryName(country.getName()).countryId(country.getId()).domain(site.getDomain()).build();
    }

    public static Mono<SiteResponse> from(Mono<Site> site, Country country){
        return Mono.just(from(site.block(),country));
    }
}
