package org.tm.api.core.centre;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tm.api.core.centre.entity.CentreSettings;
import org.tm.api.core.centre.mappers.CentreSettingsCreateRequest;
import org.tm.api.core.centre.mappers.CentreSettingsResponse;
import org.tm.api.core.centre.mappers.CentreSettingsUpdateRequest;
import org.tm.api.core.centre.service.CentreSettingsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/centre/settings")
@SecurityRequirement(name="Authorization")
@Tag(name="Centre Settings",description = "Centre Settings resource operations")
public class CentreSettingsController {

    private CentreSettingsService service;

    public CentreSettingsController(CentreSettingsService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<CentreSettings> all() {
        return service.all();
    }

    @PostMapping
    public ResponseEntity<Mono<CentreSettingsResponse>> save(@Valid  @RequestBody CentreSettingsCreateRequest request){ return service.create(request); }

    @PutMapping
    public ResponseEntity<Mono<CentreSettingsResponse>> update(@Valid @RequestBody CentreSettingsUpdateRequest request){
        return service.update(request);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Mono> delete(@Valid @PathVariable("id") Integer id){
        return service.delete(id);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Mono<CentreSettingsResponse>> findById(@Valid @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping(path="/centre/{centreId}")
    public ResponseEntity<Mono<CentreSettingsResponse>> findByCentreId(@Valid @PathVariable("centreId") Integer centreId){
        return service.findByCentreId(centreId);
    }

}
