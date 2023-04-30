package org.tm.api.core.venue.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.venue.entity.Venue;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VenueRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Address1 cannot be null")
    @Size(min = 2, max = 200, message = "Address1 is mandatory and has to be between 2 to 200 in length")
    private String address1;

    private String address2;

    private String postCode;

    private String briefAddress;

    @NotNull(message = "countryIso3 cannot be null ")
    private String countryIso3;

    private String addressNotes;

    @PositiveOrZero(message ="Total places has to be 1 and above")
    private int totalPlaces;

    private Double lat;

    private Double lng;

    public static Venue from(VenueRequest request, Integer countryId){
        return Venue.builder().name(request.getName()).address1(request.getAddress1()).address2(request.getAddress2()).
                postcode(request.getPostCode()).briefAddress(request.getBriefAddress()).countryId(countryId).
                addressNotes(request.getAddressNotes()).totalPlaces(request.getTotalPlaces()).lat(request.getLat()).lng(request.getLng()).build();
    }

    public static Mono<Venue> from(Venue venue){
        return Mono.just(venue);
    }

}
