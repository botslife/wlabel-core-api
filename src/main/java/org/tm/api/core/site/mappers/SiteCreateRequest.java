package org.tm.api.core.site.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.common.constants.LotusCoreConstants;
import org.tm.api.core.common.enumvalidator.EnumPattern;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.site.constants.SiteStatus;
import org.tm.api.core.site.entity.Site;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteCreateRequest {

    @Size(min = 3, max = 80, message = "Should be between 3 to 80 chars in length")
    private String name;

    @Size(min = 3, max = 3, message = "Has to be 3 chars in length")
    private String countryIso3;

    @EnumPattern(enumClazz = SiteStatus.class,message = "Only accepts ACTIVE|INACTIVE|SUSPENDED")
    private String status;

    @NotBlank
    @Pattern(regexp = LotusCoreConstants.URLREGEX, message = "Not a valid URL")
    private String domain;

    public static Site from(SiteCreateRequest request, Country country){
        return Site.builder().status(SiteStatus.valueOf(request.getStatus()).name()).name(request.getName()).countryId(country.getId()).domain(request.getDomain()).build();
    }

    public Mono<Site> from(Site site){
        return Mono.just(site);
    }
}
