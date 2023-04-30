package org.tm.api.core.centre.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.entity.CentreSettings;
import reactor.core.publisher.Mono;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreSettingsResponse {

    private Integer id;
    private Integer centreId;
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

    public static CentreSettingsResponse from(Centre centre, CentreSettings centreSettings){
        return CentreSettingsResponse.builder().id(centreSettings.getId()).centreId(centreSettings.getCentreId()).name(centre.getName()).description(centre.getDescription()).type(centre.getType()).
                phone(centre.getPhone()).mobile(centre.getMobile()).chairmanUserId(centre.getChairmanTeacherId()).email(centre.getEmail()).
                uri(centre.getUri()).status((centre.getStatus())).thumbImage(centre.getThumbImage()).fullImage(centre.getFullImage()).
                siteId(centre.getSiteId()).build();
    }

    public static Mono<CentreSettingsResponse> from(Mono<Centre> centreMono, Mono<CentreSettings> centreSettingsMono){
        return Mono.just(from(centreMono.share().block(),centreSettingsMono.share().block()));
    }

}
