package org.tm.api.core.centre.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.mappers.CentreCreateRequest;
import org.tm.api.core.centre.mappers.CentreResponse;
import org.tm.api.core.centre.mappers.CentreUpdateRequest;
import org.tm.api.core.centre.repository.CentreRepository;
import org.tm.api.core.site.entity.Site;
import org.tm.api.core.site.repository.SiteRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CentreService {

    private CentreRepository repository;
    private SiteRepository siteRepository;

    public CentreService(CentreRepository repository,SiteRepository siteRepository){
        this.repository = repository;
        this.siteRepository = siteRepository;
    }

    public ResponseEntity<Mono<CentreResponse>> create(@RequestBody CentreCreateRequest request){
        Mono<Site> site = siteRepository.findById(request.getSiteId());
        if(site.share().block()!=null){
            Mono<Centre> centreCheck = repository.findByName(request.getName());
            if(centreCheck.share().block()==null){
                try{
                    Centre centre = CentreCreateRequest.from(request);
                    return ResponseEntity.status(HttpStatus.CREATED).body(CentreResponse.from(repository.save(centre),site.share().block()));
                }catch(DataIntegrityViolationException uniqueViolation){
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Mono<CentreResponse>> findById(@RequestParam Integer id) {
        Mono<Centre> centre = repository.findById(id);
        if(centre.share().block()!=null){
            Mono<Site> site = siteRepository.findById(centre.share().block().getSiteId());
            return ResponseEntity.ok(CentreResponse.from(centre,site.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CentreResponse>> findByName(@RequestParam String name) {
        Mono<Centre> centre = repository.findByName(name);
        if(centre.share().block()!=null){
            Mono<Site> site = siteRepository.findById(centre.share().block().getSiteId());
            return ResponseEntity.ok(CentreResponse.from(centre,site.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<Centre> centre = repository.findById(id);
        if(centre.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CentreResponse>> update(@RequestBody CentreUpdateRequest request){
        if(request.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            Mono<Centre> centreCheck = repository.findById(request.getId());
            if(centreCheck.share().block()!=null){
                Mono<Site> site = siteRepository.findById(request.getSiteId());
                if(site.share().block()!=null){
                    try{
                        Centre centre = CentreUpdateRequest.from(request);
                        return ResponseEntity.status(HttpStatus.CREATED).body(CentreResponse.from(repository.save(centre),site.share().block()));
                    }catch(DataIntegrityViolationException uniqueViolation){
                        return ResponseEntity.status(HttpStatus.CONFLICT).build();
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public Flux<Centre> all() {
        return repository.findAll().switchIfEmpty(Flux.<Centre>empty());
    }

    public Flux<Centre> findByType(String type){
        return repository.findByType(type);
    }

    public Flux<Centre> findByStatus(String status) { return repository.findByStatus(status); }

    public Flux<Centre> findBySiteId(Integer siteId) {return repository.findBySiteId(siteId); }

}
