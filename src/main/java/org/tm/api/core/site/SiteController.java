package org.tm.api.core.site;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.site.entity.Site;
import org.tm.api.core.site.mappers.SiteCreateRequest;
import org.tm.api.core.site.mappers.SiteResponse;
import org.tm.api.core.site.mappers.SiteUpdateRequest;
import org.tm.api.core.site.service.SiteService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/site")
@SecurityRequirement(name="Authorization")
@Tag(name="Site",description = "Site resource operations")
public class SiteController {

    private final SiteService service;

    @Autowired
    public SiteController(SiteService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Site> readSites() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<SiteResponse>> create(@Valid  @RequestBody SiteCreateRequest request){ return service.create(request); }

    @PutMapping
    public ResponseEntity<Mono<SiteResponse>> update(@Valid @RequestBody SiteUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> delete(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<SiteResponse>> findById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping(path="/domain/{domain}")
    public ResponseEntity<Mono<SiteResponse>> findByDomain(@Valid @PathVariable("domain") String domain) {
        return service.findByDomain(domain);
    }

    @GetMapping(path="/country/{countryId}")
    public Flux<Site> findByCountryId(@Valid @PathVariable("countryId") Integer countryId){
        return service.findByCountryId(countryId);
    }

}
