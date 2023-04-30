package org.tm.api.core.centre.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.constants.CentreStatus;
import org.tm.api.core.centre.constants.CentreType;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.common.enumvalidator.EnumPattern;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreCreateRequest {

    @NotNull(message = "name cannot be null")
    @NotBlank
    private String name;
    @Positive
    @NotNull
    private Integer siteId;
    @NotNull
    @Positive
    private Integer chairmanUserId;
    @NotNull
    private String email;
    private String uri;
    @EnumPattern(enumClazz = CentreStatus.class,message = "Only accepts ACTIVE|INACTIVE|SUSPENDED")
    private String status;
    @EnumPattern(enumClazz = CentreType.class,message = "Only accepts COUNTRYPARENT|MAIN|SUB|AREA")
    private String type;
    private String thumbImage;
    private String fullImage;
    private String description;
    private String phone;
    private String mobile;


    public static Centre from(CentreCreateRequest request){
        return Centre.builder().name(request.getName()).type(CentreType.valueOf(request.getType()).name()).phone(request.getPhone()).
                description(request.getDescription()).
                mobile(request.getMobile()).chairmanTeacherId(request.getChairmanUserId()).siteId(request.getSiteId()).email(request.getEmail()).
                uri(request.getUri()).status(CentreStatus.valueOf(request.getStatus()).name()).thumbImage(request.getThumbImage()).
                fullImage(request.getFullImage()).build();
    }

    public static Mono<Centre> from(Centre centre){
        return Mono.just(centre);
    }


}
