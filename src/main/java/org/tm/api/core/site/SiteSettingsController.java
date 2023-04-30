package org.tm.api.core.site;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.site.entity.SiteSettings;
import org.tm.api.core.site.mappers.SiteSettingsCreateRequest;
import org.tm.api.core.site.mappers.SiteSettingsResponse;
import org.tm.api.core.site.mappers.SiteSettingsUpdateRequest;
import org.tm.api.core.site.service.SiteSettingsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/site/settings")
@SecurityRequirement(name="Authorization")
@Tag(name="Site Settings",description = "Site Settings resource operations")
public class SiteSettingsController {

    private final SiteSettingsService service;

    @Autowired
    public SiteSettingsController(SiteSettingsService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<SiteSettings> readSites() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Mono<SiteSettingsResponse>> create(@Valid  @RequestBody SiteSettingsCreateRequest request){ return service.save(request); }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<SiteSettingsResponse>> findById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public ResponseEntity<Mono<SiteSettingsResponse>> update(@Valid @RequestBody SiteSettingsUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> delete(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }



    @GetMapping(path="/site/{siteId}")
    public ResponseEntity<Mono<SiteSettingsResponse>> findBySiteId(@Valid @PathVariable("siteId") Integer siteId){
        return service.findBySiteId(siteId);
    }

}
