package org.tm.api.core.centre.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.site.entity.Site;
import reactor.core.publisher.Mono;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreResponse {

    private Integer id;
    private String name;
    private String description;
    private String type;
    private String phone;
    private String mobile;
    private Integer chairmanUserId;
    private String email;
    private String uri;
    private String status;
    private String thumbImage;
    private String fullImage;
    private Integer siteId;
    private String siteName;
    private String siteDomain;

    public static CentreResponse from(Centre centre, Site site){
        return CentreResponse.builder().id(centre.getId()).name(centre.getName()).description(centre.getDescription()).type(centre.getType()).
                phone(centre.getPhone()).mobile(centre.getMobile()).chairmanUserId(centre.getChairmanTeacherId()).email(centre.getEmail()).
                uri(centre.getUri()).status((centre.getStatus())).thumbImage(centre.getThumbImage()).fullImage(centre.getFullImage()).
                siteId(centre.getSiteId()).siteDomain(site.getDomain()).siteName(site.getName()).siteId(site.getId()).build();
    }

    public static Mono<CentreResponse> from(Mono<Centre> centre, Site site){
        return Mono.just(from(centre.block(),site));
    }

}
