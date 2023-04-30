package org.tm.api.core.centre.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.entity.CentreSettings;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreSettingsUpdateRequest {

    @Positive
    @NotNull
    private Integer id;
    @Positive
    @NotNull
    private Integer centreId;

    public static CentreSettings from(CentreSettingsUpdateRequest request){
        return CentreSettings.builder().centreId(request.getCentreId()).id(request.getId()).build();
    }

    public static Mono<CentreSettings> from(CentreSettings centreSettings){
        return Mono.just(centreSettings);
    }
}
