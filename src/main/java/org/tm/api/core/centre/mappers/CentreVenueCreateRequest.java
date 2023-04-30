package org.tm.api.core.centre.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.entity.CentreVenue;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreVenueCreateRequest {

    @Positive
    @NotNull
    private Integer venueId;
    @NotNull
    @Positive
    private Integer centreId;

    public static CentreVenue from(CentreVenueCreateRequest request){
        return CentreVenue.builder().centreId(request.getCentreId()).centreId(request.getCentreId()).venueId(request.getVenueId()).build();
    }

    public static Mono<CentreVenue> from(CentreVenue centreVenue){
        return Mono.just(centreVenue);
    }

}
