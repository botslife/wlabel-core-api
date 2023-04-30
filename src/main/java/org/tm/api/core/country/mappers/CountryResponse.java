package org.tm.api.core.country.mappers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tm.api.core.country.entity.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CountryResponse {
    
    private Integer id;
    private String iso3;
    private String iso2;
    private String name;
    private Double lat;
    private Double lng;

    public static CountryResponse from(Country country){
        return CountryResponse.builder().id(country.getId()).iso3(country.getIso3()).iso2(country.getIso2()).name(country.getName()).lat(country.getLat()).lng(country.getLng()).build();
    }

    public static Mono<CountryResponse> from(Mono<Country> country){
        return Mono.just(from(country.share().block()));
    }

    public static Flux<CountryResponse> from(Flux<Country> country){
        return country.map( c -> from(c));
    }


}
