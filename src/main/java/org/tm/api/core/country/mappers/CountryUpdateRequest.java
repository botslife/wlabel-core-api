package org.tm.api.core.country.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.country.entity.Country;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryUpdateRequest {

    @PositiveOrZero(message ="Country update can't be empty or zero")
    private Integer id;

    @Size(min = 3, max = 3, message = "ISO3 has to be 3 chars in length")
    private String iso3;

    @NotBlank
    @Size(min = 2, max = 2, message = "ISO2 has to be 2 chars in length")
    private String iso2;

    @NotBlank
    @Size(min = 3, max = 80, message = "Country name is mandatory and has to be between 3 to 80 in length")
    private String name;

    private Double lat;

    private Double lng;

    public static Country from(CountryUpdateRequest request){
        return Country.builder().id(request.getId()).iso3(request.getIso3().toUpperCase()).iso2(request.getIso2().toUpperCase()).name(request.getName()).lat(request.getLat()).lng(request.getLng()).build();
    }

    public static Mono<Country> from(Country country){
        return Mono.just(country);
    }

}
