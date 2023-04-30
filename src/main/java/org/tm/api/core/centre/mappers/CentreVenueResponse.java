package org.tm.api.core.centre.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.entity.CentreVenue;
import org.tm.api.core.venue.entity.Venue;
import reactor.core.publisher.Mono;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentreVenueResponse {

    private Integer centreVenueId;
    private Integer centreId;
    private Integer venueId;
    private String centreName;
    private String centreDescription;
    private String centreType;
    private String centreStatus;
    private String venueName;
    private String venueAddress1;
    private int venueTotalPlaces;

    public static CentreVenueResponse from(Centre centre,Venue venue,CentreVenue centreVenue){

        return CentreVenueResponse.builder().centreVenueId(centreVenue.getId()).centreId(centreVenue.getCentreId()).venueId(centreVenue.getVenueId()).
                centreName(centre.getName()).centreDescription(centre.getDescription()).centreType(centre.getType()).centreStatus(centre.getStatus()).
                venueName(venue.getName()).venueAddress1(venue.getAddress1()).venueTotalPlaces(venue.getTotalPlaces()).build();
    }

    public static Mono<CentreVenueResponse> from(Mono<Centre> centre,Mono<Venue> venue,Mono<CentreVenue> centreVenue ){
        return Mono.just(from(centre.share().block(),venue.share().block(),centreVenue.share().block()));
    }

}
