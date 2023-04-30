package org.tm.api.core.venue.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.venue.entity.Venue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VenueResponse {

    private Integer venueId;
    private String name;
    private String address1;
    private String address2;
    private String postCode;
    private String briefAddress;
    private String countryIso3;
    private Integer countryId;
    private String countryName;
    private String addressNotes;
    private int totalPlaces;
    private Double lat;
    private Double lng;

    public static VenueResponse from(Venue venue,Country country){
        return VenueResponse.builder().venueId(venue.getId()).name(venue.getName()).address1(venue.getAddress1()).address2(venue.getAddress2()).
                postCode(venue.getPostcode()).briefAddress(venue.getBriefAddress()).addressNotes(venue.getAddressNotes()).
                totalPlaces(venue.getTotalPlaces()).lat(venue.getLat()).lng(venue.getLng()).countryId(country.getId()).countryIso3(country.getIso3()).countryName(country.getName()).build();
    }

    public static Mono<VenueResponse> from(Mono<Venue> venue, Country country){
        return Mono.just(from(venue.share().block(),country));
    }

    public static Flux<VenueResponse> from(Flux<Venue> venue,Country country){
        return venue.map(c -> from(c,country));
    }


}
