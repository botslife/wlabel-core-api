package org.tm.api.core.centre;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.centre.entity.CentreVenue;
import org.tm.api.core.centre.mappers.CentreVenueCreateRequest;
import org.tm.api.core.centre.mappers.CentreVenueResponse;
import org.tm.api.core.centre.mappers.CentreVenueUpdateRequest;
import org.tm.api.core.centre.service.CentreVenueService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/centrevenue")
@SecurityRequirement(name="Authorization")
@Tag(name="CentreVenue",description = "CentreVenue resource operations")
public class CentreVenueController {

    private CentreVenueService service;

    public CentreVenueController(CentreVenueService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<CentreVenue> all() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<CentreVenueResponse>> save(@Valid  @RequestBody CentreVenueCreateRequest request){ return service.create(request); }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<CentreVenueResponse>> findById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public ResponseEntity<Mono<CentreVenueResponse>> update(@Valid @RequestBody CentreVenueUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> delete(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path="/venue/{venueId}")
    public Flux<CentreVenue> findByVenueId(@Valid @PathVariable("venueId") Integer venueId){
        return service.findByVenueId(venueId);
    }

    @GetMapping(path="/centre/{centreId}")
    public Flux<CentreVenue> findByCentreId(@Valid @PathVariable("centreId") Integer centreId){
        return service.findByCentreId(centreId);
    }

}
