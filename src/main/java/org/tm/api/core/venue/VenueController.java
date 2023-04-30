package org.tm.api.core.venue;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.venue.entity.Venue;
import org.tm.api.core.venue.mappers.VenueRequest;
import org.tm.api.core.venue.mappers.VenueResponse;
import org.tm.api.core.venue.mappers.VenueUpdateRequest;
import org.tm.api.core.venue.service.VenueService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/venue")
@SecurityRequirement(name="Authorization")
@Tag(name="Venue",description = "Venue resource operations")
public class VenueController {

    private final VenueService service;

    public VenueController(VenueService service){
        this.service = service;
    }

    @GetMapping
    public Flux<Venue> readVenues() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<VenueResponse>> createVenue(@Valid  @RequestBody VenueRequest request){ return service.save(request); }

    @PutMapping
    public ResponseEntity<Mono<VenueResponse>> updateVenue(@Valid @RequestBody VenueUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> deleteVenue(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<VenueResponse>> readById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<Mono<VenueResponse>> readById(@Valid @PathVariable("name") String name) {
        return service.findByName(name);
    }

    @GetMapping(path="/country/{countryId}")
    public Flux<Venue> findByCountryId(@Valid @PathVariable("countryId") Integer countryId){
        return service.findByCountryId(countryId);
    }

}
