package org.tm.api.core.country;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.country.mappers.CountryRequest;
import org.tm.api.core.country.mappers.CountryResponse;
import org.tm.api.core.country.mappers.CountryUpdateRequest;
import org.tm.api.core.country.service.CountryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/country")
@SecurityRequirement(name="Authorization")
@Tag(name="Country",description = "Country resource operations")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service){
        this.service = service;
    }

    @GetMapping
    public Flux<CountryResponse> readCountries() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<CountryResponse>> createCountry(@Valid @RequestBody CountryRequest request){ return service.create(request); }

    @PutMapping
    public ResponseEntity<Mono<CountryResponse>> updateCountry(@Valid @RequestBody CountryUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> deleteCountry(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path = "/iso3/{iso3}")
    public ResponseEntity<Mono<CountryResponse>> readCountryByIso3(@Valid  @PathVariable("iso3") String iso3){
        return service.findByIso3(iso3);
    }

    @GetMapping(path = "/iso2/{iso2}")
    public ResponseEntity<Mono<CountryResponse>> readCountryByIso2(@Valid  @PathVariable("iso2") String iso2){
        return service.findByIso2(iso2);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<CountryResponse>> readById(@Valid @PathVariable("id") Integer id) {
        return service.read(id);
    }

}
