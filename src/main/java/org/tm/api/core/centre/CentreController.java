package org.tm.api.core.centre;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.mappers.CentreCreateRequest;
import org.tm.api.core.centre.mappers.CentreResponse;
import org.tm.api.core.centre.mappers.CentreUpdateRequest;
import org.tm.api.core.centre.service.CentreService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/centre")
@SecurityRequirement(name="Authorization")
@Tag(name="Centre",description = "Centre resource operations")
public class CentreController {

    private CentreService service;

    public CentreController(CentreService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Centre> all() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<CentreResponse>> save(@Valid  @RequestBody CentreCreateRequest request){ return service.create(request); }

    @PutMapping
    public ResponseEntity<Mono<CentreResponse>> update(@Valid @RequestBody CentreUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> delete(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<CentreResponse>> findById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<Mono<CentreResponse>> findByName(@Valid @PathVariable("name") String name) {
        return service.findByName(name);
    }

    @GetMapping(path="/site/{siteId}")
    public Flux<Centre> findBySiteId(@Valid @PathVariable("siteId") Integer siteId){
        return service.findBySiteId(siteId);
    }

    @GetMapping(path="/status/{status}")
    public Flux<Centre> findByStatus(@Valid @PathVariable("status") String status){
        return service.findByStatus(status);
    }

    @GetMapping(path="/type/{type}")
    public Flux<Centre> findByType(@Valid @PathVariable("type") String type){
        return service.findByType(type);
    }

}
